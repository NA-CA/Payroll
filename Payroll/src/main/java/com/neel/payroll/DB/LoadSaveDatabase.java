/*
MIT License

Copyright (c) 2022 Neel Apte

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.neel.payroll.DB;

import com.neel.payroll.Model.TimeSheetOneEmployeeModel;
import com.neel.payroll.Model.EmployeeW4Model;
import com.neel.payroll.Model.CompanyEmployeePayrollRatesModel;
import com.neel.payroll.Model.CompanyInfoModel;
import com.neel.payroll.Model.EmployeeCA_DE4Model;
import com.neel.payroll.Model.EmployeeInfoModel;
import com.neel.payroll.Model.PayrollHistoryModel;
import com.neel.payroll.Model.OnePayPeriodAllEmpoyeesTimeSheets;
import com.neel.payroll.Model.YearToDateTotals;
import com.neel.payroll.Util.Util;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

/**
 *
 * @author Neel
 */
public class LoadSaveDatabase {

    private LoadSaveDatabase() {
    }

    private static LoadSaveDatabase instance = new LoadSaveDatabase();

    public static LoadSaveDatabase getInstance() {
        if (instance == null) {
            instance = new LoadSaveDatabase();
        }

        return instance;
    }

    public boolean isinitiialized() {
        return m_initiialized;
    }

    public CompanyInfoModel getdbCompanyData() {
        return m_dbCompanyData;
    }

    public void setdbCompanyData(CompanyInfoModel dbCompanyData) {
        this.m_dbCompanyData = dbCompanyData;
    }

    private boolean m_initiialized = false;

    private CompanyInfoModel m_dbCompanyData = new CompanyInfoModel();

    public void LoadFromDatabase() throws SQLException {
        LoadCompanyInfo();
        LoadAllEmployeesWithW4();
        m_initiialized = true;

    }

    private void LoadAllEmployeesWithW4() throws SQLException {
        LoadEmployeeW4Info();
        LoadEmployeeInfo();
    }

    private void LoadCompanyInfo() throws SQLException {
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();
        String sql = "SELECT * FROM companyinfo";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {

            String companyName = result.getString("companyName");
            String companyAddress = result.getString(2);
            String phoneNumber = result.getString(3);
            String federalEIN = result.getString(4);
            String stateID = result.getString(5);
            String payFrequency = result.getString(6);
            String adminUsername = result.getString(7);
            String password = result.getString(8);

            m_dbCompanyData.setCompanyName(companyName);
            m_dbCompanyData.setFederalEIN(federalEIN);
            m_dbCompanyData.setStateID(stateID);
            m_dbCompanyData.setPayrollFrequency(CompanyInfoModel.PayrollFrequency.valueOf(payFrequency));

        }

        statement.close();

        LoadCompanyPayrollRates();

    }

    private void LoadCompanyPayrollRates() throws SQLException {
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();
        String sql = "SELECT * FROM custom_taxrate";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {

            var year = result.getInt("year");
            var futa = result.getFloat("futa");
            var sdi = result.getFloat("sdi");

            var companyRates = new CompanyEmployeePayrollRatesModel();

            companyRates.setYear(year);
            companyRates.setUnemploymentFUTARatePercent(futa);
            companyRates.setSdi(sdi);

            m_dbCompanyData.getYearToPayrollTax().put(year, companyRates);
        }

        statement.close();

    }

    public void SaveCompanyInfo(CompanyInfoModel company) throws SQLException {
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();

        String sql = "UPDATE companyinfo SET companyname=?,federalein=?,stateid=?,pay_frequency=?  WHERE companyname=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, company.getCompanyName());
        statement.setString(2, company.getFederalEIN());
        statement.setString(3, company.getStateID());
        statement.setString(4, company.getPayrollFrequency().toString());
        statement.setString(5, this.m_dbCompanyData.getCompanyName());

        int rows = statement.executeUpdate();

        if (rows > 0) {
            System.out.println("A row has been inserted/updated");
        }

        statement.close();

        AddOrUpdateCompanyPayrollRates(company);

        setdbCompanyData(company);
    }

    private void AddOrUpdateCompanyPayrollRates(CompanyInfoModel company) throws SQLException {
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();

        for (Map.Entry<Integer, CompanyEmployeePayrollRatesModel> entry : company.getYearToPayrollTax().entrySet()) {

            CompanyEmployeePayrollRatesModel oneYearTaxRate = entry.getValue();

            String sql = "INSERT INTO custom_taxrate(year,futa,sdi) values (?,?,?) on conflict(year) "
                    + "DO UPDATE SET year=?,futa=?,sdi=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, oneYearTaxRate.getYear());
            statement.setFloat(2, oneYearTaxRate.getUnemploymentFUTARatePercent());
            statement.setFloat(3, oneYearTaxRate.getSdi());
            statement.setInt(4, oneYearTaxRate.getYear());
            statement.setFloat(5, oneYearTaxRate.getUnemploymentFUTARatePercent());
            statement.setFloat(6, oneYearTaxRate.getSdi());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("A row has been inserted/updated");
            }

            statement.close();
        }

    }


    private void LoadEmployeeInfo() throws SQLException {
        m_dbMapEmployeeData.clear();
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();
        String sql = "SELECT * FROM employeeinfo";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {

            int id = result.getInt("id");
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            int ssn = result.getInt("ssn");
            float salary_per_hour = result.getFloat("salary_ph");
            float defRate401K = result.getFloat("def_401k_rate");
            boolean age50Plus = result.getInt("age_50Plus") > 0;

            EmployeeInfoModel oneEmployee = new EmployeeInfoModel();
            oneEmployee.setId(id);;
            oneEmployee.setFirstname(firstname);
            oneEmployee.setLastname(lastname);
            oneEmployee.setSsn(ssn);
            oneEmployee.setSalary_per_hour(salary_per_hour);
            oneEmployee.setEmployee401KDefRate(defRate401K);
            oneEmployee.setAge50Plus(age50Plus);

            oneEmployee.setEmployeeW4(this.m_dbMapEmployeeW4Data.get(id));
            m_dbMapEmployeeData.put(id, oneEmployee);
        }

        statement.close();

    }

    public void AddOrUpdateOneEmployeeInfo(EmployeeInfoModel oneEmployee, boolean bAdd) throws SQLException {

        if (bAdd) {
            AddOneEmployeeInfo(oneEmployee);
        } else {
            UpdateOneEmployeeInfo(oneEmployee);
        }
    }

    private void AddOneEmployeeInfo(EmployeeInfoModel oneEmployee) throws SQLException {
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();

        String sql;

        sql = "INSERT INTO employeeinfo(firstname,lastname,ssn,salary_ph,def_401k_rate,age_50Plus) values (?,?,?,?,?,?)";

        //PreparedStatement statement = connection.prepareStatement(sql);
        PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, oneEmployee.getFirstname());
        statement.setString(2, oneEmployee.getLastname());
        statement.setInt(3, oneEmployee.getSsn());
        statement.setFloat(4, oneEmployee.getSalary_per_hour());
        statement.setFloat(5, oneEmployee.getEmployee401KDefRate());
        statement.setInt(6, (oneEmployee.isAge50Plus() == true) ? 1 : 0);

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating employees failed, no rows affected.");
        }

        if (affectedRows > 0) {
            System.out.println("A row has been inserted/updated");
        }

        ResultSet generatedKeys = statement.getGeneratedKeys();

        if (generatedKeys.next()) {
            long employee_id = generatedKeys.getLong(1);
            EmployeeW4Model oneEmployeeW4 = new EmployeeW4Model();
            oneEmployeeW4.setEmployee_id((int) employee_id);
            AddOrUpdateOneEmployeeW4(oneEmployeeW4, true);
            var de4 = new EmployeeCA_DE4Model();
            de4.setEmployee_id((int) employee_id);
            AddOrUpdateOneEmployeeDE4(de4, true);

        } else {
            throw new SQLException("Creating employee failed, no ID obtained.");
        }

        statement.close();

        LoadAllEmployeesWithW4();

    }

    private void UpdateOneEmployeeInfo(EmployeeInfoModel oneEmployee) throws SQLException {
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();

        String sql;

        sql = "UPDATE employeeinfo SET firstname=?,lastname=?,ssn=?,salary_ph=?,def_401k_rate=?,age_50Plus=?  WHERE id ="
                + oneEmployee.getId();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, oneEmployee.getFirstname());
        statement.setString(2, oneEmployee.getLastname());
        statement.setInt(3, oneEmployee.getSsn());
        statement.setFloat(4, oneEmployee.getSalary_per_hour());
        statement.setFloat(5, oneEmployee.getEmployee401KDefRate());
        statement.setInt(6, (oneEmployee.isAge50Plus() == true) ? 1 : 0);

        int rows = statement.executeUpdate();

        if (rows > 0) {
            System.out.println("A row has been inserted/updated");
        }

        statement.close();

        LoadAllEmployeesWithW4();

    }

    private HashMap<Integer, EmployeeInfoModel> m_dbMapEmployeeData = new HashMap<Integer, EmployeeInfoModel>();

    /**
     * Get the value of m_dbEmployeeData
     *
     * @return the value of m_dbEmployeeData
     */
    public HashMap<Integer, EmployeeInfoModel> getdbEmployeeData() {
        return m_dbMapEmployeeData;
    }

    public OnePayPeriodAllEmpoyeesTimeSheets LoadPayPeriodTimeSheet(long startdate) throws SQLException {
        OnePayPeriodAllEmpoyeesTimeSheets OnePayPeriodModel = new OnePayPeriodAllEmpoyeesTimeSheets();

        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();
        String sql = "SELECT * FROM timesheet WHERE startdate = " + Long.toString(startdate);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        OnePayPeriodModel.setStartDate(startdate);

        Vector<TimeSheetOneEmployeeModel> vctAllEmpTimeSheetOnePayPeriod = new Vector<TimeSheetOneEmployeeModel>();

        while (result.next()) {

            TimeSheetOneEmployeeModel oneEmployeeModel = new TimeSheetOneEmployeeModel();
            int id = result.getInt("employee_id");
            float hours = result.getFloat("hours");

            oneEmployeeModel.setEmployeeId(id);;
            oneEmployeeModel.setHours(hours);
            oneEmployeeModel.setFirstname(m_dbMapEmployeeData.get(id).getFirstname());
            oneEmployeeModel.setLastname(m_dbMapEmployeeData.get(id).getLastname());

            vctAllEmpTimeSheetOnePayPeriod.add(oneEmployeeModel);
        }

        OnePayPeriodModel.setOnePayPeriodAllEmployeeTimeSheets(vctAllEmpTimeSheetOnePayPeriod);
        statement.close();

        return OnePayPeriodModel;
    }

    public OnePayPeriodAllEmpoyeesTimeSheets LoadEmptyPayPeriodTimeSheet() throws SQLException {
        OnePayPeriodAllEmpoyeesTimeSheets OnePayPeriodModel = new OnePayPeriodAllEmpoyeesTimeSheets();

        Vector<TimeSheetOneEmployeeModel> vctAllEmpTimeSheetOnePayPeriod = new Vector<TimeSheetOneEmployeeModel>();

        m_dbMapEmployeeData.forEach((key, oneEmployee) -> {

            TimeSheetOneEmployeeModel oneEmployeeTimesheet = new TimeSheetOneEmployeeModel();
            int id = oneEmployee.getId();
            float hours = 0.0F;

            oneEmployeeTimesheet.setEmployeeId(id);;
            oneEmployeeTimesheet.setHours(hours);
            oneEmployeeTimesheet.setFirstname(oneEmployee.getFirstname());
            oneEmployeeTimesheet.setLastname(oneEmployee.getLastname());

            vctAllEmpTimeSheetOnePayPeriod.add(oneEmployeeTimesheet);

        });
        OnePayPeriodModel.setOnePayPeriodAllEmployeeTimeSheets(vctAllEmpTimeSheetOnePayPeriod);

        return OnePayPeriodModel;
    }

    private boolean CheckEmployeeTimesheetExists(TimeSheetOneEmployeeModel onEmployeeTimesheet,
            long startdate) throws SQLException {

        boolean bPresent = false;
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();
        String sql = "SELECT COUNT(*) FROM timesheet WHERE employee_id=" + onEmployeeTimesheet.getEmployeeId()
                + " AND startdate=" + Long.toString(startdate);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            if (rs.getInt("COUNT(*)") > 0) {
                bPresent = true;
            }

        }

        statement.close();
        return bPresent;
    }

    public TimeSheetOneEmployeeModel LoadOnePayPeriodTimeSheetOneEmployee(long startdate, int employeeId) throws SQLException {

        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();
        String sql = "SELECT * FROM timesheet WHERE startdate = " + Long.toString(startdate) + " AND employee_id = "
                + Integer.toString(employeeId);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        TimeSheetOneEmployeeModel oneTimeSheet = new TimeSheetOneEmployeeModel();

        while (result.next()) {

            int id = result.getInt("employee_id");
            float hours = result.getFloat("hours");

            oneTimeSheet.setEmployeeId(id);;
            oneTimeSheet.setHours(hours);
            oneTimeSheet.setFirstname(m_dbMapEmployeeData.get(id).getFirstname());
            oneTimeSheet.setLastname(m_dbMapEmployeeData.get(id).getLastname());
        }

        statement.close();

        return oneTimeSheet;
    }

    public void AddOrUpdateOnePayrollPeriodTimesheets(OnePayPeriodAllEmpoyeesTimeSheets onePayrollPeriod) throws SQLException {
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();

        long startDate = onePayrollPeriod.getStartDate();
        long endDate = startDate;
        for (var oneEmployeeTimesheet : onePayrollPeriod.getOnePayPeriodAllEmployeeTimeSheets()) {

            boolean bTimeSheetPresent = CheckEmployeeTimesheetExists(oneEmployeeTimesheet, startDate);
            if (bTimeSheetPresent == false) {
                PreparedStatement statement;
                String sql;
                sql = "INSERT INTO timesheet(employee_id,startdate,end_date,hours) values (?,?,?,?)";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, oneEmployeeTimesheet.getEmployeeId());
                statement.setLong(2, startDate);
                statement.setLong(3, endDate);
                statement.setFloat(4, oneEmployeeTimesheet.getHours());
                int rows = statement.executeUpdate();

                if (rows > 0) {
                    System.out.println("A row has been inserted/updated");
                }

                statement.close();
            } else {
                PreparedStatement statement;
                String sql;
                sql = "UPDATE timesheet SET hours=?  WHERE employee_id =" + oneEmployeeTimesheet.getEmployeeId()
                        + " AND startdate=" + Long.toString(startDate);
                statement = connection.prepareStatement(sql);

                statement.setFloat(1, oneEmployeeTimesheet.getHours());
                int rows = statement.executeUpdate();
                if (rows > 0) {
                    System.out.println("A row has been inserted/updated");
                }
                statement.close();
            }

        }

    }

    private long getStartDateSinceEpoch(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private HashMap<Integer, EmployeeW4Model> m_dbMapEmployeeW4Data = new HashMap<Integer, EmployeeW4Model>();

    public void setdbMapEmployeeW4Data(HashMap<Integer, EmployeeW4Model> m_dbMapEmployeeW4Data) {
        this.m_dbMapEmployeeW4Data = m_dbMapEmployeeW4Data;
    }

    public HashMap<Integer, EmployeeW4Model> getdbMapEmployeeW4Data() {
        return this.m_dbMapEmployeeW4Data;
    }

    private void LoadEmployeeW4Info() throws SQLException {
        m_dbMapEmployeeW4Data.clear();
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();
        String sql = "SELECT * FROM w4";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {

            int id = result.getInt("employee_id");
            int submittedNew2020W4 = result.getInt("n2020plus_w4");
            int old_allowance = result.getInt("old_allowance");
            int old_filing_status = result.getInt("old_filing_status");
            int old_add_wh = result.getInt("old_add_wh");
            String n2020_filing_status = result.getString("n2020_filing_status");
            int n2020_multiple_jobs = result.getInt("n2020_multiple_jobs");
            int n2020_step3_line3 = result.getInt("n2020_step3_line3");
            int n2020_step4_line_4a = result.getInt("n2020_step4_line_4a");
            int n2020_step_4_line_4b = result.getInt("n2020_step_4_line_4b");
            int n2020_step_4_line_4c = result.getInt("n2020_step_4_line_4c");
            int exempt = result.getInt("exempt");

            EmployeeW4Model oneEmployeeW4 = new EmployeeW4Model();
            oneEmployeeW4.setEmployee_id(id);
            oneEmployeeW4.setSubmitted2020PlusW4((submittedNew2020W4 > 0));
            oneEmployeeW4.setOldW4NumOfAllowances(old_allowance);
            oneEmployeeW4.setOldW4FilingStatus(old_filing_status);
            oneEmployeeW4.setOldW4AdduitionWithHold(old_add_wh);
            oneEmployeeW4.setNew2020W4FilingStatus(EmployeeW4Model.FilingStatus.valueOf(n2020_filing_status));
            oneEmployeeW4.setNew2020W4MultipleJobs((n2020_multiple_jobs > 0));
            oneEmployeeW4.setNew2020W4Step3Line3Dependents(n2020_step3_line3);
            oneEmployeeW4.setNew2020W4Step4Line4aOtherIncome(n2020_step4_line_4a);
            oneEmployeeW4.setNew2020W4Step4Line4bDeductions(n2020_step_4_line_4b);
            oneEmployeeW4.setNew2020W4Step4Line4cExtraWithHolding(n2020_step_4_line_4c);
            oneEmployeeW4.setExempt(exempt > 0);

            m_dbMapEmployeeW4Data.put(id, oneEmployeeW4);
        }

        statement.close();

    }

    public void AddOrUpdateOneEmployeeW4(EmployeeW4Model oneEmployeeW4, boolean bAdd) throws SQLException {
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();

        String sql;
        if (bAdd) {
            //employee_id is LAST so that most of the code will be same for update

            sql = "INSERT INTO w4(n2020plus_w4,old_allowance,old_filing_status,"
                    + "old_add_wh,n2020_filing_status,n2020_multiple_jobs,n2020_step3_line3,"
                    + "n2020_step4_line_4a,n2020_step_4_line_4b,n2020_step_4_line_4c, exempt, employee_id) "
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?)";

        } else {
            sql = "UPDATE w4 SET n2020plus_w4=?,old_allowance=?,old_filing_status=?,old_add_wh=?,"
                    + "n2020_filing_status=?,n2020_multiple_jobs=?,n2020_step3_line3=?,"
                    + "n2020_step4_line_4a=?,n2020_step_4_line_4b=?,n2020_step_4_line_4c=?,exempt=? "
                    + "  WHERE employee_id =" + oneEmployeeW4.getEmployee_id();

        }

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, ((oneEmployeeW4.isSubmitted2020PlusW4() == true) ? 1 : 0));
        statement.setInt(2, oneEmployeeW4.getOldW4NumOfAllowances());
        statement.setInt(3, oneEmployeeW4.getOldW4FilingStatus());
        statement.setInt(4, oneEmployeeW4.getOldW4AdduitionWithHold());
        statement.setString(5, oneEmployeeW4.getNew2020W4FilingStatus().name());
        statement.setInt(6, ((oneEmployeeW4.isNew2020W4MultipleJobs() == true) ? 1 : 0));
        statement.setInt(7, oneEmployeeW4.getNew2020W4Step3Line3Dependents());
        statement.setInt(8, oneEmployeeW4.getNew2020W4Step4Line4aOtherIncome());
        statement.setInt(9, oneEmployeeW4.getNew2020W4Step4Line4bDeductions());
        statement.setInt(10, oneEmployeeW4.getNew2020W4Step4Line4cExtraWithHolding());
        statement.setInt(11, (oneEmployeeW4.isExempt() == true) ? 1 : 0);

        if (bAdd) {
            statement.setInt(12, oneEmployeeW4.getEmployee_id());
        }

        int rows = statement.executeUpdate();

        if (rows > 0) {
            System.out.println("A row has been inserted/updated");
        }

        statement.close();

        if (bAdd == false) {
            m_dbMapEmployeeW4Data.put(oneEmployeeW4.getEmployee_id(), oneEmployeeW4);
            m_dbMapEmployeeData.get(oneEmployeeW4.getEmployee_id()).setEmployeeW4(oneEmployeeW4);
        }

    }

    private void PayrollHistory() throws SQLException {
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();
        String sql = "SELECT * FROM companyinfo";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {

            String companyName = result.getString("companyName");
            String companyAddress = result.getString(2);
            String phoneNumber = result.getString(3);
            String federalEIN = result.getString(4);
            String stateID = result.getString(5);
            String payFrequency = result.getString(6);
            String adminUsername = result.getString(7);
            String password = result.getString(8);

            m_dbCompanyData.setCompanyName(companyName);
            m_dbCompanyData.setFederalEIN(federalEIN);
            m_dbCompanyData.setStateID(stateID);
            m_dbCompanyData.setPayrollFrequency(CompanyInfoModel.PayrollFrequency.valueOf(payFrequency));

        }

        statement.close();

        LoadCompanyPayrollRates();

    }

    public boolean CheckEmployeePaytExists(int employee_id, long startdate, long end_date) throws SQLException {

        boolean bPresent = false;
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();
        String sql = "SELECT COUNT(*) FROM payroll_history WHERE employee_id=" + Integer.toString(employee_id)
                + " AND (start_date BETWEEN " + Long.toString(startdate) + " AND " + Long.toString(startdate) + ")";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            if (rs.getInt("COUNT(*)") > 0) {
                bPresent = true;
            }

        }

        statement.close();
        return bPresent;
    }

    public void AddAllEmployeesPayOnePayPeriod(Vector<PayrollHistoryModel> vctEmployeePay) throws SQLException {

        for (var oneEmployeePay : vctEmployeePay) {
            AddOneEmployeePayOnePayPeriod(oneEmployeePay);
        }

    }

    public void AddOneEmployeePayOnePayPeriod(PayrollHistoryModel oneEmployeePay) throws SQLException {
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();

        String sql;

        sql = "INSERT INTO payroll_history(employee_id,first_name,last_name, pay_year,pay_month,pay_month_day, start_date,end_date,"
                + "hour,hourly_rate,employee_gross_wages, employee_401k,employee_state_wh,employee_federal_wh,"
                + "employee_medicare,employee_ss,employee_sdi,company_futa,company_medicare,company_ss)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, oneEmployeePay.getEmployee_id());
        statement.setString(2, oneEmployeePay.getFirst_name());
        statement.setString(3, oneEmployeePay.getLast_name());
        statement.setInt(4, oneEmployeePay.getPayYear());
        statement.setInt(5, oneEmployeePay.getPay_month());
        statement.setInt(6, oneEmployeePay.getPayMonthDay());
        statement.setLong(7, oneEmployeePay.getStart_date());
        statement.setLong(8, oneEmployeePay.getEnd_date());

        statement.setFloat(9, oneEmployeePay.getHour());
        statement.setFloat(10, oneEmployeePay.getHourly_rate());
        statement.setFloat(11, oneEmployeePay.getEmployee_gross_wages());
        statement.setFloat(12, oneEmployeePay.getEmployee_401k());
        statement.setFloat(13, oneEmployeePay.getEmployee_state_wh());
        statement.setFloat(14, oneEmployeePay.getEmployee_federal_wh());

        statement.setFloat(15, oneEmployeePay.getEmployee_medicare());
        statement.setFloat(16, oneEmployeePay.getEmployee_ss());
        statement.setFloat(17, oneEmployeePay.getEmployee_sdi());
        statement.setFloat(18, oneEmployeePay.getCompany_futa());
        statement.setFloat(19, oneEmployeePay.getCompany_medicare());
        statement.setFloat(20, oneEmployeePay.getCompany_ss());

        int rows = statement.executeUpdate();

        if (rows > 0) {
            System.out.println("A row has been inserted/updated");
        }

        statement.close();

    }

    public void LoadAllEmployeesPayrollWithFilter(Vector<PayrollHistoryModel> vctEmployeePay, String sql,
            String filenameWithPath) throws SQLException, IOException {

        vctEmployeePay.clear();
        boolean bGenReport = !filenameWithPath.isEmpty();

        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();
        //String sql = "SELECT * FROM payroll_history";
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(sql);

        // Open CSV file.
        BufferedWriter writer = null;
        CSVPrinter csvPrinter = null;

        if (bGenReport) {
            writer = Files.newBufferedWriter(Paths.get(filenameWithPath));

            // Add table headers to CSV file.
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(results.getMetaData()).
                    withQuoteMode(QuoteMode.ALL));
        }

        while (results.next()) {

            //PayrollHistoryModel onePayrollHistoryRow = new PayrollHistoryModel();
            PayrollHistoryModel oneEmployeePay = new PayrollHistoryModel();

            oneEmployeePay.setEmployee_id(results.getInt("employee_id"));
            oneEmployeePay.setFirst_name(results.getString("first_name"));
            oneEmployeePay.setLast_name(results.getString("last_name"));
            oneEmployeePay.setPayYear(results.getInt("pay_year"));
            oneEmployeePay.setPay_month(results.getInt("pay_month"));
            oneEmployeePay.setPayMonthDay((int) results.getLong("pay_month_day"));

            oneEmployeePay.setStart_date( results.getLong("start_date"));
            oneEmployeePay.setEnd_date(results.getLong("end_date"));

            oneEmployeePay.setHour(results.getFloat("hour"));
            oneEmployeePay.setHourly_rate(results.getFloat("hourly_rate"));
            oneEmployeePay.setEmployee_gross_wages(results.getFloat("employee_gross_wages"));
            oneEmployeePay.setEmployee_401k(results.getFloat("employee_401k"));
            oneEmployeePay.setEmployee_state_wh(results.getFloat("employee_state_wh"));
            oneEmployeePay.setEmployee_federal_wh(results.getFloat("employee_federal_wh"));

            oneEmployeePay.setEmployee_medicare(results.getFloat("employee_medicare"));
            oneEmployeePay.setEmployee_ss(results.getFloat("employee_ss"));
            oneEmployeePay.setEmployee_sdi(results.getFloat("employee_sdi"));
            oneEmployeePay.setCompany_futa(results.getFloat("company_futa"));
            oneEmployeePay.setCompany_medicare(results.getFloat("company_medicare"));
            oneEmployeePay.setCompany_ss(results.getFloat("company_ss"));

            if (bGenReport) {
                csvPrinter.printRecord(
                        oneEmployeePay.getEmployee_id(),
                        oneEmployeePay.getFirst_name(),
                        oneEmployeePay.getLast_name(),
                        oneEmployeePay.getPayYear(),
                        oneEmployeePay.getPay_month(),
                        Util.getDayOfMonthFromEpoch(oneEmployeePay.getPayMonthDay()),
                        Util.getYearMonthDayOfMonthFromEpoch(oneEmployeePay.getStart_date()),
                        Util.getYearMonthDayOfMonthFromEpoch(oneEmployeePay.getEnd_date()),
                        oneEmployeePay.getHour(),
                        oneEmployeePay.getHourly_rate(),
                        oneEmployeePay.getEmployee_gross_wages(),
                        oneEmployeePay.getEmployee_401k(),
                        oneEmployeePay.getEmployee_state_wh(),
                        oneEmployeePay.getEmployee_federal_wh(),
                        oneEmployeePay.getEmployee_medicare(),
                        oneEmployeePay.getCompany_ss(),
                        oneEmployeePay.getEmployee_sdi(),
                        oneEmployeePay.getCompany_futa(),
                        oneEmployeePay.getCompany_medicare(),
                        oneEmployeePay.getCompany_ss());
            }

            vctEmployeePay.add(oneEmployeePay);
        }

        if (bGenReport) {
            // Close CSV file.
            csvPrinter.flush();
            csvPrinter.close();
        }
        statement.close();

    }

    public YearToDateTotals GetYTDTotalForOneEmployee(int payYear, int employeeId) throws SQLException {

        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();
        String sql = "SELECT sum(employee_gross_wages), sum(employee_401k) FROM payroll_history WHERE pay_year = "
                + Integer.toString(payYear) + " AND employee_id = " + Integer.toString(employeeId);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        YearToDateTotals ytd = new YearToDateTotals();

        while (result.next()) {

            ytd.setGrossSalary(result.getInt(1));
            ytd.setYtd401K(result.getInt(2));
        }

        statement.close();

        return ytd;
    }

    public EmployeeCA_DE4Model LoadEmployeeCADE4Info(int employee_id) throws SQLException {

        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();
        String sql = "SELECT * FROM cade4 WHERE employee_id =" + employee_id;
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        EmployeeCA_DE4Model oneEmployeeDE4 = new EmployeeCA_DE4Model();

        while (result.next()) {

            int id = result.getInt("employee_id");
            int line1a = result.getInt("line1a_reg");
            int line1b = result.getInt("line1b_est");
            String filingStatus = result.getString("filing_status");
            int exempt = result.getInt("exempt");

            oneEmployeeDE4.setEmployee_id(id);
            oneEmployeeDE4.setLine1ARegularStdAllowance(line1a);
            oneEmployeeDE4.setLine1B_EstimatedDeduction(line1b);
            oneEmployeeDE4.setFilingStatus(EmployeeW4Model.FilingStatus.valueOf(filingStatus));

            oneEmployeeDE4.setExempt(exempt > 0);

        }

        statement.close();

        return oneEmployeeDE4;
    }

    public void AddOrUpdateOneEmployeeDE4(EmployeeCA_DE4Model oneEmployeeDE4, boolean bAdd) throws SQLException {
        Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();

        String sql;
        if (bAdd) {
            //employee_id is LAST so that most of the code will be same for update

            sql = "INSERT INTO cade4(line1a_reg,line1b_est, filing_status, exempt, employee_id) "
                    + " values (?,?,?,?,?)";

        } else {
            sql = "UPDATE cade4 SET line1a_reg=?,line1b_est=?, filing_status=?,exempt=? "
                    + "  WHERE employee_id =" + oneEmployeeDE4.getEmployee_id();

        }

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, (oneEmployeeDE4.getLine1ARegularStdAllowance()));
        statement.setInt(2, (oneEmployeeDE4.getLine1B_EstimatedDeduction()));
        statement.setString(3, oneEmployeeDE4.getFilingStatus().name());
        statement.setInt(4, (oneEmployeeDE4.isExempt() == true) ? 1 : 0);

        if (bAdd) {
            statement.setInt(5, oneEmployeeDE4.getEmployee_id());
        }

        int rows = statement.executeUpdate();

        if (rows > 0) {
            System.out.println("A row has been inserted/updated");
        }

        statement.close();

    }

}
