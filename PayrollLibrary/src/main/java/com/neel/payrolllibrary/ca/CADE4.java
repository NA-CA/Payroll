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

public class CADE4
{

    private CAPayrollPeriodEnum payrollPeriod = CAPayrollPeriodEnum.BI_WEEKLY;
    private int caDe4Line1AregularWHStandardAllowane = 0;
    private int caDe4Line1BestimatedDeduction_exemptionWHAllowance = 0;
    private CAFilingStatusEnum caFilingStatusEnum = CAFilingStatusEnum.SINGLE;
    private boolean EXEMPT = false;

    public CAPayrollPeriodEnum getPayrollPeriod()
    {
        return payrollPeriod;
    }

    public void setPayrollPeriod(CAPayrollPeriodEnum payrollPeriod)
    {
        this.payrollPeriod = payrollPeriod;
    }

    public int getCaDe4Line1AregularWHStandardAllowane()
    {
        return caDe4Line1AregularWHStandardAllowane;
    }

    public void setCaDe4Line1AregularWHStandardAllowane(int caDe4Line1AregularWHStandardAllowane)
    {
        this.caDe4Line1AregularWHStandardAllowane = caDe4Line1AregularWHStandardAllowane;
    }

    public int getCaDe4Line1BestimatedDeduction_exemptionWHAllowance()
    {
        return caDe4Line1BestimatedDeduction_exemptionWHAllowance;
    }

    public void setCaDe4Line1BestimatedDeduction_exemptionWHAllowance(int caDe4Line1BestimatedDeduction_exemptionWHAllowance)
    {
        this.caDe4Line1BestimatedDeduction_exemptionWHAllowance = caDe4Line1BestimatedDeduction_exemptionWHAllowance;
    }

    public CAFilingStatusEnum getCaFilingStatusEnum()
    {
        return caFilingStatusEnum;
    }

    public void setCaFilingStatusEnum(CAFilingStatusEnum caFilingStatusEnum)
    {
        this.caFilingStatusEnum = caFilingStatusEnum;
    }

    public boolean getEXEMPT()
    {
        return EXEMPT;
    }

    public void setEXEMPT(boolean EXEMPT)
    {
        this.EXEMPT = EXEMPT;
    }

    public CADE4(CAPayrollPeriodEnum payrollPeriod, int caDe4Line1AregularWHStandardAllowane, int caDe4Line1BestimatedDeduction_exemptionWHAllowance, CAFilingStatusEnum caFilingStatusEnum, boolean EXEMPT)
    {
        this.payrollPeriod = payrollPeriod;
        this.caDe4Line1AregularWHStandardAllowane = caDe4Line1AregularWHStandardAllowane;
        this.caDe4Line1BestimatedDeduction_exemptionWHAllowance = caDe4Line1BestimatedDeduction_exemptionWHAllowance;
        this.caFilingStatusEnum = caFilingStatusEnum;
        this.EXEMPT = EXEMPT;
    }
}
