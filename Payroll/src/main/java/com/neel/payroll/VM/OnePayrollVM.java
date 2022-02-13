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

package com.neel.payroll.VM;

import com.neel.payroll.DB.LoadSaveDatabase;
import com.neel.payroll.Model.CompanyInfoModel;
import com.neel.payroll.Model.EmployeeCA_DE4Model;
import com.neel.payroll.Model.EmployeeInfoModel;
import com.neel.payroll.Model.EmployeeW4Model;
import com.neel.payroll.Model.EmployeeW4Model.FilingStatus;
import com.neel.payroll.Model.PayrollHistoryModel;
import com.neel.payroll.PayrollUI;
import com.neel.payroll.Util.Util;
import com.neel.payroll.Util.toast;
import com.neel.payrolllibrary.ComputeAllPayrollTaxes;
import com.neel.payrolllibrary.EmployeePayrollInfo;
import com.neel.payrolllibrary.FederalFilingStatusEnum;
import com.neel.payrolllibrary.PayrollInfo;
import com.neel.payrolllibrary.PayrollPeriodEnum;
import com.neel.payrolllibrary.ca.CADE4;
import com.neel.payrolllibrary.ca.CAFilingStatusEnum;
import com.neel.payrolllibrary.ca.CAPayrollPeriodEnum;
import com.neel.payrolllibrary.fed.EmployeeW4;
import java.sql.SQLException;

import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Neel
 */
public class OnePayrollVM {

    public static void PayEmployeesOnePayPeriod(PayrollUI ui) {
        try {

            var startDate = ui.getPayTimesheetStartDate();

            long startdateTimeSheet = Util.getStartDateSinceEpoch(startDate.getDate());
            var endDate = LoadSaveDatabase.getInstance().getdbCompanyData().GetPayrollEndDate(Util.getLocalDateFromDate(startDate.getDate()));
            long enddateTimeSheet = Util.getStartDateSinceEpoch(Util.getDateFromLocalDate(endDate));

            var mapEmployees = LoadSaveDatabase.getInstance().getdbEmployeeData();
            mapEmployees.forEach((key, oneEmployee) -> {

                try {

                    if (LoadSaveDatabase.getInstance().CheckEmployeePaytExists(oneEmployee.getId(), startdateTimeSheet, enddateTimeSheet) == false) {                   //Execute run payroll computation for one employee for one pay check

                        ComputeAndSaveOneEmployeeOnePayPeriodPayroll(ui, oneEmployee);

                    } else {
                        //@@@@@@@@@@@@@@@@@ Employee already paid
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());

                }
            });

            toast.ShowToastMsg("Saved payroll information", ui.getLocation().x + ui.getWidth() / 2,
                    ui.getLocation().y + ui.getHeight() / 2);

            FillOnePayrollPayDateTable(ui);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }

    public static void FillOnePayrollPayDateTable(PayrollUI ui) {
        try {

            var payDate = ui.getPayDate();
            var startDate = ui.getPayTimesheetStartDate();

            int payYear = Util.getLocalDateFromDate(payDate.getDate()).getYear();
            int payMonth = Util.getLocalDateFromDate(payDate.getDate()).getMonthValue();
            int payDayOfMonth = Util.getLocalDateFromDate(payDate.getDate()).getDayOfMonth();
            long startdateTimeSheet = Util.getStartDateSinceEpoch(startDate.getDate());

            var endDate = LoadSaveDatabase.getInstance().getdbCompanyData().GetPayrollEndDate(Util.getLocalDateFromDate(startDate.getDate()));
            ui.getLabelPayTimesheetEndDate().setText(endDate.toString());

            Vector<PayrollHistoryModel> vctEmployeePay = new Vector<PayrollHistoryModel>();

            String sql = "SELECT * FROM payroll_history WHERE pay_year = " + Integer.toString(payYear) + " AND pay_month = "
                    + Integer.toString(payMonth) + " AND pay_month_day = " + Integer.toString(payDayOfMonth);

            //HACKED sql = "SELECT * FROM payroll_history WHERE pay_year =0 AND pay_month = 0 AND pay_month_day = 0";
            LoadSaveDatabase.getInstance().LoadAllEmployeesPayrollWithFilter(vctEmployeePay, sql, new String());

            var table = ui.getJtableOnePayPeriod();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            for (var onePayRow : vctEmployeePay) {
                float netPay = onePayRow.getEmployee_gross_wages() - onePayRow.getEmployee_federal_wh() -
                 onePayRow.getEmployee_state_wh() -  onePayRow.getEmployee_401k() -
                    onePayRow.getEmployee_sdi() - onePayRow.getEmployee_ss() - onePayRow.getEmployee_medicare();
                    
                model.addRow(new Object[]{onePayRow.getEmployee_id(),
                    onePayRow.getFirst_name(), onePayRow.getLast_name(), onePayRow.getHour(),
                    onePayRow.getEmployee_gross_wages(), onePayRow.getEmployee_federal_wh(),
                    onePayRow.getEmployee_state_wh(), onePayRow.getEmployee_401k(),
                    onePayRow.getEmployee_sdi(), onePayRow.getEmployee_ss(),onePayRow.getEmployee_medicare(),
                    netPay});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }

    //getJtableOnePayPeriod
    private static void ComputePayroll(PayrollInfo payrollInfo) throws Exception {

        ComputeAllPayrollTaxes computeAllPayrollTaxes = new ComputeAllPayrollTaxes(payrollInfo);
        computeAllPayrollTaxes.computeAllTaxes();
    }

    private static PayrollInfo getPayrollInfo(int payYear, double grossWagesCurrPayPeriod, EmployeeInfoModel employee,
            CompanyInfoModel company) throws SQLException {

        var ytd = LoadSaveDatabase.getInstance().GetYTDTotalForOneEmployee(payYear, employee.getId());
        var cade4FromUI = LoadSaveDatabase.getInstance().LoadEmployeeCADE4Info(employee.getId());
        CADE4 cade4 = new CADE4(getCAPayPeriod(company), cade4FromUI.getLine1ARegularStdAllowance(), cade4FromUI.getLine1B_EstimatedDeduction(),
                getCAFilingStatus(cade4FromUI), cade4FromUI.isExempt());

        double grossWagesYTD = ytd.getGrossSalary();
        double employee401KYTD = ytd.getYtd401K();
        double employee401KDeferallRateInDecimal = employee.getEmployee401KDefRate() / 100.0;
        boolean fiftyPlus = employee.isAge50Plus();

        double sdiRateInDecimal = company.getYearToPayrollTax().get(payYear).getSdi() / 100.0;
        EmployeeW4 employeeW4 = GetEmployeeW4(employee.getEmployeeW4());

        EmployeePayrollInfo employeePayInfo = new EmployeePayrollInfo(grossWagesYTD,
                grossWagesCurrPayPeriod, employee401KYTD, employee401KDeferallRateInDecimal, fiftyPlus,
                sdiRateInDecimal, employeeW4, cade4);
        PayrollInfo payrollInfo = new PayrollInfo(payYear, Util.getAppDataDir(),
                getPayPeriodEnum(company), GetCompanyPayrollRate(payYear), employeePayInfo);

        return payrollInfo;

    }

    private static com.neel.payrolllibrary.CompanyPayrollRate GetCompanyPayrollRate(int payYear) {
        var companyTaxInfo = LoadSaveDatabase.getInstance().getdbCompanyData().getYearToPayrollTax();
        return new com.neel.payrolllibrary.CompanyPayrollRate(companyTaxInfo.get(payYear).getUnemploymentFUTARatePercent() / 100.0);
    }

    private static FederalFilingStatusEnum getFederalFilingStatusEnum(FilingStatus new2020W4FilingStatus) {
        FederalFilingStatusEnum libFederalFilingStatusEnum = FederalFilingStatusEnum.SINGLE_OR_MARRIEDFILINGSEPERATELY;
        switch (new2020W4FilingStatus) {
            case M:
                libFederalFilingStatusEnum = FederalFilingStatusEnum.MARRIEDFILINGJOINTLY_OR_QUALIFYINGWIDOWER;
                break;
            case H:
                libFederalFilingStatusEnum = FederalFilingStatusEnum.HEADOFHOUSEHOLD;
                break;
        }
        return libFederalFilingStatusEnum;
    }

    private static CAFilingStatusEnum getCAFilingStatus(EmployeeCA_DE4Model de4) {
        CAFilingStatusEnum convertedEnum = CAFilingStatusEnum.SINGLE;
        switch (de4.getFilingStatus()) {
            case M:
                convertedEnum = CAFilingStatusEnum.MARRIED;
                break;
            case H:
                convertedEnum = CAFilingStatusEnum.HEADOFHOUSEHOLD;
                break;
        }

        return convertedEnum;
    }

    private static CAPayrollPeriodEnum getCAPayPeriod(CompanyInfoModel company) {
        CAPayrollPeriodEnum payPeriod = CAPayrollPeriodEnum.WEEKLY;

        switch (company.getPayrollFrequency()) {
            case Daily:
                payPeriod = CAPayrollPeriodEnum.DAILY_MISC;
                break;
            case Weekly:
                payPeriod = CAPayrollPeriodEnum.WEEKLY;
                break;
            case BiWeekly:
                payPeriod = CAPayrollPeriodEnum.BI_WEEKLY;
                break;

            case SemiMonthly:
                payPeriod = CAPayrollPeriodEnum.SEMI_MONTHLY;
                break;
            case Monthly:
                payPeriod = CAPayrollPeriodEnum.MONTHLY;
                break;
            case Quarterly:
                payPeriod = CAPayrollPeriodEnum.QUARTERLY;
                break;

            case Annually:
                payPeriod = CAPayrollPeriodEnum.ANNUAL;
                break;

        }
        return payPeriod;
    }

    private static PayrollPeriodEnum getPayPeriodEnum(CompanyInfoModel company) {
        PayrollPeriodEnum payPeriod = PayrollPeriodEnum.BI_WEEKLY;
        switch (company.getPayrollFrequency()) {
            case Daily:
                payPeriod = PayrollPeriodEnum.DAILY_MISC;
                break;
            case Weekly:
                payPeriod = PayrollPeriodEnum.WEEKLY;
                break;
            case BiWeekly:
                payPeriod = PayrollPeriodEnum.BI_WEEKLY;
                break;

            case SemiMonthly:
                payPeriod = PayrollPeriodEnum.SEMI_MONTHLY;
                break;
            case Monthly:
                payPeriod = PayrollPeriodEnum.MONTHLY;
                break;
            case Quarterly:
                payPeriod = PayrollPeriodEnum.QUARTERLY;
                break;

            case Annually:
                payPeriod = PayrollPeriodEnum.ANNUAL;
                break;

        }
        return payPeriod;
    }

    private static EmployeeW4 GetEmployeeW4(EmployeeW4Model w4) {
        boolean bSubmittedAnyYearW4 = true; //@@@@@@@@@@@@@@@@@@@ In future ask user if user submitted any W4 or not.

        EmployeeW4 employeeW4 = new EmployeeW4(getFederalFilingStatusEnum(w4.getNew2020W4FilingStatus()),
                bSubmittedAnyYearW4,
                w4.getOldW4FilingStatus(), w4.getOldW4NumOfAllowances(), w4.getOldW4AdduitionWithHold(),
                w4.isNew2020W4MultipleJobs(), w4.getNew2020W4Step3Line3Dependents(),
                w4.getNew2020W4Step4Line4aOtherIncome(), w4.getNew2020W4Step4Line4bDeductions(),
                w4.getNew2020W4Step4Line4cExtraWithHolding(), w4.isSubmitted2020PlusW4(), w4.isExempt());

        return employeeW4;
    }

    private static void ComputeAndSaveOneEmployeeOnePayPeriodPayroll(PayrollUI ui, EmployeeInfoModel employee) throws SQLException, Exception {

        var payDate = ui.getPayDate();
        var startDate = ui.getPayTimesheetStartDate();

        int payYear = Util.getLocalDateFromDate(payDate.getDate()).getYear();
        int payMonth = Util.getLocalDateFromDate(payDate.getDate()).getMonthValue();
        int payDayOfMonth = Util.getLocalDateFromDate(payDate.getDate()).getDayOfMonth();
        long startdateTimeSheet = Util.getStartDateSinceEpoch(startDate.getDate());
        var endDate = LoadSaveDatabase.getInstance().getdbCompanyData().GetPayrollEndDate(Util.getLocalDateFromDate(startDate.getDate()));
        long enddateTimeSheet = Util.getStartDateSinceEpoch(Util.getDateFromLocalDate(endDate));

        var employeeTimesheet = LoadSaveDatabase.getInstance().LoadOnePayPeriodTimeSheetOneEmployee(startdateTimeSheet, employee.getId());

        var company = LoadSaveDatabase.getInstance().getdbCompanyData();

        float hours = employeeTimesheet.getHours();

        if (employeeTimesheet.getEmployeeId() == employee.getId()) {
            double grossWagesCurrPayPeriod = hours * employee.getSalary_per_hour();

            PayrollInfo payInfo = getPayrollInfo(payYear, grossWagesCurrPayPeriod, employee, company);
            ComputePayroll(payInfo);

            var oneEmployeePay = new PayrollHistoryModel();

            oneEmployeePay.setEmployee_id(employee.getId());
            oneEmployeePay.setFirst_name(employee.getFirstname());
            oneEmployeePay.setLast_name(employee.getLastname());
            oneEmployeePay.setPayYear(payInfo.getYear());
            oneEmployeePay.setPay_month(payMonth);
            oneEmployeePay.setPayMonthDay(payDayOfMonth);
            oneEmployeePay.setStart_date((int) startdateTimeSheet);
            oneEmployeePay.setEnd_date(enddateTimeSheet);
            oneEmployeePay.setHour(hours);
            oneEmployeePay.setHourly_rate(employee.getSalary_per_hour());
            oneEmployeePay.setEmployee_gross_wages((float) grossWagesCurrPayPeriod);

            FillOneEmployeePayStubOnePayPeriod(oneEmployeePay, payInfo);

            LoadSaveDatabase.getInstance().AddOneEmployeePayOnePayPeriod(oneEmployeePay);

        }

    }

    private static void FillOneEmployeePayStubOnePayPeriod(PayrollHistoryModel oneEmployeePay, PayrollInfo payInfo) {
        oneEmployeePay.setEmployee_401k((float) payInfo.getEmployeePayrollTax().getEmployee_401k());
        oneEmployeePay.setEmployee_state_wh((float) payInfo.getEmployeePayrollTax().getState_wh());
        oneEmployeePay.setEmployee_federal_wh((float) payInfo.getEmployeePayrollTax().getFederal_wh());

        oneEmployeePay.setEmployee_medicare((float) payInfo.getEmployeePayrollTax().getMedicare());
        oneEmployeePay.setEmployee_ss((float) payInfo.getEmployeePayrollTax().getSs());
        oneEmployeePay.setEmployee_sdi((float) payInfo.getEmployeePayrollTax().getSdi());
        oneEmployeePay.setCompany_futa((float) payInfo.getCompanyPayrollTax().getFutaTax());
        oneEmployeePay.setCompany_medicare((float) payInfo.getCompanyPayrollTax().getMedicareTax());
        oneEmployeePay.setCompany_ss((float) payInfo.getCompanyPayrollTax().getSsTax());

    }
}
