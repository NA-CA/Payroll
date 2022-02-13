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
public class EmployeeW4Model {

    private boolean exempt = false;

    /**
     * Get the value of exempt
     *
     * @return the value of exempt
     */
    public boolean isExempt() {
        return exempt;
    }

    /**
     * Set the value of exempt
     *
     * @param exempt new value of exempt
     */
    public void setExempt(boolean exempt) {
        this.exempt = exempt;
    }

    private boolean submitted2020PlusW4 = true;

    /**
     * Get the value of submitted2020PlusW4
     *
     * @return the value of submitted2020PlusW4
     */
    public boolean isSubmitted2020PlusW4() {
        return submitted2020PlusW4;
    }

    /**
     * Set the value of submitted2020PlusW4
     *
     * @param submitted2020PlusW4 new value of submitted2020PlusW4
     */
    public void setSubmitted2020PlusW4(boolean submitted2020PlusW4) {
        this.submitted2020PlusW4 = submitted2020PlusW4;
    }

    private int oldW4NumOfAllowances;

    /**
     * Get the value of oldW4NumOfAllowances
     *
     * @return the value of oldW4NumOfAllowances
     */
    public int getOldW4NumOfAllowances() {
        return oldW4NumOfAllowances;
    }

    /**
     * Set the value of oldW4NumOfAllowances
     *
     * @param oldW4NumOfAllowances new value of oldW4NumOfAllowances
     */
    public void setOldW4NumOfAllowances(int oldW4NumOfAllowances) {
        this.oldW4NumOfAllowances = oldW4NumOfAllowances;
    }

    public static final int OLD_W4_SINGLE = 0;
    public static final int OLD_W4_MARRIED = 1;
    public static final int OLD_W4_MARRIED_HIGH_WH = 2;
    private int oldW4FilingStatus = OLD_W4_SINGLE;

    /**
     * Get the value of oldW4FilingStatus
     *
     * @return the value of oldW4FilingStatus
     */
    public int getOldW4FilingStatus() {
        return oldW4FilingStatus;
    }

    /**
     * Set the value of oldW4FilingStatus
     *
     * @param oldW4FilingStatus new value of oldW4FilingStatus
     */
    public void setOldW4FilingStatus(int oldW4FilingStatus) {
        this.oldW4FilingStatus = oldW4FilingStatus;
    }

    private int oldW4AdduitionWithHold = 0;

    /**
     * Get the value of oldW4AdduitionWithHold
     *
     * @return the value of oldW4AdduitionWithHold
     */
    public int getOldW4AdduitionWithHold() {
        return oldW4AdduitionWithHold;
    }

    /**
     * Set the value of oldW4AdduitionWithHold
     *
     * @param oldW4AdduitionWithHold new value of oldW4AdduitionWithHold
     */
    public void setOldW4AdduitionWithHold(int oldW4AdduitionWithHold) {
        this.oldW4AdduitionWithHold = oldW4AdduitionWithHold;
    }

    public enum FilingStatus {
        S,
        M,
        H
    }

    private FilingStatus new2020W4FilingStatus = FilingStatus.S;

    /**
     * Get the value of new2020W4FilingStatus
     *
     * @return the value of new2020W4FilingStatus
     */
    public FilingStatus getNew2020W4FilingStatus() {
        return new2020W4FilingStatus;
    }

    /**
     * Set the value of new2020W4FilingStatus
     *
     * @param new2020W4FilingStatus new value of new2020W4FilingStatus
     */
    public void setNew2020W4FilingStatus(FilingStatus new2020W4FilingStatus) {
        this.new2020W4FilingStatus = new2020W4FilingStatus;
    }

    private boolean new2020W4MultipleJobs = false;

    /**
     * Get the value of new2020W4MultipleJobs
     *
     * @return the value of new2020W4MultipleJobs
     */
    public boolean isNew2020W4MultipleJobs() {
        return new2020W4MultipleJobs;
    }

    /**
     * Set the value of new2020W4MultipleJobs
     *
     * @param new2020W4MultipleJobs new value of new2020W4MultipleJobs
     */
    public void setNew2020W4MultipleJobs(boolean new2020W4MultipleJobs) {
        this.new2020W4MultipleJobs = new2020W4MultipleJobs;
    }

    private int new2020W4Step3Line3Dependents = 0;

    /**
     * Get the value of new2020W4Step3Line3Dependents
     *
     * @return the value of new2020W4Step3Line3Dependents
     */
    public int getNew2020W4Step3Line3Dependents() {
        return new2020W4Step3Line3Dependents;
    }

    /**
     * Set the value of new2020W4Step3Line3Dependents
     *
     * @param new2020W4Step3Line3Dependents new value of
     * new2020W4Step3Line3Dependents
     */
    public void setNew2020W4Step3Line3Dependents(int new2020W4Step3Line3Dependents) {
        this.new2020W4Step3Line3Dependents = new2020W4Step3Line3Dependents;
    }

    private int new2020W4Step4Line4aOtherIncome = 0;

    /**
     * Get the value of new2020W4Step4Line4aOtherIncome
     *
     * @return the value of new2020W4Step4Line4aOtherIncome
     */
    public int getNew2020W4Step4Line4aOtherIncome() {
        return new2020W4Step4Line4aOtherIncome;
    }

    /**
     * Set the value of new2020W4Step4Line4aOtherIncome
     *
     * @param new2020W4Step4Line4aOtherIncome new value of
     * new2020W4Step4Line4aOtherIncome
     */
    public void setNew2020W4Step4Line4aOtherIncome(int new2020W4Step4Line4aOtherIncome) {
        this.new2020W4Step4Line4aOtherIncome = new2020W4Step4Line4aOtherIncome;
    }
    private int new2020W4Step4Line4bDeductions = 0;

    /**
     * Get the value of new2020W4Step4Line4bDeductions
     *
     * @return the value of new2020W4Step4Line4bDeductions
     */
    public int getNew2020W4Step4Line4bDeductions() {
        return new2020W4Step4Line4bDeductions;
    }

    /**
     * Set the value of new2020W4Step4Line4bDeductions
     *
     * @param new2020W4Step4Line4bDeductions new value of
     * new2020W4Step4Line4bDeductions
     */
    public void setNew2020W4Step4Line4bDeductions(int new2020W4Step4Line4bDeductions) {
        this.new2020W4Step4Line4bDeductions = new2020W4Step4Line4bDeductions;
    }

    private int new2020W4Step4Line4cExtraWithHolding = 0;

    /**
     * Get the value of new2020W4Step4Line4cExtraWithHolding
     *
     * @return the value of new2020W4Step4Line4cExtraWithHolding
     */
    public int getNew2020W4Step4Line4cExtraWithHolding() {
        return new2020W4Step4Line4cExtraWithHolding;
    }

    /**
     * Set the value of new2020W4Step4Line4cExtraWithHolding
     *
     * @param new2020W4Step4Line4cExtraWithHolding new value of
     * new2020W4Step4Line4cExtraWithHolding
     */
    public void setNew2020W4Step4Line4cExtraWithHolding(int new2020W4Step4Line4cExtraWithHolding) {
        this.new2020W4Step4Line4cExtraWithHolding = new2020W4Step4Line4cExtraWithHolding;
    }

    private int employee_id;

    /**
     * Get the value of employee_id
     *
     * @return the value of employee_id
     */
    public int getEmployee_id() {
        return employee_id;
    }

    /**
     * Set the value of employee_id
     *
     * @param employee_id new value of employee_id
     */
    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

}
