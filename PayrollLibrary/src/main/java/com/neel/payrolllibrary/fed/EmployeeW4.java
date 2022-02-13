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

import com.neel.payrolllibrary.FederalFilingStatusEnum;

public class EmployeeW4
{
    private FederalFilingStatusEnum federalFilingStatusEnum = FederalFilingStatusEnum.SINGLE_OR_MARRIEDFILINGSEPERATELY;
    private boolean submittedAnyYearW4 = true;

    private int oldW4FilingStatus = OLD_W4_SINGLE;
    private int oldW4NumOfAllowances;
    private int oldW4AdduitionWithHold = 0;
    private boolean new2020W4step2MultipleJobs = false;
    private int new2020W4Step3Line3Dependents = 0;
    private int new2020W4Step4Line4aOtherIncome = 0;
    private int new2020W4Step4Line4bDeductions = 0;
    private int new2020W4Step4Line4cExtraWithHolding = 0;
    private boolean w42020OrLater = false;

    public static final int OLD_W4_SINGLE = 0;
    public static final int OLD_W4_MARRIED = 1;
    public static final int OLD_W4_MARRIED_HIGH_WH = 2;
    private boolean EXEMPT = false;


    public EmployeeW4(FederalFilingStatusEnum federalFilingStatusEnum, boolean submittedAnyYearW4,
                      int oldW4FilingStatus, int oldW4NumOfAllowances, int oldW4AdduitionWithHold,  boolean new2020W4step2MultipleJobs,
                      int new2020W4Step3Line3Dependents, int new2020W4Step4Line4aOtherIncome, int new2020W4Step4Line4bDeductions, int new2020W4Step4Line4cExtraWithHolding,
                      boolean w42020OrLater, boolean EXEMPT )
    {;
        this.federalFilingStatusEnum = federalFilingStatusEnum;
        this.submittedAnyYearW4 = submittedAnyYearW4;
        this.oldW4FilingStatus = oldW4FilingStatus;
        this.oldW4NumOfAllowances = oldW4NumOfAllowances;
        this.oldW4AdduitionWithHold = oldW4AdduitionWithHold;
        this.new2020W4step2MultipleJobs = new2020W4step2MultipleJobs;
        this.new2020W4Step3Line3Dependents = new2020W4Step3Line3Dependents;
        this.new2020W4Step4Line4aOtherIncome = new2020W4Step4Line4aOtherIncome;
        this.new2020W4Step4Line4bDeductions = new2020W4Step4Line4bDeductions;
        this.new2020W4Step4Line4cExtraWithHolding = new2020W4Step4Line4cExtraWithHolding;
        this.w42020OrLater = w42020OrLater;
        this.EXEMPT = EXEMPT;
    }

    public FederalFilingStatusEnum getFederalFilingStatusEnum()
    {
        return federalFilingStatusEnum;
    }

    public void setFederalFilingStatusEnum(FederalFilingStatusEnum federalFilingStatusEnum)
    {
        this.federalFilingStatusEnum = federalFilingStatusEnum;
    }

    // Payroll Period enum is missing
;

    public boolean getW42020OrLater()
    {
        return w42020OrLater;
    }

    public boolean getW4FormYear()
    {
        return w42020OrLater;
    }

    public void setW4FormYear(boolean w4FormYear)
    {
        this.w42020OrLater = w4FormYear;
    }

    public boolean getSubmittedAnyYearW4() {
        return submittedAnyYearW4;
    }

    public void setSubmittedAnyYearW4(boolean submittedAnyYearW4) {
        this.submittedAnyYearW4 = submittedAnyYearW4;
    }




    public int getOldW4NumOfAllowances() {
        return oldW4NumOfAllowances;
    }


    public void setOldW4NumOfAllowances(int oldW4NumOfAllowances) {
        this.oldW4NumOfAllowances = oldW4NumOfAllowances;
    }





    public int getOldW4FilingStatus() {
        return oldW4FilingStatus;
    }


    public void setOldW4FilingStatus(int oldW4FilingStatus) {
        this.oldW4FilingStatus = oldW4FilingStatus;
    }




    public int getOldW4AdduitionWithHold() {
        return oldW4AdduitionWithHold;
    }


    public void setOldW4AdduitionWithHold(int oldW4AdduitionWithHold) {
        this.oldW4AdduitionWithHold = oldW4AdduitionWithHold;
    }


    public boolean getEXEMPT()
    {
        return EXEMPT;
    }

    public void setEXEMPT(boolean EXEMPT)
    {
        this.EXEMPT = EXEMPT;
    }

    public boolean isNew2020W4step2MultipleJobs() {
        return new2020W4step2MultipleJobs;
    }

    public boolean getNew2020W4step2MultipleJobs() {
        return new2020W4step2MultipleJobs;
    }


    public void setNew2020W4step2MultipleJobs(boolean new2020W4step2MultipleJobs) {
        this.new2020W4step2MultipleJobs = new2020W4step2MultipleJobs;
    }




    public int getNew2020W4Step3Line3Dependents() {
        return new2020W4Step3Line3Dependents;
    }


    public void setNew2020W4Step3Line3Dependents(int new2020W4Step3Line3Dependents) {
        this.new2020W4Step3Line3Dependents = new2020W4Step3Line3Dependents;
    }




    public int getNew2020W4Step4Line4aOtherIncome() {
        return new2020W4Step4Line4aOtherIncome;
    }


    public void setNew2020W4Step4Line4aOtherIncome(int new2020W4Step4Line4aOtherIncome) {
        this.new2020W4Step4Line4aOtherIncome = new2020W4Step4Line4aOtherIncome;
    }


    public int getNew2020W4Step4Line4bDeductions() {
        return new2020W4Step4Line4bDeductions;
    }


    public void setNew2020W4Step4Line4bDeductions(int new2020W4Step4Line4bDeductions) {
        this.new2020W4Step4Line4bDeductions = new2020W4Step4Line4bDeductions;
    }




    public int getNew2020W4Step4Line4cExtraWithHolding() {
        return new2020W4Step4Line4cExtraWithHolding;
    }


    public void setNew2020W4Step4Line4cExtraWithHolding(int new2020W4Step4Line4cExtraWithHolding) {
        this.new2020W4Step4Line4cExtraWithHolding = new2020W4Step4Line4cExtraWithHolding;
    }

}
