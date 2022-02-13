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

import com.neel.payrolllibrary.fed.W4_2019_EarlierTentativeWitholding;
import com.neel.payrolllibrary.fed.W4_2020_LaterTentativeWitholding;

import java.io.IOException;

public class ComputeFederalWithholding
{
    private int payYear = 0;
    private double totalTaxableWages = 0.0;
    private PayrollPeriodEnum payrollPeriod;
    private boolean submittedW4Form = false;
    private double newStep4aAdditionalIncome = 0.0;
    private double newStep4bDeduction = 0.0;
    private boolean newStep2MultipleJob = false;
    private int oldNumAllowance = 0;
    private FederalFilingStatusEnum oldFederalFilingStatus;
    private boolean w42020AndLater = false;
    private int oldStep4cAdditionalAmntWithold = 0;
    private int newStep3Line3Amnt = 0;
    private int newStep4Line4aAmount = 0;
    private String dataPath = "";
    private boolean EXEMPT = false;


    public ComputeFederalWithholding(int payYear, double totalTaxableWages, PayrollPeriodEnum payrollPeriod, boolean submittedW4Form, double newStep4aAdditionalIncome,
                                     double newStep4bDeduction, boolean newStep2MultipleJob, int oldNumAllowance, FederalFilingStatusEnum oldFederalFilingStatus,
                                     boolean w42020AndLater, int newStep3Line3Amnt, int oldStep4cAdditionalAmntWithold, int newStep4Line4aAmount, String dataPath, boolean EXEMPT) throws Exception
    {
        if (oldFederalFilingStatus == FederalFilingStatusEnum.HEADOFHOUSEHOLD && w42020AndLater == false)
        {
            throw new Exception("W4 Form 2019 or earlier does not support Head of Household");
        }
        if (w42020AndLater == true)
        {
            resetW4Old2019EarlerValues();
        }
        else
        {
            resetW4New2020LaterValues();
        }

        this.payYear = payYear;
        this.totalTaxableWages = totalTaxableWages;
        this.payrollPeriod = payrollPeriod;
        this.submittedW4Form = submittedW4Form;
        this.newStep4aAdditionalIncome = newStep4aAdditionalIncome;
        this.newStep4bDeduction = newStep4bDeduction;
        this.newStep2MultipleJob = newStep2MultipleJob;
        this.oldNumAllowance = oldNumAllowance;
        this.oldFederalFilingStatus = oldFederalFilingStatus;
        this.w42020AndLater = w42020AndLater;
        this.newStep3Line3Amnt = newStep3Line3Amnt;
        this.oldStep4cAdditionalAmntWithold = oldStep4cAdditionalAmntWithold;
        this.newStep4Line4aAmount = newStep4Line4aAmount;
        this.dataPath = dataPath;
        this.EXEMPT = EXEMPT;


    }

    private void resetW4Old2019EarlerValues()
    {
        oldNumAllowance = 0;
        oldStep4cAdditionalAmntWithold = 0;

    }

    private void resetW4New2020LaterValues()
    {
        newStep4aAdditionalIncome = 0.0;
        newStep4bDeduction = 0.0;
        newStep2MultipleJob = false;
        newStep3Line3Amnt = 0;
        newStep4Line4aAmount = 0;
    }

    public double federalWitholding() throws IOException
    {
        if (EXEMPT == true)
        {
            return 0.0;
        }
        double adjustedPaymentAmount = adjustedPaymentAmount();
        return adjustedPaymentAmount;

    }

    private double adjustedPaymentAmount() throws IOException
    {
        double adjustedAnnualWageAmount = 0.0;
        double payPeriod = getPayPeriod();
        double resultStep1C = getResultStep1C(payPeriod);
        if (submittedW4Form == true)
        {
            adjustedAnnualWageAmount = submittedFormW4(resultStep1C);
        }
        else
        {
            adjustedAnnualWageAmount = notSubmittedFormW4(resultStep1C);
        }

        double tentativeWitholdingAmount = tentativeWitholdingAmount(adjustedAnnualWageAmount, payPeriod);
        double taxCredit = taxCredit(payPeriod, tentativeWitholdingAmount);
        double finalAmountWitheld = finalAmountWitheld(taxCredit);

        return finalAmountWitheld;

    }

    private double getPayPeriod()
    {
        double payPeriod = 0.0;
        switch (payrollPeriod)
        {
            case WEEKLY:
                payPeriod = 52;
                break;
            case BI_WEEKLY:
                payPeriod = 26;
                break;
            case SEMI_MONTHLY:
                payPeriod = 24;
                break;
            case MONTHLY:
                payPeriod = 12;
                break;
            case QUARTERLY:
                payPeriod = 4;
                break;
            case SEMI_ANNUAL:
                payPeriod = 2;
                break;
            case DAILY_MISC:
                payPeriod = 260;
                break;
        }

        return payPeriod;
    }

    private double getResultStep1C(double payPeriod)
    {
        double resultStep1C = totalTaxableWages * payPeriod;
        return resultStep1C;
    }

    private double submittedFormW4(double resultStep1C)
    {
        double sumLine1c1D = resultStep1C + newStep4aAdditionalIncome;
        double step2Salary = 0.0;
        if (newStep2MultipleJob == true)
        {
            step2Salary = 0.0;
        }
        else
        {
            if (oldFederalFilingStatus == FederalFilingStatusEnum.MARRIEDFILINGJOINTLY_OR_QUALIFYINGWIDOWER)
            {
                step2Salary = 12900;
            }
            else
            {
                step2Salary = 8600;
            }
        }

        double sum1f1g = step2Salary + newStep4bDeduction;
        double adjustedAnnualWageAmount = sumLine1c1D - sum1f1g;

        if (adjustedAnnualWageAmount > 0)
        {
            return adjustedAnnualWageAmount;
        }
        else
        {
            return 0.0;
        }
    }

    private double notSubmittedFormW4(double resultStep1C)
    {
        double resultStep1k = oldNumAllowance * 4300;
        double adjustedAnnualWageAmount = resultStep1C - resultStep1k;

        return adjustedAnnualWageAmount;
    }

    public double tentativeWitholdingAmount(double adjustedAnnualWageAmount, double payPeriod) throws IOException
    {
        double columnA = 0;
        double columnC = 0.0;
        double columnD = 0.0;
        double tentativeWitholdingAmount = 0.0;
        switch (oldFederalFilingStatus)
        {
            case MARRIEDFILINGJOINTLY_OR_QUALIFYINGWIDOWER:
            case SINGLE_OR_MARRIEDFILINGSEPERATELY:
            case HEADOFHOUSEHOLD:
                if (w42020AndLater == false || (w42020AndLater == true && newStep2MultipleJob == false))
                {


                    W4_2019_EarlierTentativeWitholding w42019EarlierTentativeWitholdingAmount = new W4_2019_EarlierTentativeWitholding(payYear,
                            dataPath);
                    w42019EarlierTentativeWitholdingAmount.initialize(oldFederalFilingStatus, payYear);

                    tentativeWitholdingAmount = w42019EarlierTentativeWitholdingAmount.getTentativeWitholdingAmount(adjustedAnnualWageAmount, payPeriod);
                }

                else if (w42020AndLater == true && newStep2MultipleJob == true)
                {

                    W4_2020_LaterTentativeWitholding w42020LaterTentativeWitholdingAmount = new W4_2020_LaterTentativeWitholding(payYear,
                            dataPath);
                    w42020LaterTentativeWitholdingAmount.initialize(oldFederalFilingStatus, payYear);

                    tentativeWitholdingAmount = w42020LaterTentativeWitholdingAmount.getTentativeWitholdingAmount(adjustedAnnualWageAmount, payPeriod);

                }
        }

        return tentativeWitholdingAmount;

    }


    public double taxCredit(double payPeriod, double tentativeWitholdingAmount)
    {
        double taxCredit = 0.0;
        double step3b = 0.0;

        if (w42020AndLater == true)
        {
            step3b = newStep3Line3Amnt / payPeriod;
            taxCredit = tentativeWitholdingAmount - step3b;
        }
        else
        {
            return 0.0;
        }

        if (taxCredit <= 0)
        {
            return 0.0;
        }

        return taxCredit;

    }

    public double finalAmountWitheld(double taxCredit)
    {
        double finalAmountWitheld = oldStep4cAdditionalAmntWithold + taxCredit + newStep4Line4aAmount;
        return finalAmountWitheld;

    }


}
