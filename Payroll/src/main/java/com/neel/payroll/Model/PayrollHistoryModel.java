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
public class PayrollHistoryModel {

    private int employee_id = 0;

    private String first_name;

    private String last_name;

    private int pay_year = 0;

    private long start_date = 0;

    private long end_date = 0;

    private float hour = 0.0F;

    private float hourly_rate = 0.0F;

    private float employee_gross_wages = 0.0F;

    private float employee_401k = 0.0F;

    private float employee_state_wh = 0.0F;

    private float employee_federal_wh = 0.0F;

    private float employee_medicare = 0.0F;

    private float employee_ss = 0.0F;

    private float employee_sdi = 0.0F;

    private float company_futa = 0.0F;

    private float company_medicare = 0.0F;

    private float company_ss = 0.0F;

    private int pay_month_day = 0;

    private int pay_month = 0;

    /**
     * Get the value of pay_date
     *
     * @return the value of pay_date
     */
    public int getPayMonthDay() {
        return pay_month_day;
    }

    /**
     * Set the value of pay_date
     *
     * @param pay_date new value of pay_date
     */
    public void setPayMonthDay(int payMonthdate) {
        this.pay_month_day = payMonthdate;
    }

    public int getPay_month() {
        return pay_month;
    }

    public void setPay_month(int pay_month) {
        this.pay_month = pay_month;
    }

    /**
     * Get the value of pay_year
     *
     * @return the value of pay_year
     */
    public int getPayYear() {
        return pay_year;
    }

    /**
     * Set the value of pay_year
     *
     * @param year new value of pay_year
     */
    public void setPayYear(int payYear) {
        this.pay_year = payYear;
    }

    /**
     * Get the value of company_ss
     *
     * @return the value of company_ss
     */
    public float getCompany_ss() {
        return company_ss;
    }

    /**
     * Set the value of company_ss
     *
     * @param company_ss new value of company_ss
     */
    public void setCompany_ss(float company_ss) {
        this.company_ss = company_ss;
    }

    /**
     * Get the value of company_medicare
     *
     * @return the value of company_medicare
     */
    public float getCompany_medicare() {
        return company_medicare;
    }

    /**
     * Set the value of company_medicare
     *
     * @param company_medicare new value of company_medicare
     */
    public void setCompany_medicare(float company_medicare) {
        this.company_medicare = company_medicare;
    }

    /**
     * Get the value of company_futa
     *
     * @return the value of company_futa
     */
    public float getCompany_futa() {
        return company_futa;
    }

    /**
     * Set the value of company_futa
     *
     * @param company_futa new value of company_futa
     */
    public void setCompany_futa(float company_futa) {
        this.company_futa = company_futa;
    }

    /**
     * Get the value of employee_sdi
     *
     * @return the value of employee_sdi
     */
    public float getEmployee_sdi() {
        return employee_sdi;
    }

    /**
     * Set the value of employee_sdi
     *
     * @param employee_sdi new value of employee_sdi
     */
    public void setEmployee_sdi(float employee_sdi) {
        this.employee_sdi = employee_sdi;
    }

    /**
     * Get the value of employee_ss
     *
     * @return the value of employee_ss
     */
    public float getEmployee_ss() {
        return employee_ss;
    }

    /**
     * Set the value of employee_ss
     *
     * @param employee_ss new value of employee_ss
     */
    public void setEmployee_ss(float employee_ss) {
        this.employee_ss = employee_ss;
    }

    /**
     * Get the value of employee_medicare
     *
     * @return the value of employee_medicare
     */
    public float getEmployee_medicare() {
        return employee_medicare;
    }

    /**
     * Set the value of employee_medicare
     *
     * @param employee_medicare new value of employee_medicare
     */
    public void setEmployee_medicare(float employee_medicare) {
        this.employee_medicare = employee_medicare;
    }

    /**
     * Get the value of employee_federal_wh
     *
     * @return the value of employee_federal_wh
     */
    public float getEmployee_federal_wh() {
        return employee_federal_wh;
    }

    /**
     * Set the value of employee_federal_wh
     *
     * @param employee_federal_wh new value of employee_federal_wh
     */
    public void setEmployee_federal_wh(float employee_federal_wh) {
        this.employee_federal_wh = employee_federal_wh;
    }

    /**
     * Get the value of employee_state_wh
     *
     * @return the value of employee_state_wh
     */
    public float getEmployee_state_wh() {
        return employee_state_wh;
    }

    /**
     * Set the value of employee_state_wh
     *
     * @param employee_state_wh new value of employee_state_wh
     */
    public void setEmployee_state_wh(float employee_state_wh) {
        this.employee_state_wh = employee_state_wh;
    }

    /**
     * Get the value of employee_401k
     *
     * @return the value of employee_401k
     */
    public float getEmployee_401k() {
        return employee_401k;
    }

    /**
     * Set the value of employee_401k
     *
     * @param employee_401k new value of employee_401k
     */
    public void setEmployee_401k(float employee_401k) {
        this.employee_401k = employee_401k;
    }

    /**
     * Get the value of employee_gross_wages
     *
     * @return the value of employee_gross_wages
     */
    public float getEmployee_gross_wages() {
        return employee_gross_wages;
    }

    /**
     * Set the value of employee_gross_wages
     *
     * @param employee_gross_wages new value of employee_gross_wages
     */
    public void setEmployee_gross_wages(float employee_gross_wages) {
        this.employee_gross_wages = employee_gross_wages;
    }

    /**
     * Get the value of hourly_rate
     *
     * @return the value of hourly_rate
     */
    public float getHourly_rate() {
        return hourly_rate;
    }

    /**
     * Set the value of hourly_rate
     *
     * @param hourly_rate new value of hourly_rate
     */
    public void setHourly_rate(float hourly_rate) {
        this.hourly_rate = hourly_rate;
    }

    /**
     * Get the value of hour
     *
     * @return the value of hour
     */
    public float getHour() {
        return hour;
    }

    /**
     * Set the value of hour
     *
     * @param hour new value of hour
     */
    public void setHour(float hour) {
        this.hour = hour;
    }

    /**
     * Get the value of end_date
     *
     * @return the value of end_date
     */
    public long getEnd_date() {
        return end_date;
    }

    /**
     * Set the value of end_date
     *
     * @param end_date new value of end_date
     */
    public void setEnd_date(long end_date) {
        this.end_date = end_date;
    }

    /**
     * Get the value of start_date
     *
     * @return the value of start_date
     */
    public long getStart_date() {
        return start_date;
    }

    /**
     * Set the value of start_date
     *
     * @param start_date new value of start_date
     */
    public void setStart_date(long start_date) {
        this.start_date = start_date;
    }

    /**
     * Get the value of last_name
     *
     * @return the value of last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Set the value of last_name
     *
     * @param last_name new value of last_name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Get the value of first_name
     *
     * @return the value of first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Set the value of first_name
     *
     * @param first_name new value of first_name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

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
