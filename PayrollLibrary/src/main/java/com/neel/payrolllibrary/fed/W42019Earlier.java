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

package com.neel.payrolllibrary.fed;

public class W42019Earlier
{

    private double minAmount = 0;
    private double maxAmount = 0;
    private double tentativeWitholdAmount = 0.0;
    private double percentage = 0;
    private double exceededAdjustedAnnualAmount = 0;

    public W42019Earlier(double minAmount, double maxAmount, double tentativeWitholdAmount, double percentage, double exceededAdjustedAnnualAmount)
    {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.tentativeWitholdAmount = tentativeWitholdAmount;
        this.percentage = percentage;
        this.exceededAdjustedAnnualAmount = exceededAdjustedAnnualAmount;
    }

    public double getMinAmount()
    {
        return minAmount;
    }

    public void setMinAmount(double minAmount)
    {
        this.minAmount = minAmount;
    }

    public double getMaxAmount()
    {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount)
    {
        this.maxAmount = maxAmount;
    }

    public double getTentativeWitholdAmount()
    {
        return tentativeWitholdAmount;
    }

    public void setTentativeWitholdAmount(double tentativeWitholdAmount)
    {
        this.tentativeWitholdAmount = tentativeWitholdAmount;
    }

    public double getPercentage()
    {
        return percentage;
    }

    public void setPercentage(double percentage)
    {
        this.percentage = percentage;
    }

    public double getExceededAdjustedAnnualAmount()
    {
        return exceededAdjustedAnnualAmount;
    }

    public void setExceededAdjustedAnnualAmount(double exceededAdjustedAnnualAmount)
    {
        this.exceededAdjustedAnnualAmount = exceededAdjustedAnnualAmount;
    }
}
