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

public class ComputeFUTA
{
    private double grossWagesYTD = 0.0;
    private double grossWagesCurrPayPeriod = 0.0;
    private double futaTaxRate = 0.0;

    public ComputeFUTA(double grossWagesYTD, double grossWagesCurrPayPeriod, double futaTaxRate)
    {
        this.grossWagesYTD = grossWagesYTD;
        this.grossWagesCurrPayPeriod = grossWagesCurrPayPeriod;
        this.futaTaxRate = futaTaxRate;
    }

    public double computeFutaTax()
    {
        double futaTax = 0.0;
        if (grossWagesYTD >= 7000)
        {
            return 0.0;
        }
        else if (grossWagesYTD + grossWagesCurrPayPeriod >= 7000)
        {
            futaTax = (7000-grossWagesYTD)  * futaTaxRate;
        }
        else
        {
            futaTax = grossWagesCurrPayPeriod * futaTaxRate;

        }

        return futaTax;
    }

}
