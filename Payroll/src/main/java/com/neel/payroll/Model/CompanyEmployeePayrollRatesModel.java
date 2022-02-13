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
public class CompanyEmployeePayrollRatesModel {

    private float futa;

    private float sdi;

    private int year;

    /**
     * Get the value of year
     *
     * @return the value of year
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the value of year
     *
     * @param year new value of year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Get the value of sdi
     *
     * @return the value of sdi
     */
    public float getSdi() {
        return sdi;
    }

    /**
     * Set the value of sdi
     *
     * @param sdi new value of sdi
     */
    public void setSdi(float sdi) {
        this.sdi = sdi;
    }

    /**
     * Get the value of futa
     *
     * @return the value of futa
     */
    public float getUnemploymentFUTARatePercent() {
        return futa;
    }

    /**
     * Set the value of futa
     *
     * @param unemploymentFUTARatePercent new value of futa
     */
    public void setUnemploymentFUTARatePercent(float futa) {
        this.futa = futa;
    }

}
