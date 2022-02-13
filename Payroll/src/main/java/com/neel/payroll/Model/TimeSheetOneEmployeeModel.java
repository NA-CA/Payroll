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
public class TimeSheetOneEmployeeModel {

    private int employeeId;

    /**
     * Get the value of employeeId
     *
     * @return the value of employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Set the value of employeeId
     *
     * @param employeeId new value of employeeId
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Get the value of hours
     *
     * @return the value of hours
     */
    public float getHours() {
        return hours;
    }

    /**
     * Set the value of hours
     *
     * @param hours new value of hours
     */
    public void setHours(float hours) {
        this.hours = hours;
    }

    /**
     * Get the value of lastname
     *
     * @return the value of lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set the value of lastname
     *
     * @param lastname new value of lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Get the value of firstname
     *
     * @return the value of firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set the value of firstname
     *
     * @param firstname new value of firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private float hours = 0F;
    private String firstname;
    private String lastname;
}
