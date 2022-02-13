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

public class CAStandardDeductionTable
{
    private String payrollPeriod = "";
    private double singleDualIncomeMarriedMarriedMultipleEmployers = 0.0;
    private double married0OR1Allowance = 0.0;
    private double married2MoreAllowance = 0.0;
    private double unmarriedHeadOfHousehold = 0.0;

    @Override
    public String toString()
    {
        return "StandardDeductionTable{" +
                "payrollPeriod='" + payrollPeriod + '\'' +
                ", singleDualIncomeMarriedMarriedMultipleEmployers=" + singleDualIncomeMarriedMarriedMultipleEmployers +
                ", married0OR1Allowance=" + married0OR1Allowance +
                ", married2MoreAllowance=" + married2MoreAllowance +
                ", unmarriedHeadOfHousehold=" + unmarriedHeadOfHousehold +
                '}';
    }

    public CAStandardDeductionTable(String payrollPeriod, double singleDualIncomeMarriedMarriedMultipleEmployers, double married0OR1Allowance, double married2MoreAllowance, double unmarriedHeadOfHousehold)
    {
        this.payrollPeriod = payrollPeriod;
        this.singleDualIncomeMarriedMarriedMultipleEmployers = singleDualIncomeMarriedMarriedMultipleEmployers;
        this.married0OR1Allowance = married0OR1Allowance;
        this.married2MoreAllowance = married2MoreAllowance;
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

    public double getSingleDualIncomeMarriedMarriedMultipleEmployers()
    {
        return singleDualIncomeMarriedMarriedMultipleEmployers;
    }

    public void setSingleDualIncomeMarriedMarriedMultipleEmployers(double singleDualIncomeMarriedMarriedMultipleEmployers)
    {
        this.singleDualIncomeMarriedMarriedMultipleEmployers = singleDualIncomeMarriedMarriedMultipleEmployers;
    }

    public double getMarried0OR1Allowance()
    {
        return married0OR1Allowance;
    }

    public void setMarried0OR1Allowance(double married0OR1Allowance)
    {
        this.married0OR1Allowance = married0OR1Allowance;
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
}
