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

package com.neel.payroll.Model;

/**
 *
 * @author Neel
 */
public class YearToDateTotals {

    private float grossSalary = 0F;

    private float ytd401K = 0F;

    public YearToDateTotals() {
    }

    /**
     * Get the value of ytd401K
     *
     * @return the value of ytd401K
     */
    public float getYtd401K() {
        return ytd401K;
    }

    /**
     * Set the value of ytd401K
     *
     * @param ytd401K new value of ytd401K
     */
    public void setYtd401K(float ytd401K) {
        this.ytd401K = ytd401K;
    }

    /**
     * Get the value of grossSalary
     *
     * @return the value of grossSalary
     */
    public float getGrossSalary() {
        return grossSalary;
    }

    /**
     * Set the value of grossSalary
     *
     * @param grossSalary new value of grossSalary
     */
    public void setGrossSalary(float grossSalary) {
        this.grossSalary = grossSalary;
    }

}
