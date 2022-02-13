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

import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author Neel
 */
public class CompanyInfoModel {

    public enum PayrollFrequency {
        Daily,
        Weekly,
        BiWeekly,
        SemiMonthly,
        Monthly,
        Quarterly,
        Annually
    }

    private HashMap<Integer, CompanyEmployeePayrollRatesModel> hmapYearToPayrollTax = new HashMap<Integer, CompanyEmployeePayrollRatesModel>();

    /**
     * Get the value of hmapYearToPayrollTax
     *
     * @return the value of hmapYearToPayrollTax
     */
    public HashMap<Integer, CompanyEmployeePayrollRatesModel> getYearToPayrollTax() {
        return hmapYearToPayrollTax;
    }

    /**
     * Set the value of hmapYearToPayrollTax
     *
     * @param hmapYearToPayrollTax new value of hmapYearToPayrollTax
     */
    public void setYearToPayrollTax(HashMap<Integer, CompanyEmployeePayrollRatesModel> mapYearToPayrollTax) {
        this.hmapYearToPayrollTax = mapYearToPayrollTax;
    }

    private PayrollFrequency payrollFrequency = PayrollFrequency.BiWeekly;

    /**
     * Get the value of payrollFrequency
     *
     * @return the value of payrollFrequency
     */
    public PayrollFrequency getPayrollFrequency() {
        return payrollFrequency;
    }

    /**
     * Set the value of payrollFrequency
     *
     * @param payrollFrequency new value of payrollFrequency
     */
    public void setPayrollFrequency(PayrollFrequency payrollFrequency) {
        this.payrollFrequency = payrollFrequency;
    }

    private String companyName;
    private String federalEIN;
    private String stateID;

    /**
     * Get the value of federalEIN
     *
     * @return the value of federalEIN
     */
    public String getFederalEIN() {
        return federalEIN;
    }

    /**
     * Set the value of federalEIN
     *
     * @param federalEIN new value of federalEIN
     */
    public void setFederalEIN(String federalEIN) {
        this.federalEIN = federalEIN;
    }

    /**
     * Get the value of stateID
     *
     * @return the value of stateID
     */
    public String getStateID() {
        return stateID;
    }

    /**
     * Set the value of stateID
     *
     * @param stateID new value of stateID
     */
    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    /**
     * Get the value of companyName
     *
     * @return the value of companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Set the value of companyName
     *
     * @param companyName new value of companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate GetPayrollEndDate(LocalDate startDate) {
        LocalDate endDate = startDate;
        switch (payrollFrequency) {
            case Daily:
                endDate = startDate.plusDays(1);
                break;

            case Weekly:
                endDate = startDate.plusWeeks(1);
                break;

            case BiWeekly:
                endDate = startDate.plusWeeks(2);

                break;

            case SemiMonthly:
                endDate = startDate.plusDays(15);
                break;

            case Monthly:
                endDate = startDate.plusMonths(1);
                break;

            case Quarterly:
                endDate = startDate.plusMonths(3);
                break;

            case Annually:
                endDate = startDate.plusYears(1);
                break;

        }

        endDate = endDate.minusDays(1);
        return endDate;
    }

}
