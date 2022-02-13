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
public class EmployeeInfoModel {

    private int id = -1;

    private String firstname;
    private String lastname;

    private int ssn = 0;
    private float salary_per_hour = 0.0F;

    private float employee401KDefRate = 0.0F;

    private boolean age50Plus = false;

    /**
     * Get the value of age50Plus
     *
     * @return the value of age50Plus
     */
    public boolean isAge50Plus() {
        return age50Plus;
    }

    /**
     * Set the value of age50Plus
     *
     * @param age50Plus new value of age50Plus
     */
    public void setAge50Plus(boolean age50Plus) {
        this.age50Plus = age50Plus;
    }

    /**
     * Get the value of employee401KDefRate
     *
     * @return the value of employee401KDefRate
     */
    public float getEmployee401KDefRate() {
        return employee401KDefRate;
    }

    /**
     * Set the value of employee401KDefRate
     *
     * @param employee401KDefRate new value of employee401KDefRate
     */
    public void setEmployee401KDefRate(float employee401KDefRate) {
        this.employee401KDefRate = employee401KDefRate;
    }

    /**
     * Get the value of salary_per_hour
     *
     * @return the value of salary_per_hour
     */
    public float getSalary_per_hour() {
        return salary_per_hour;
    }

    /**
     * Set the value of salary_per_hour
     *
     * @param salary_per_hour new value of salary_per_hour
     */
    public void setSalary_per_hour(float salary_per_hour) {
        this.salary_per_hour = salary_per_hour;
    }

    /**
     * Get the value of ssn
     *
     * @return the value of ssn
     */
    public int getSsn() {
        return ssn;
    }

    /**
     * Set the value of ssn
     *
     * @param ssn new value of ssn
     */
    public void setSsn(int ssn) {
        this.ssn = ssn;
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

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

    private EmployeeW4Model employeeW4 = new EmployeeW4Model();

    /**
     * Get the value of employeeW4
     *
     * @return the value of employeeW4
     */
    public EmployeeW4Model getEmployeeW4() {
        return employeeW4;
    }

    /**
     * Set the value of employeeW4
     *
     * @param employeeW4 new value of employeeW4
     */
    public void setEmployeeW4(EmployeeW4Model employeeW4) {
        this.employeeW4 = employeeW4;
    }

}
