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

public class CATaxRateTable
{
    private int minAmount = 0;
    private int maxAmount = 0;
    private double taxRate = 0.0;
    private double computedTax = 0.0;


    public double getComputedTax()
    {
        return computedTax;
    }

    public void setComputedTax(double computedTax)
    {
        this.computedTax = computedTax;
    }

    public CATaxRateTable(int minAmount, int maxAmount, double taxRate)
    {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.taxRate = taxRate;
        this.computedTax = (maxAmount-minAmount) * taxRate;

    }

    public int getMinAmount()
    {
        return minAmount;
    }

    public void setMinAmount(int minAmount)
    {
        this.minAmount = minAmount;
    }

    public int getMaxAmount()
    {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount)
    {
        this.maxAmount = maxAmount;
    }

    public double getTaxRate()
    {
        return taxRate;
    }

    public void setTaxRate(double taxRate)
    {
        this.taxRate = taxRate;
    }

    @Override
    public String toString()
    {
        return "ExactCalculationMethod{" +
                "minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", taxRate=" + taxRate +
                '}';
    }
}
