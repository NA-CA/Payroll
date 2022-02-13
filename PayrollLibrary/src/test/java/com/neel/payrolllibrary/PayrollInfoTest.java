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

package com.neel.payrolllibrary;

import com.neel.payrolllibrary.ca.CADE4;
import com.neel.payrolllibrary.ca.CAPayrollPeriodEnum;
import com.neel.payrolllibrary.fed.EmployeeW4;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayrollInfoTest
{
    @Test
   void checkPayrollInfo() throws Exception
    {

        CompanyPayrollRate companyPayrollRate = new CompanyPayrollRate(0.045);
        EmployeeW4 employeeW4 = new EmployeeW4(FederalFilingStatusEnum.MARRIEDFILINGJOINTLY_OR_QUALIFYINGWIDOWER, true,
                0, 0, 0,  false, 0,
                0, 0, 0,  true, false);


        CADE4 cade4 = new CADE4(CAPayrollPeriodEnum.BI_WEEKLY, 0, 0, com.neel.payrolllibrary.ca.CAFilingStatusEnum.MARRIED, false);

        EmployeePayrollInfo employeePayrollInfo = new EmployeePayrollInfo(0, 2670,
                0, 0, false, 0.05, employeeW4, cade4);
        PayrollInfo payrollInfo = new PayrollInfo(2022, PayrollLibUnitTestUtil.getDataPath(), PayrollPeriodEnum.BI_WEEKLY, companyPayrollRate, employeePayrollInfo);


        ComputeAllPayrollTaxes computeAllPayrollTaxes = new ComputeAllPayrollTaxes(payrollInfo);
        computeAllPayrollTaxes.computeAllTaxes();
        assertTrue(payrollInfo.getEmployeePayrollTax().getFederal_wh() >= 184 && payrollInfo.getEmployeePayrollTax().getFederal_wh() <= 186, "new w4 standard HH");
        assertTrue(payrollInfo.getEmployeePayrollTax().getEmployee_401k() >= 0 && payrollInfo.getEmployeePayrollTax().getEmployee_401k() <= 1);
        assertTrue(payrollInfo.getEmployeePayrollTax().getSs()>= 165 && payrollInfo.getEmployeePayrollTax().getSs()<= 166);
        assertTrue(payrollInfo.getEmployeePayrollTax().getMedicare() >= 38 && payrollInfo.getEmployeePayrollTax().getMedicare() <= 39);
        assertTrue(payrollInfo.getEmployeePayrollTax().getSdi() >= 133 && payrollInfo.getEmployeePayrollTax().getSdi() <= 134);
        assertTrue(payrollInfo.getEmployeePayrollTax().getState_wh() >= 64 && payrollInfo.getEmployeePayrollTax().getState_wh() <= 67);


        assertTrue(payrollInfo.getCompanyPayrollTax().getFutaTax() >= 120 && payrollInfo.getCompanyPayrollTax().getFutaTax() <= 121);
        assertTrue(payrollInfo.getCompanyPayrollTax().getMedicareTax() >= 38 && payrollInfo.getCompanyPayrollTax().getMedicareTax() <= 39);
        assertTrue(payrollInfo.getCompanyPayrollTax().getSsTax() >= 165 && payrollInfo.getCompanyPayrollTax().getSsTax() <= 166);


    }

    @Test
    void checkPayrollInfo2() throws Exception
    {

        CompanyPayrollRate companyPayrollRate = new CompanyPayrollRate(0.045);
        EmployeeW4 employeeW4 = new EmployeeW4(FederalFilingStatusEnum.SINGLE_OR_MARRIEDFILINGSEPERATELY, true,
                0, 0, 0,  false, 0,
                0, 0, 0,  true, false);


        CADE4 cade4 = new CADE4(CAPayrollPeriodEnum.BI_WEEKLY, 0, 0, com.neel.payrolllibrary.ca.CAFilingStatusEnum.MARRIED, false);

        EmployeePayrollInfo employeePayrollInfo = new EmployeePayrollInfo(100000, 200000,
                10000, 0.10, false, 0.05, employeeW4, cade4);
        PayrollInfo payrollInfo = new PayrollInfo(2022, PayrollLibUnitTestUtil.getDataPath(), PayrollPeriodEnum.BI_WEEKLY, companyPayrollRate, employeePayrollInfo);


        ComputeAllPayrollTaxes computeAllPayrollTaxes = new ComputeAllPayrollTaxes(payrollInfo);
        computeAllPayrollTaxes.computeAllTaxes();
       // assertTrue(payrollInfo.getEmployeePayrollTax().getFederal_wh() >= 54610 && payrollInfo.getEmployeePayrollTax().getFederal_wh() <= 54611, "new w4 standard HH");


        assertTrue(payrollInfo.getEmployeePayrollTax().getEmployee_401k() >= 10500 && payrollInfo.getEmployeePayrollTax().getEmployee_401k() <= 10500);
        assertTrue(payrollInfo.getEmployeePayrollTax().getSs()>= 2914 && payrollInfo.getEmployeePayrollTax().getSs()<= 2914);
        assertTrue(payrollInfo.getEmployeePayrollTax().getMedicare() >= 3800 && payrollInfo.getEmployeePayrollTax().getMedicare() <= 3800);
        assertTrue(payrollInfo.getEmployeePayrollTax().getSdi() >= 2280 && payrollInfo.getEmployeePayrollTax().getSdi() <= 2280);
        //assertTrue(payrollInfo.getEmployeePayrollTax().getState_wh() >= 64 && payrollInfo.getEmployeePayrollTax().getState_wh() <= 67);
        assertTrue(payrollInfo.getCompanyPayrollTax().getFutaTax() >= 0 && payrollInfo.getCompanyPayrollTax().getFutaTax() <= 0);
        assertTrue(payrollInfo.getCompanyPayrollTax().getMedicareTax() >= 2900 && payrollInfo.getCompanyPayrollTax().getMedicareTax() <= 2900);
        assertTrue(payrollInfo.getCompanyPayrollTax().getSsTax() >= 2914 && payrollInfo.getCompanyPayrollTax().getSsTax() <= 2914);


    }

    @Test
    void checkPayrollInfo3() throws Exception
    {

        CompanyPayrollRate companyPayrollRate = new CompanyPayrollRate(0.045);
        EmployeeW4 employeeW4 = new EmployeeW4(FederalFilingStatusEnum.SINGLE_OR_MARRIEDFILINGSEPERATELY, true,
                0, 0, 0,  false, 0,
                0, 0, 0,  true, false);


        CADE4 cade4 = new CADE4(CAPayrollPeriodEnum.BI_WEEKLY, 0, 0, com.neel.payrolllibrary.ca.CAFilingStatusEnum.MARRIED, false);

        EmployeePayrollInfo employeePayrollInfo = new EmployeePayrollInfo(1000000, 3820,
                10000, 0.10, false, 0.05, employeeW4, cade4);
        PayrollInfo payrollInfo = new PayrollInfo(2022, PayrollLibUnitTestUtil.getDataPath(), PayrollPeriodEnum.BI_WEEKLY, companyPayrollRate, employeePayrollInfo);


        ComputeAllPayrollTaxes computeAllPayrollTaxes = new ComputeAllPayrollTaxes(payrollInfo);
        computeAllPayrollTaxes.computeAllTaxes();
         assertTrue(payrollInfo.getEmployeePayrollTax().getFederal_wh() >= 478 && payrollInfo.getEmployeePayrollTax().getFederal_wh() <= 479, "new w4 standard HH");
        assertTrue(payrollInfo.getEmployeePayrollTax().getState_wh() >= 114 && payrollInfo.getEmployeePayrollTax().getState_wh() <= 116);


    }

}