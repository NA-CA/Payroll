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

public class PayrollInfo
{
    private int year = 0;
    private String dataPath = "";

    private PayrollPeriodEnum payrollPeriodEnum = PayrollPeriodEnum.BI_WEEKLY;

    private CompanyPayrollRate companyPayrollRate = null;
    private EmployeePayrollInfo employeePayrollInfo = null;

    private CompanyPayrollTax companyPayrollTax = null;
    private EmployeePayrollTax employeePayrollTax = null;

    public PayrollInfo(int year, String dataPath, PayrollPeriodEnum payrollPeriodEnum, CompanyPayrollRate companyPayrollRate, EmployeePayrollInfo employeePayrollInfo)
    {
        this.year = year;
        this.payrollPeriodEnum = payrollPeriodEnum;
        this.companyPayrollRate = companyPayrollRate;
        this.employeePayrollInfo = employeePayrollInfo;
        this.dataPath = dataPath;
    }

    public PayrollInfo()
    {

    }

    public void setDataPath(String dataPath)
    {
        this.dataPath = dataPath;
    }

    public void setCompanyPayrollTax(CompanyPayrollTax companyPayrollTax)
    {
        this.companyPayrollTax = companyPayrollTax;
    }

    public void setEmployeePayrollTax(EmployeePayrollTax employeePayrollTax)
    {
        this.employeePayrollTax = employeePayrollTax;
    }

    public PayrollPeriodEnum getPayrollPeriodEnum()
    {
        return payrollPeriodEnum;
    }

    public void setPayrollPeriodEnum(PayrollPeriodEnum payrollPeriodEnum)
    {
        this.payrollPeriodEnum = payrollPeriodEnum;
    }

    public int getYear()
    {
        return year;
    }

    public CompanyPayrollRate getCompanyPayrollRate()
    {
        return companyPayrollRate;
    }

    public EmployeePayrollInfo getEmployeePayrollInfo()
    {
        return employeePayrollInfo;
    }

    public CompanyPayrollTax getCompanyPayrollTax()
    {
        return companyPayrollTax;
    }

    public EmployeePayrollTax getEmployeePayrollTax()
    {
        return employeePayrollTax;
    }

    public String getDataPath()
    {
        return dataPath;
    }


}
