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

import com.neel.payroll.CA_De4_Dlg;
import com.neel.payroll.DB.LoadSaveDatabase;
import com.neel.payroll.Model.EmployeeCA_DE4Model;
import com.neel.payroll.Model.EmployeeW4Model;
import com.neel.payroll.PayrollUI;
import com.neel.payroll.Util.toast;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Neel
 */
public class EmployeeDE4TabVM {

    static public void FillUI(CA_De4_Dlg de4UI, int employeeId) {

        try {

            if (employeeId > 0) {
                var employeeDE4 = LoadSaveDatabase.getInstance().LoadEmployeeCADE4Info(employeeId);

                if (employeeDE4.getEmployee_id() == employeeId) {

                    de4UI.getChkCA_DE4_Exempt().setSelected(employeeDE4.isExempt());
                    de4UI.getCaDE4_Line1A_RegAllowance().getModel().setValue(employeeDE4.getLine1ARegularStdAllowance());
                    de4UI.getCaDE4_Line1B_EstAllowance().getModel().setValue(employeeDE4.getLine1B_EstimatedDeduction());
                    de4UI.getCaDE4FilingStatus().setSelectedItem(employeeDE4.getFilingStatus().toString());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }

    static public void SaveDE4(CA_De4_Dlg de4UI, int employeeId) {

        try {

            if (employeeId > 0) {
                var employeeDE4 = new EmployeeCA_DE4Model();

                employeeDE4.setEmployee_id(employeeId);
                employeeDE4.setExempt(de4UI.getChkCA_DE4_Exempt().isSelected());
                employeeDE4.setLine1ARegularStdAllowance(Integer.valueOf(de4UI.getCaDE4_Line1A_RegAllowance().getModel().getValue().toString()));
                employeeDE4.setLine1B_EstimatedDeduction(Integer.valueOf(de4UI.getCaDE4_Line1B_EstAllowance().getModel().getValue().toString()));
                var filingStatus = EmployeeW4Model.FilingStatus.valueOf(de4UI.getCaDE4FilingStatus().getSelectedItem().toString());
                employeeDE4.setFilingStatus(filingStatus);

                LoadSaveDatabase.getInstance().AddOrUpdateOneEmployeeDE4(employeeDE4, false);
                
                JOptionPane.showMessageDialog(null, "Saved employee DE4 information");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }
}
