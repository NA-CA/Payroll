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

import com.neel.payroll.Model.EmployeeInfoModel;
import com.neel.payroll.DB.LoadSaveDatabase;
import com.neel.payroll.PayrollUI;
import com.neel.payroll.Util.toast;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Neel
 */
public class EmployeeTabVM {

    static public void FillUI(PayrollUI ui) {

        var dbVctEmployeeInfo = LoadSaveDatabase.getInstance().getdbEmployeeData();
        var table = ui.getjTableEmployeeList();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.setRowCount(0);

        dbVctEmployeeInfo.forEach((key, oneEmployee) -> {
            model.addRow(new Object[]{oneEmployee.getId(), oneEmployee.getFirstname(), oneEmployee.getLastname()});

        });
        /*
        for (var oneEmployee : dbVctEmployeeInfo) {
            model.addRow(new Object[]{oneEmployee.getId(), oneEmployee.getFirstname(), oneEmployee.getLastname()});
        }
         */

        model.addRow(new Object[]{-1, "Row to select to add new employee", ""});

        table.setRowSelectionInterval(0, 0);

        int J = 0;
    }

    static public void EmployeeSelChanged(PayrollUI ui, int row) {
        var table = ui.getjTableEmployeeList();
        int employeeId = (int) table.getValueAt(row, 0);

        var tfFirstname = ui.getTfEmployeeFirstName();
        var tfLastname = ui.getTfEmployeeLastName();
        //var tfSSN = ui.getTfEmployeeSSN();
        var tfSalaryPerHour = ui.getTfSalary();
        var spin401KRate = ui.getspinemployee401KRate();
        var ckBoxAge50Plus = ui.getCkemployee50PlusAge();

        String FirstName = "";
        String LastName = "";
        String SSN = "";
        String salary = "";
        float def401KRate = 0.0F;
        boolean age50Plus = false;

        String btnTextAddOrUpdate = "Add Employee";
        if (employeeId > 0) {

            btnTextAddOrUpdate = "Update Employee";
            int vctEmployeeIndex = row;

            var dbVctEmployeeInfo = LoadSaveDatabase.getInstance().getdbEmployeeData();
            var employee = dbVctEmployeeInfo.get(employeeId);
            FirstName = employee.getFirstname();

            LastName = employee.getLastname();
            SSN = Integer.toString(employee.getSsn());
            salary = Float.toString(employee.getSalary_per_hour());
            def401KRate = employee.getEmployee401KDefRate();
            age50Plus = employee.isAge50Plus();
        }

        ui.getEmployeeAddUpdateBtn().setText(btnTextAddOrUpdate);
        tfFirstname.setText(FirstName);
        tfLastname.setText(LastName);
        //tfSSN.setText(SSN);
        tfSalaryPerHour.setText(salary);
        spin401KRate.getModel().setValue(def401KRate);
        ckBoxAge50Plus.setSelected(age50Plus);

        EmployeeW4TabVM.FillUI(ui, row);

    }

    static public void EmployeeAddOrUpdate(PayrollUI ui) {
        try {
            var table = ui.getjTableEmployeeList();
            int row = table.getSelectedRow();
            int employeeId = (int) table.getValueAt(row, 0);

            EmployeeInfoModel employee = new EmployeeInfoModel();
            employee.setId(employeeId);
            employee.setFirstname(ui.getTfEmployeeFirstName().getText());
            employee.setLastname(ui.getTfEmployeeLastName().getText());
            //employee.setSsn(Integer.parseInt(ui.getTfEmployeeSSN().getText()));
            employee.setSalary_per_hour(Float.valueOf(ui.getTfSalary().getText()).floatValue());
            employee.setEmployee401KDefRate(Float.valueOf(ui.getspinemployee401KRate().getModel().getValue().toString()));
            employee.setAge50Plus(ui.getCkemployee50PlusAge().isSelected());

            LoadSaveDatabase.getInstance().AddOrUpdateOneEmployeeInfo(employee, employeeId <= 0);

            toast.ShowToastMsg("Added/Updated employee information", ui.getLocation().x + ui.getWidth() / 2,
            ui.getLocation().y + ui.getHeight() / 2);
            
            ui.ClearEmployeeTable();

        } catch (Exception e) {
            String strErrorMsg = "Failed to add or update employee "
                    + ". Error " + e.getMessage() + " cause " + e.getCause();
            JOptionPane.showMessageDialog(null, strErrorMsg);

        }

    }

}
