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
import com.neel.payroll.Model.PayrollHistoryModel;
import com.neel.payroll.PayrollUI;
import com.neel.payroll.Util.Util;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Neel
 */
public class GenerateReportVM {

    public static void GeneratePayrollReport(PayrollUI ui) {
        String strFilename = "";
        try {
            int payYear = ui.getReportYearChooser().getYear();
            strFilename = ui.getReportFilename().getText();

            Vector<PayrollHistoryModel> vctEmployeePay = new Vector<PayrollHistoryModel>();

            String sql = "SELECT * FROM payroll_history WHERE pay_year=" + Integer.toString(payYear);
            String filenameWithPath = Util.getAppReportsDir() + "/" + strFilename;
            LoadSaveDatabase.getInstance().LoadAllEmployeesPayrollWithFilter(vctEmployeePay, sql,
                    filenameWithPath);

            JOptionPane.showMessageDialog(null, "File generated in " + filenameWithPath);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report and write to " + strFilename + " Cause "
                    + e.getMessage());

        }

    }

}
