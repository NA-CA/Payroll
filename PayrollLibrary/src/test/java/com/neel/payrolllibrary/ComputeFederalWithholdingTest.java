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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputeFederalWithholdingTest
{
    private  ComputeFederalWithholding CreateStdYr202NewW4(int payYear, int wages, PayrollPeriodEnum payFrequency,  FederalFilingStatusEnum filingStatus) throws Exception
    {
        ComputeFederalWithholding fwh = new ComputeFederalWithholding(payYear, wages, payFrequency, true, 0,
                0, false, 0, filingStatus,
                true, 0, 0, 0, PayrollLibUnitTestUtil.getDataPath(), false);
        return fwh;
    }

    private  ComputeFederalWithholding CreateWithStep2CheckedYr202NewW4(int payYear, int wages, PayrollPeriodEnum payFrequency,  FederalFilingStatusEnum filingStatus) throws Exception
    {
        ComputeFederalWithholding fwh = new ComputeFederalWithholding(payYear, wages, payFrequency, true, 0,
                0, true, 0, filingStatus,
                true, 0, 0, 0, PayrollLibUnitTestUtil.getDataPath(), false);
        return fwh;
    }

    //-------------------------------------- Bi weekly new standard ------------------------------------------------
    @org.junit.jupiter.api.Test
    @DisplayName("Testing pay year 2020 new W4, biweekly, head household, standard with holding")
    void federalWitholdingNewW4HeadHousehold() throws Exception
    {
        ComputeFederalWithholding fwh = CreateStdYr202NewW4(2020, 2885, PayrollPeriodEnum.BI_WEEKLY,  FederalFilingStatusEnum.HEADOFHOUSEHOLD);
        double fwhDollar = fwh.federalWitholding();
        System.out.println(fwhDollar);
        assertTrue(fwhDollar >= 256 && fwhDollar <= 257, "new w4 standard HH");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testing pay year 2020 new W4, biweekly, single , standard with holding")
    void federalWitholdingNewW4Single() throws Exception
    {
        ComputeFederalWithholding fwh = CreateStdYr202NewW4(2020, 2885, PayrollPeriodEnum.BI_WEEKLY,  FederalFilingStatusEnum.SINGLE_OR_MARRIEDFILINGSEPERATELY);
        double fwhDollar = fwh.federalWitholding();
        System.out.println(fwhDollar);
        assertTrue(fwhDollar >= 364 && fwhDollar <= 365, "new w4 standard single");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testing pay year 2020 new W4, biweekly, married , standard with holding")
    void federalWitholdingNewW4Married() throws Exception
    {
        ComputeFederalWithholding fwh = CreateStdYr202NewW4(2020, 2885, PayrollPeriodEnum.BI_WEEKLY,  FederalFilingStatusEnum.MARRIEDFILINGJOINTLY_OR_QUALIFYINGWIDOWER);
        double fwhDollar = fwh.federalWitholding();
        System.out.println(fwhDollar);
        assertTrue(fwhDollar >= 215 && fwhDollar <= 216, "new w4 standard Married");
    }

    //-------------------------------------- Bi weekly new step 2 ------------------------------------------------
    @org.junit.jupiter.api.Test
    @DisplayName("Testing pay year 2020 new W4, biweekly, head household, step 2  with holding")
    void federalWitholdingNewW4HeadHouseholdStep2() throws Exception
    {
        ComputeFederalWithholding fwh = CreateWithStep2CheckedYr202NewW4(2020, 2885, PayrollPeriodEnum.BI_WEEKLY,  FederalFilingStatusEnum.HEADOFHOUSEHOLD);
        double fwhDollar = fwh.federalWitholding();
        System.out.println(fwhDollar);
        assertTrue(fwhDollar >= 461 && fwhDollar <= 463, "new w4 step 2  HH");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testing pay year 2020 new W4, biweekly, single , step 2 with holding")
    void federalWitholdingNewW4SingleStep2() throws Exception
    {
        ComputeFederalWithholding fwh = CreateWithStep2CheckedYr202NewW4(2020, 2885, PayrollPeriodEnum.BI_WEEKLY,  FederalFilingStatusEnum.SINGLE_OR_MARRIEDFILINGSEPERATELY);
        double fwhDollar = fwh.federalWitholding();
        System.out.println(fwhDollar);
        assertTrue(fwhDollar >= 519 && fwhDollar <= 520, "new w4 step 2 single");
    }
    @org.junit.jupiter.api.Test
    @DisplayName("Testing pay year 2020 new W4, biweekly, married , Step 2 with holding")
    void federalWitholdingNewW4MarriedStep2() throws Exception
    {
        ComputeFederalWithholding fwh = CreateWithStep2CheckedYr202NewW4(2020, 2885, PayrollPeriodEnum.BI_WEEKLY,  FederalFilingStatusEnum.MARRIEDFILINGJOINTLY_OR_QUALIFYINGWIDOWER);
        double fwhDollar = fwh.federalWitholding();
        System.out.println(fwhDollar);
        assertTrue(fwhDollar >= 364 && fwhDollar <= 365, "new w4 step2 Married");
    }

    @Test
     void UnitTestFederalWitholding1() throws Exception
    {
        ComputeFederalWithholding computeFederalWithholding = new ComputeFederalWithholding(2020, 736,PayrollPeriodEnum.WEEKLY,Boolean.FALSE,0,0,Boolean.FALSE,2,
                FederalFilingStatusEnum.MARRIEDFILINGJOINTLY_OR_QUALIFYINGWIDOWER, true, 0,0,0, PayrollLibUnitTestUtil.getDataPath(), Boolean.FALSE);
        double federalWitholding = computeFederalWithholding.federalWitholding();

        System.out.println(federalWitholding);
        federalWitholding = Math.round(federalWitholding * 100.0)/100.0;

        assertTrue(federalWitholding >= 33.6 && federalWitholding <= 33.6, "new w4 standard HH");
    }

    @Test
     void UnitTestFederalWitholding2() throws Exception
    {
        ComputeFederalWithholding computeFederalWithholding = new ComputeFederalWithholding(2020, 2001,PayrollPeriodEnum.SEMI_MONTHLY,Boolean.TRUE,0,0,Boolean.FALSE,2, FederalFilingStatusEnum.HEADOFHOUSEHOLD,
                true,0,0,0, PayrollLibUnitTestUtil.getDataPath(), Boolean.FALSE);
        double federalWitholding = computeFederalWithholding.federalWitholding();

        System.out.println(federalWitholding);
        federalWitholding = Math.round(federalWitholding * 100.0)/100.0;

        assertTrue(federalWitholding >= 134.29 && federalWitholding <= 134.29, "new w4 standard HH");
    }

    @Test
     void UnitTestFederalWitholding3() throws Exception
    {
        ComputeFederalWithholding computeFederalWithholding = new ComputeFederalWithholding(2020,2005,PayrollPeriodEnum.MONTHLY,Boolean.TRUE,0,0,Boolean.FALSE,0, FederalFilingStatusEnum.SINGLE_OR_MARRIEDFILINGSEPERATELY,
                true,0,0,0, PayrollLibUnitTestUtil.getDataPath(), Boolean.FALSE);
        double federalWitholding = computeFederalWithholding.federalWitholding();

        System.out.println(federalWitholding);
        federalWitholding = Math.round(federalWitholding * 100.0)/100.0;

        assertTrue(federalWitholding >= 98.52 && federalWitholding <= 98.52, "new w4 standard HH");
    }



    @Test
     void UnitTestFederalWitholding4() throws Exception
    {
        ComputeFederalWithholding computeFederalWithholding = new ComputeFederalWithholding(2020, 30000,PayrollPeriodEnum.DAILY_MISC,Boolean.TRUE,4000,0,
                Boolean.TRUE,0, FederalFilingStatusEnum.HEADOFHOUSEHOLD, true,0,0,0, PayrollLibUnitTestUtil.getDataPath(), Boolean.FALSE);
        double federalWitholding = computeFederalWithholding.federalWitholding();

        System.out.println(federalWitholding);
        federalWitholding = Math.round(federalWitholding * 100.0)/100.0;


        assertTrue(federalWitholding >= 11020.44 && federalWitholding <= 11020.44, "new w4 standard HH");
    }


    @Test

     void UnitTestFederalWitholding5() throws Exception
    {
        ComputeFederalWithholding computeFederalWithholding = new ComputeFederalWithholding(2020, 1004,PayrollPeriodEnum.WEEKLY,Boolean.TRUE,0,0,
                Boolean.FALSE,0, FederalFilingStatusEnum.HEADOFHOUSEHOLD, true,0,0,0, PayrollLibUnitTestUtil.getDataPath(), Boolean.FALSE);
        double federalWitholding = computeFederalWithholding.federalWitholding();

        System.out.println(federalWitholding);
        federalWitholding = Math.round(federalWitholding * 100.0)/100.0;


        assertTrue(federalWitholding >= 71.63 && federalWitholding <= 71.63, "new w4 standard HH");

    }

}