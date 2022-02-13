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

import com.neel.payroll.Model.TimeSheetOneEmployeeModel;
import java.util.Vector;

/**
 *
 * @author Neel
 */
public class OnePayPeriodAllEmpoyeesTimeSheets {

    private long startDate = 0;

    private long endDate = 0;

    private Vector<TimeSheetOneEmployeeModel> vctEmployeeTimeSheet = new Vector<TimeSheetOneEmployeeModel>();

    public Vector<TimeSheetOneEmployeeModel> getOnePayPeriodAllEmployeeTimeSheets() {
        return vctEmployeeTimeSheet;
    }

    public void setOnePayPeriodAllEmployeeTimeSheets(Vector<TimeSheetOneEmployeeModel> vctEmployeeTimeSheet) {
        this.vctEmployeeTimeSheet = vctEmployeeTimeSheet;
    }

    /**
     * Get the value of endDate
     *
     * @return the value of endDate
     */
    public long getEndDate() {
        return endDate;
    }

    /**
     * Set the value of endDate
     *
     * @param endDate new value of endDate
     */
    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    /**
     * Get the value of startDate
     *
     * @return the value of startDate
     */
    public long getStartDate() {
        return startDate;
    }

    /**
     * Set the value of startDate
     *
     * @param startDate new value of startDate
     */
    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

}
