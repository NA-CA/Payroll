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

package com.neel.payrolllibrary.ca;

import java.util.HashMap;

public class CALowIncomeExemptionTable
{

    // Step 1: Update enum with all possible payroll periods
    // Step 2: Create variables to store all information from file
    // Step 3: Based on hashmap payroll period with key, get object necessary
    // Step 4: Then with the key, (year) get payroll information

    private int year = 0;
    private double singleDualIncomeMarriedMarriedWithMultipleEmployers = 0.0;
    private double married01Allowance = 0.0;
    private double married2MoreAllowance = 0.0;
    private double unmarriedHeadOfHousehold = 0.0;
    private String payrollPeriod = "";


    public CALowIncomeExemptionTable(String payrollPeriod, double singleDualIncomeMarriedMarriedWithMultipleEmployers, double married01Allowance, double married2MoreAllowance, double unmarriedHeadOfHousehold)
    {
        this.payrollPeriod = payrollPeriod;
        this.singleDualIncomeMarriedMarriedWithMultipleEmployers = singleDualIncomeMarriedMarriedWithMultipleEmployers;
        this.married01Allowance = married01Allowance;
        this.married2MoreAllowance = married2MoreAllowance;
        this.unmarriedHeadOfHousehold = unmarriedHeadOfHousehold;

    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public double getSingleDualIncomeMarriedMarriedWithMultipleEmployers()
    {
        return singleDualIncomeMarriedMarriedWithMultipleEmployers;
    }

    public void setSingleDualIncomeMarriedMarriedWithMultipleEmployers(double singleDualIncomeMarriedMarriedWithMultipleEmployers)
    {
        this.singleDualIncomeMarriedMarriedWithMultipleEmployers = singleDualIncomeMarriedMarriedWithMultipleEmployers;
    }

    public double getMarried01Allowance()
    {
        return married01Allowance;
    }

    public void setMarried01Allowance(double married01Allowance)
    {
        this.married01Allowance = married01Allowance;
    }

    public double getMarried2MoreAllowance()
    {
        return married2MoreAllowance;
    }

    public void setMarried2MoreAllowance(double married2MoreAllowance)
    {
        this.married2MoreAllowance = married2MoreAllowance;
    }


    public double getUnmarriedHeadOfHousehold()
    {
        return unmarriedHeadOfHousehold;
    }

    public void setUnmarriedHeadOfHousehold(double unmarriedHeadOfHousehold)
    {
        this.unmarriedHeadOfHousehold = unmarriedHeadOfHousehold;
    }

    public String getPayrollPeriod()
    {
        return payrollPeriod;
    }

    public void setPayrollPeriod(String payrollPeriod)
    {
        this.payrollPeriod = payrollPeriod;
    }

    @Override
    public String toString()
    {
        return "LowIncomeExceptionTable{" +
                "year=" + year +
                ", singleDualIncomeMarriedMarriedWithMultipleEmployers=" + singleDualIncomeMarriedMarriedWithMultipleEmployers +
                ", married01Allowance=" + married01Allowance +
                ", married2MoreAllowance=" + married2MoreAllowance +
                ", unmarriedHeadOfHousehold=" + unmarriedHeadOfHousehold +
                ", payrollPeriod='" + payrollPeriod + '\'' +
                '}';
    }



}
