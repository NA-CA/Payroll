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

package com.neel.payrolllibrary.ca;

import com.neel.payrolllibrary.PayrollLibUnitTestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaliforniaWitholdingTest
{

    @Test
     void UnitTestComputeExactCalculationMethod1() throws Exception
    {
        double computedTax = 0.0;

        CaliforniaWitholding californiaWitholding = new CaliforniaWitholding(2021, CAFilingStatusEnum.SINGLE, CAPayrollPeriodEnum.ANNUAL, PayrollLibUnitTestUtil.getDataPath());
        californiaWitholding.initialize();

        computedTax = californiaWitholding.computedTax(20000);


        computedTax = Math.round(computedTax * 100.0)/100.0;
        System.out.println(computedTax);



        assertTrue(computedTax >= 341.75 && computedTax <= 341.75, "new w4 standard HH");

    }

    @Test
     void UnitTestComputeExactCalculationMethod2() throws Exception
    {
        double computedTax = 0.0;

        CaliforniaWitholding californiaWitholding = new CaliforniaWitholding(2021, CAFilingStatusEnum.MARRIED, CAPayrollPeriodEnum.ANNUAL, PayrollLibUnitTestUtil.getDataPath());
        californiaWitholding.initialize();

        computedTax = californiaWitholding.computedTax(40000);

        computedTax = Math.round(computedTax * 100.0)/100.0;
        System.out.println(computedTax);

        assertTrue(computedTax >= 683.5 && computedTax <= 683.5, "new w4 standard HH");

    }
    
    @Test

     void UnitTestComputeExactCalculationMethod3() throws Exception
    {
        double computedTax = 0.0;

        CaliforniaWitholding californiaWitholding = new CaliforniaWitholding(2021, CAFilingStatusEnum.HEADOFHOUSEHOLD, CAPayrollPeriodEnum.ANNUAL, PayrollLibUnitTestUtil.getDataPath());
        californiaWitholding.initialize();

        computedTax = californiaWitholding.computedTax(42353);
        computedTax = Math.round(computedTax * 100.0)/100.0;
        System.out.println(computedTax);

        assertTrue(computedTax >= 735.13 && computedTax <= 735.13, "new w4 standard HH");

    }



    @Test
     void UnitTestComputeExactCalculationMethod4() throws Exception
    {
        double computedTax = 0.0;

        CaliforniaWitholding californiaWitholding = new CaliforniaWitholding(2021, CAFilingStatusEnum.SINGLE, CAPayrollPeriodEnum.ANNUAL, PayrollLibUnitTestUtil.getDataPath());
        californiaWitholding.initialize();

        computedTax = californiaWitholding.computedTax(1000001);
        computedTax = Math.round(computedTax * 100.0)/100.0;
        System.out.println(computedTax);

        assertTrue(computedTax >= 118304.45 && computedTax <= 118304.45, "new w4 standard HH");

    }
    
    @Test

     void UnitTestComputeExactCalculationMethod5() throws Exception
    {
        double computedTax = 0.0;

        CaliforniaWitholding californiaWitholding = new CaliforniaWitholding(2021, CAFilingStatusEnum.HEADOFHOUSEHOLD, CAPayrollPeriodEnum.ANNUAL, PayrollLibUnitTestUtil.getDataPath());
        californiaWitholding.initialize();

        computedTax = californiaWitholding.computedTax(1000000);
        computedTax = Math.round(computedTax * 100.0)/100.0;
        System.out.println(computedTax);
        

        assertTrue( computedTax >= 111524.02 &&  computedTax <= 111524.02, "new w4 standard HH");

    }
    
    @Test

     void UnitTestComputeExactCalculationMethod6() throws Exception
    {
        double computedTax = 0.0;

        CaliforniaWitholding californiaWitholding = new CaliforniaWitholding(2021, CAFilingStatusEnum.SINGLE, CAPayrollPeriodEnum.ANNUAL, PayrollLibUnitTestUtil.getDataPath());
        californiaWitholding.initialize();

        computedTax = californiaWitholding.computedTax(599012);
        computedTax = Math.round(computedTax * 100.0)/100.0;
        System.out.println(computedTax);



        assertTrue( computedTax >= 64050.63 &&  computedTax <= 64050.63, "new w4 standard HH");

    }
    
    @Test

     void UnitTestComputeExactCalculationMethod7() throws Exception
    {
        double computedTax = 0.0;

        CaliforniaWitholding californiaWitholding = new CaliforniaWitholding(2021, CAFilingStatusEnum.SINGLE, CAPayrollPeriodEnum.DAILY_MISC, PayrollLibUnitTestUtil.getDataPath());
        californiaWitholding.initialize();

        computedTax = californiaWitholding.computedTax(200);
        computedTax = Math.round(computedTax * 100.0)/100.0;
        System.out.println(computedTax);
        

        assertTrue( computedTax >= 8.69 &&  computedTax <= 8.69, "new w4 standard HH");

    }
    
    @Test

     void UnitTestComputeExactCalculationMethod8() throws Exception
    {
        double computedTax = 0.0;

        CaliforniaWitholding californiaWitholding = new CaliforniaWitholding(2021, CAFilingStatusEnum.SINGLE, CAPayrollPeriodEnum.DAILY_MISC, PayrollLibUnitTestUtil.getDataPath());
        californiaWitholding.initialize();

        computedTax = californiaWitholding.computedTax(200);
        computedTax = Math.round(computedTax * 100.0)/100.0;
        System.out.println(computedTax);
        

        assertTrue( computedTax >= 8.69 &&  computedTax <= 8.69, "new w4 standard HH");

    }
    
    @Test

     void UnitTestComputeExactCalculationMethod9() throws Exception
    {
        double computedTax = 0.0;

        CaliforniaWitholding californiaWitholding = new CaliforniaWitholding(2021, CAFilingStatusEnum.SINGLE, CAPayrollPeriodEnum.BI_WEEKLY, PayrollLibUnitTestUtil.getDataPath());
        californiaWitholding.initialize();

        computedTax = californiaWitholding.computedTax(23038);
        computedTax = Math.round(computedTax * 100.0)/100.0;
        System.out.println(computedTax);
        

        assertTrue( computedTax >= 2463.37 &&  computedTax <= 2463.37, "new w4 standard HH");

    }

    @Test
     void UnitTestComputeExactCalculationMethod10() throws Exception
    {
        double computedTax = 0.0;

        CaliforniaWitholding californiaWitholding = new CaliforniaWitholding(2021, CAFilingStatusEnum.SINGLE, CAPayrollPeriodEnum.QUARTERLY, PayrollLibUnitTestUtil.getDataPath());
        californiaWitholding.initialize();

        computedTax = californiaWitholding.computedTax(250000);
        computedTax = Math.round(computedTax * 100.0)/100.0;
        System.out.println(computedTax);

        assertTrue( computedTax >= 29576.08 &&  computedTax <= 29576.08, "new w4 standard HH");


    }
}