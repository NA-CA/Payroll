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
import com.neel.payroll.Model.OnePayPeriodAllEmpoyeesTimeSheets;
import com.neel.payroll.PayrollUI;
import com.neel.payroll.Model.TimeSheetOneEmployeeModel;
import com.neel.payroll.Util.Util;
import com.neel.payroll.Util.toast;
import com.toedter.calendar.JDateChooser;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Neel
 */
public class TimeSheetTabVM {

    static public void FillUI(PayrollUI ui) {

        JDateChooser startDate = ui.getTimesheetStartDate();
        if (startDate != null) {
            try {

                var dbVctTimeSheet = LoadSaveDatabase.getInstance().LoadEmptyPayPeriodTimeSheet().getOnePayPeriodAllEmployeeTimeSheets();

                long startdate = Util.getStartDateSinceEpoch(ui.getTimesheetStartDate().getDate());
                dbVctTimeSheet = LoadSaveDatabase.getInstance().LoadPayPeriodTimeSheet(startdate).getOnePayPeriodAllEmployeeTimeSheets();
                if (dbVctTimeSheet.size() == 0) {
                    dbVctTimeSheet = LoadSaveDatabase.getInstance().LoadEmptyPayPeriodTimeSheet().getOnePayPeriodAllEmployeeTimeSheets();
                }

                var table = ui.getTimesheetTable();
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                model.setRowCount(0);

                for (var oneEmployeeTimeSheet : dbVctTimeSheet) {
                    model.addRow(new Object[]{oneEmployeeTimeSheet.getEmployeeId(), oneEmployeeTimeSheet.getFirstname(),
                        oneEmployeeTimeSheet.getLastname(), oneEmployeeTimeSheet.getHours()});

                }

                var endDate = LoadSaveDatabase.getInstance().getdbCompanyData().GetPayrollEndDate(Util.getLocalDateFromDate(startDate.getDate()));
                ui.getLblTimesheetEndDate().setText("End Date: " + endDate.toString());

            } catch (Exception e) {
                String strErrorMsg = "Failed to get  employee timeshet"
                        + ". Error " + e.getMessage() + " cause " + e.getCause();
                JOptionPane.showMessageDialog(null, strErrorMsg);

            }
        }
    }

    static public void TimesheetAddOrUpdate(PayrollUI ui) {
        try {
            if (ui.getTimesheetStartDate().getDate() != null) {
                var table = ui.getTimesheetTable();

                DefaultTableModel model = (DefaultTableModel) table.getModel();

                OnePayPeriodAllEmpoyeesTimeSheets allEmpsTimeSheet = new OnePayPeriodAllEmpoyeesTimeSheets();
                long startdate = Util.getStartDateSinceEpoch(ui.getTimesheetStartDate().getDate());

                allEmpsTimeSheet.setStartDate(startdate);

                Vector<TimeSheetOneEmployeeModel> vctAllEmpTimeSheetOnePayPeriod = new Vector<TimeSheetOneEmployeeModel>();
                for (int row = 0; row < model.getRowCount(); row++) {
                    TimeSheetOneEmployeeModel oneEmpTimeSheet = new TimeSheetOneEmployeeModel();

                    var id = (int) model.getValueAt(row, 0);
                    oneEmpTimeSheet.setEmployeeId(id);

                    float hours = (float) model.getValueAt(row, 3);
                    oneEmpTimeSheet.setHours(hours);

                    vctAllEmpTimeSheetOnePayPeriod.add(oneEmpTimeSheet);

                }

                allEmpsTimeSheet.setOnePayPeriodAllEmployeeTimeSheets(vctAllEmpTimeSheetOnePayPeriod);

                LoadSaveDatabase.getInstance().AddOrUpdateOnePayrollPeriodTimesheets(allEmpsTimeSheet);

                toast.ShowToastMsg("Saved timesheet information", ui.getLocation().x + ui.getWidth() / 2,
                        ui.getLocation().y + ui.getHeight() / 2);
            }
        } catch (Exception e) {
            String strErrorMsg = "Failed to add or update employee "
                    + ". Error " + e.getMessage() + " cause " + e.getCause();
            JOptionPane.showMessageDialog(null, strErrorMsg);

        }

    }

}
