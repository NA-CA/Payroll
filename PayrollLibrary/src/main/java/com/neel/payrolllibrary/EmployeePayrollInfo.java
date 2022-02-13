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
import com.neel.payrolllibrary.fed.EmployeeW4;

public class EmployeePayrollInfo
{
    private double grossWagesYTD = 0.0;
    private double grossWagesCurrPayPeriod = 0.0;
    private double employee401KYTD = 0.0;
    private double employee401KDeferallRate = 0.0;
    private boolean fiftyPlus = false;
    private double sdiRate = 0.0;
    private EmployeeW4 employeeW4;
    private CADE4 cade4;

    public EmployeePayrollInfo(double grossWagesYTD, double grossWagesCurrPayPeriod, double employee401KYTD, double employee401KDeferallRate, boolean fiftyPlus,
                               double sdiRate, EmployeeW4 employeeW4, CADE4 cade4)
    {
        this.grossWagesYTD = grossWagesYTD;
        this.grossWagesCurrPayPeriod = grossWagesCurrPayPeriod;
        this.employee401KYTD = employee401KYTD;
        this.employee401KDeferallRate = employee401KDeferallRate;
        this.fiftyPlus = fiftyPlus;
        this.sdiRate = sdiRate;
        this.employeeW4 = employeeW4;
        this.cade4 = cade4;

    }

    public CADE4 getCade4()
    {
        return cade4;
    }

    public EmployeeW4 getEmployeeW4()
    {
        return employeeW4;
    }

    public void setEmployeeW4(EmployeeW4 employeeW4)
    {
        this.employeeW4 = employeeW4;
    }

    public void setGrossWagesYTD(double grossWagesYTD)
    {
        this.grossWagesYTD = grossWagesYTD;
    }

    public void setGrossWagesCurrPayPeriod(double grossWagesCurrPayPeriod)
    {
        this.grossWagesCurrPayPeriod = grossWagesCurrPayPeriod;
    }

    public double getSdiRate()
    {
        return sdiRate;
    }



    public double getGrossWagesCurrPayPeriod()
    {
        return grossWagesCurrPayPeriod;
    }

    public double getEmployee401KYTD()
    {
        return employee401KYTD;
    }

    public double getGrossWagesYTD()
    {
        return grossWagesYTD;
    }

    public boolean isFiftyPlus()
    {
        return fiftyPlus;
    }

    public double getEmployee401KDeferallRate()
    {
        return employee401KDeferallRate;
    }
}
