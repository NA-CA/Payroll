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
public class EmployeeCA_DE4Model {

    private int employee_id = 0;

    private int Line1ARegularStdAllowance = 0;
    private boolean exempt = false;
    private int Line1B_EstimatedDedAllowance = 0;
    EmployeeW4Model.FilingStatus filingStatus = EmployeeW4Model.FilingStatus.S;

    /**
     * Get the value of Line1B_EstimatedDedAllowance
     *
     * @return the value of Line1B_EstimatedDedAllowance
     */
    public int getLine1B_EstimatedDeduction() {
        return Line1B_EstimatedDedAllowance;
    }

    /**
     * Set the value of Line1B_EstimatedDedAllowance
     *
     * @param Line1B_EstimatedDeduction new value of
     * Line1B_EstimatedDedAllowance
     */
    public void setLine1B_EstimatedDeduction(int Line1B_EstimatedDeduction) {
        this.Line1B_EstimatedDedAllowance = Line1B_EstimatedDeduction;
    }

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

    /**
     * Get the value of Line1ARegularStdAllowance
     *
     * @return the value of Line1ARegularStdAllowance
     */
    public int getLine1ARegularStdAllowance() {
        return Line1ARegularStdAllowance;
    }

    /**
     * Set the value of Line1ARegularStdAllowance
     *
     * @param Line1ARegularStdAllowance new value of Line1ARegularStdAllowance
     */
    public void setLine1ARegularStdAllowance(int Line1ARegularStdAllowance) {
        this.Line1ARegularStdAllowance = Line1ARegularStdAllowance;
    }

    public EmployeeW4Model.FilingStatus getFilingStatus() {
        return filingStatus;
    }

    public void setFilingStatus(EmployeeW4Model.FilingStatus filingStatus) {
        this.filingStatus = filingStatus;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }
}
