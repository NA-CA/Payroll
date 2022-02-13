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

import com.neel.payroll.Model.EmployeeW4Model;
import com.neel.payroll.DB.LoadSaveDatabase;
import com.neel.payroll.PayrollUI;
import com.neel.payroll.Util.toast;
import javax.swing.JOptionPane;

/**
 *
 * @author Neel
 */
public class EmployeeW4TabVM {

    static public void FillUI(PayrollUI ui, int employeeRow) {

        //int employeeId = (int) ui.getjTableEmployeeList().getValueAt(employeeRow, 0);
        int employeeId = ui.GetEmployeeId();
        if (employeeId > 0) {
            var employeeW4 = LoadSaveDatabase.getInstance().getdbEmployeeData().get(employeeId).getEmployeeW4();

            ui.getW4Exempt().setSelected(employeeW4.isExempt());

            ui.getW4Submitted2020PlusW4().setSelected(employeeW4.isSubmitted2020PlusW4());

            if (employeeW4.isSubmitted2020PlusW4()) {
                var table = ui.getW4New2020W4FormTable();
                var model = table.getModel();

                model.setValueAt(employeeW4.getNew2020W4FilingStatus(), 0, 1);
                model.setValueAt(employeeW4.isNew2020W4MultipleJobs(), 1, 1);
                model.setValueAt(employeeW4.getNew2020W4Step3Line3Dependents(), 2, 1);
                model.setValueAt(employeeW4.getNew2020W4Step4Line4aOtherIncome(), 3, 1);
                model.setValueAt(employeeW4.getNew2020W4Step4Line4bDeductions(), 4, 1);
                model.setValueAt(employeeW4.getNew2020W4Step4Line4cExtraWithHolding(), 5, 1);
            } else {
                //Using 2019 and older
                ui.getW4EmployeeOldW4Allowance().getModel().setValue(employeeW4.getOldW4NumOfAllowances());
                ui.getw4OldAdditionalAmount().setText(Integer.toString(employeeW4.getOldW4AdduitionWithHold()));
                ui.getOldW4FilingStatus().setSelectedIndex(employeeW4.getOldW4FilingStatus());
            }

        }
    }

    static public void SaveEmployeeW4(PayrollUI ui) {

        try {

            int employeeId = ui.GetEmployeeId();

            if (employeeId > 0) {
                EmployeeW4Model employeeW4 = new EmployeeW4Model();

                employeeW4.setEmployee_id(employeeId);
                employeeW4.setExempt(ui.getW4Exempt().isSelected());
                employeeW4.setSubmitted2020PlusW4(ui.getW4Submitted2020PlusW4().isSelected());

                if (employeeW4.isSubmitted2020PlusW4()) {

                    var table = ui.getW4New2020W4FormTable();
                    var model = table.getModel();

                    var filingStatus = EmployeeW4Model.FilingStatus.valueOf((model.getValueAt(0, 1)).toString());
                    employeeW4.setNew2020W4FilingStatus(filingStatus);

                    employeeW4.setNew2020W4MultipleJobs(Boolean.valueOf(model.getValueAt(1, 1).toString()));
                    employeeW4.setNew2020W4Step3Line3Dependents(Integer.valueOf(model.getValueAt(2, 1).toString()));
                    employeeW4.setNew2020W4Step4Line4aOtherIncome(Integer.valueOf(model.getValueAt(3, 1).toString()));
                    employeeW4.setNew2020W4Step4Line4bDeductions(Integer.valueOf(model.getValueAt(4, 1).toString()));
                    employeeW4.setNew2020W4Step4Line4cExtraWithHolding(Integer.valueOf(model.getValueAt(5, 1).toString()));

                } else {
                    //Using 2019 and older

                    employeeW4.setOldW4NumOfAllowances(Integer.valueOf(ui.getW4EmployeeOldW4Allowance().getModel().getValue().toString()));
                    employeeW4.setOldW4AdduitionWithHold(Integer.valueOf(ui.getw4OldAdditionalAmount().getText()));

                    var oldFilingStatus = ui.getOldW4FilingStatus().getSelectedIndex();
                    employeeW4.setOldW4FilingStatus(oldFilingStatus);

                }

                //W4 id added when employee is added this is only called for update
                LoadSaveDatabase.getInstance().AddOrUpdateOneEmployeeW4(employeeW4, false);
                
                toast.ShowToastMsg("Added/Updated employee W4", ui.getLocation().x + ui.getWidth() / 2,
                        ui.getLocation().y + ui.getHeight() / 2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }
}
