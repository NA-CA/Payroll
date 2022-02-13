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

public class SocialSecurity
{
    private int year = 0;
    private double employeeRate = 0.0;
    private double employerRate = 0.0;
    private double socialSecurityWageLimit = 0.0;

    @Override
    public String toString()
    {
        return "SocialSecurity{" +
                "year=" + year +
                ", employeeRate=" + employeeRate +
                ", employerRate=" + employerRate +
                ", maxAmount=" + socialSecurityWageLimit +
                '}';
    }

    public SocialSecurity(int year, double employeeRate, double employerRate, double socialSecurityWageLimit)
    {
        this.year = year;
        this.employeeRate = employeeRate;
        this.employerRate = employerRate;
        this.socialSecurityWageLimit = socialSecurityWageLimit;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public double getEmployeeRate()
    {
        return employeeRate;
    }

    public void setEmployeeRate(double employeeRate)
    {
        this.employeeRate = employeeRate;
    }

    public double getEmployerRate()
    {
        return employerRate;
    }

    public void setEmployerRate(double employerRate)
    {
        this.employerRate = employerRate;
    }

    public double getSocialSecurityWageLimit()
    {
        return socialSecurityWageLimit;
    }

    public void setSocialSecurityWageLimit(double socialSecurityWageLimit)
    {
        this.socialSecurityWageLimit = socialSecurityWageLimit;
    }
}
