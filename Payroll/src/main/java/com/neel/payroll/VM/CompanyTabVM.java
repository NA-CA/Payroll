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

import com.neel.payroll.Model.CompanyEmployeePayrollRatesModel;
import com.neel.payroll.Model.CompanyInfoModel;
import com.neel.payroll.DB.LoadSaveDatabase;
import com.neel.payroll.PayrollUI;
import com.neel.payroll.Util.toast;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Neel
 */
public class CompanyTabVM {

    CompanyTabVM() {

    }

    static public void FillUI(PayrollUI ui) {
        var dbCompanyInfo = LoadSaveDatabase.getInstance().getdbCompanyData();
        ui.getTfCompanyName().setText(dbCompanyInfo.getCompanyName());
        //ui.getCompanyEIN().setText(dbCompanyInfo.getFederalEIN());
        //ui.getCompanyStateID().setText(dbCompanyInfo.getStateID());
        ui.getCompanyPayrollFrequency().setSelectedItem(dbCompanyInfo.getPayrollFrequency().toString());

        var dbPayrollRates = dbCompanyInfo.getYearToPayrollTax();

        DefaultTableModel model = (DefaultTableModel) ui.getCompanyRateTable().getModel();

        //model.setRowCount(0);
        int rowIndex = 0;
        //dbPayrollRates.forEach((key, oneYearTaxRate) -> {
        for (Map.Entry<Integer, CompanyEmployeePayrollRatesModel> entry : dbPayrollRates.entrySet()) {
            CompanyEmployeePayrollRatesModel oneYearTaxRate = entry.getValue();

            model.setValueAt(oneYearTaxRate.getYear(), rowIndex, 0);
            model.setValueAt(oneYearTaxRate.getUnemploymentFUTARatePercent(), rowIndex, 1);
            model.setValueAt(oneYearTaxRate.getSdi(), rowIndex, 2);
            //model.addRow(new Object[]{oneYearTaxRate.getYear(), oneYearTaxRate.getUnemploymentFUTARatePercent(),
            // oneYearTaxRate.getSdi()});
            rowIndex++;
        }
        //});
    }

    static public void SaveCompanyInfo(PayrollUI ui) {

        try {

            CompanyInfoModel dbCompanyInfo = new CompanyInfoModel();

            dbCompanyInfo.setCompanyName(ui.getTfCompanyName().getText());
            //dbCompanyInfo.setFederalEIN(ui.getCompanyEIN().getText());
            //dbCompanyInfo.setStateID(ui.getCompanyStateID().getText());
            dbCompanyInfo.setPayrollFrequency(CompanyInfoModel.PayrollFrequency.valueOf(ui.getCompanyPayrollFrequency().getSelectedItem().toString()));

            DefaultTableModel model = (DefaultTableModel) ui.getCompanyRateTable().getModel();

            for (var oneRow : model.getDataVector()) {

                if (oneRow.elementAt(0) != null) {

                    String yearString = oneRow.elementAt(0).toString();

                    if (yearString.isBlank() == false) {
                        CompanyEmployeePayrollRatesModel oneYearTaxRate = new CompanyEmployeePayrollRatesModel();
                        int year = Integer.parseInt(oneRow.elementAt(0).toString());

                        if (dbCompanyInfo.getYearToPayrollTax().containsKey(year) == true) {
                            throw new Exception("Duplicate year entered " + yearString);
                        }

                        oneYearTaxRate.setYear(year);
                        oneYearTaxRate.setUnemploymentFUTARatePercent(Float.parseFloat(oneRow.elementAt(1).toString()));
                        oneYearTaxRate.setSdi(Float.parseFloat(oneRow.elementAt(2).toString()));

                        //oneRow.size() == 3
                        dbCompanyInfo.getYearToPayrollTax().put(year, oneYearTaxRate);
                    }
                }
            }

            LoadSaveDatabase.getInstance().SaveCompanyInfo(dbCompanyInfo);
            toast.ShowToastMsg("Saved company information", ui.getLocation().x + ui.getWidth() / 2,
                    ui.getLocation().y + ui.getHeight() / 2);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }
}
