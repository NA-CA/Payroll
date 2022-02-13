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

public class CAEstimatedDeductionTable
{
    private int additionalWitholdingAllowances = 0;
    private double annual = 0.0;
    private double dailyMisc = 0.0;



    public CAEstimatedDeductionTable(int additionalWitholdingAllowances, double annual, double dailyMisc)
    {
        this.additionalWitholdingAllowances = additionalWitholdingAllowances;
        this.annual = annual;
        this.dailyMisc = dailyMisc;
    }

    public int getAdditionalWitholdingAllowances()
    {
        return additionalWitholdingAllowances;
    }

    public void setAdditionalWitholdingAllowances(int additionalWitholdingAllowances)
    {
        this.additionalWitholdingAllowances = additionalWitholdingAllowances;
    }

    public double getAnnual()
    {
        return annual;
    }

    public void setAnnual(double annual)
    {
        this.annual = annual;
    }

    public double getDailyMisc()
    {
        return dailyMisc;
    }

    public void setDailyMisc(double dailyMisc)
    {
        this.dailyMisc = dailyMisc;
    }

    @Override
    public String toString()
    {
        return "EstimatedDeductionTable{" +
                "additionalWitholdingAllowances=" + additionalWitholdingAllowances +
                ", annual=" + annual +
                ", dailyMisc=" + dailyMisc +
                '}';
    }
}
