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

public class EmployeePayrollTax
{
    private double employee_401k = 0.0F;

    private double state_wh = 0.0F;

    private double federal_wh = 0.0F;

    private double medicare = 0.0F;

    private double ss = 0.0F;

    private double sdi = 0.0F;



    public EmployeePayrollTax()
    {

    }

    public EmployeePayrollTax(double medicare, double ss, double employee_401k, double sdi, double federal_wh, double state_wh)
    {
        this.medicare = medicare;
        this.ss = ss;
        this.employee_401k = employee_401k;
        this.sdi = sdi;
        this.federal_wh = federal_wh;
        this.state_wh = state_wh;
    }

    public double getEmployee_401k()
    {
        return employee_401k;
    }

    public void setEmployee_401k(double employee_401k)
    {
        this.employee_401k = employee_401k;
    }

    public double getState_wh()
    {
        return state_wh;
    }

    public void setState_wh(double state_wh)
    {
        this.state_wh = state_wh;
    }

    public double getFederal_wh()
    {
        return federal_wh;
    }

    public void setFederal_wh(double federal_wh)
    {
        this.federal_wh = federal_wh;
    }

    public double getMedicare()
    {
        return medicare;
    }

    public void setMedicare(double medicare)
    {
        this.medicare = medicare;
    }

    public double getSs()
    {
        return ss;
    }

    public void setSs(double ss)
    {
        this.ss = ss;
    }

    public double getSdi()
    {
        return sdi;
    }

    public void setSdi(double sdi)
    {
        this.sdi = sdi;
    }
}
