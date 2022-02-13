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

class CAComputeExemptionAllowanceTableTest
{

    @Test
     void UnitTestComputeExemptionAllowanceTable1() throws Exception
    {
        CAComputeExemptionAllowanceTable CAComputeExemptionAllowanceTable = new CAComputeExemptionAllowanceTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeExemptionAllowanceTable.initialize();

        double estimatedDeduction = CAComputeExemptionAllowanceTable.estimatedExemptionAllowance(3, CAPayrollPeriodEnum.WEEKLY);
        System.out.println(estimatedDeduction);

        assertTrue(estimatedDeduction >= 7.87 && estimatedDeduction <= 7.87, "new w4 standard HH");
    }
    
    @Test
     void UnitTestComputeExemptionAllowanceTable2() throws Exception
    {
        CAComputeExemptionAllowanceTable CAComputeExemptionAllowanceTable = new CAComputeExemptionAllowanceTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeExemptionAllowanceTable.initialize();

        double estimatedDeduction = CAComputeExemptionAllowanceTable.estimatedExemptionAllowance(9, CAPayrollPeriodEnum.MONTHLY);
        System.out.println(estimatedDeduction);

        assertTrue(estimatedDeduction >= 102.30 && estimatedDeduction <= 102.30, "new w4 standard HH");
    }

    @Test
     void UnitTestComputeExemptionAllowanceTable3() throws Exception
    {
        CAComputeExemptionAllowanceTable CAComputeExemptionAllowanceTable = new CAComputeExemptionAllowanceTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeExemptionAllowanceTable.initialize();

        double estimatedDeduction = CAComputeExemptionAllowanceTable.estimatedExemptionAllowance(7, CAPayrollPeriodEnum.BI_WEEKLY);
        System.out.println(estimatedDeduction);

        assertTrue(estimatedDeduction >= 36.72 && estimatedDeduction <= 36.72, "new w4 standard HH");
    }

    @Test
     void UnitTestComputeExemptionAllowanceTable4() throws Exception
    {
        CAComputeExemptionAllowanceTable CAComputeExemptionAllowanceTable = new CAComputeExemptionAllowanceTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeExemptionAllowanceTable.initialize();

        double estimatedDeduction = CAComputeExemptionAllowanceTable.estimatedExemptionAllowance(3, CAPayrollPeriodEnum.SEMI_MONTHLY);
        System.out.println(estimatedDeduction);

        assertTrue(estimatedDeduction >= 17.05 && estimatedDeduction <= 17.05, "new w4 standard HH");
    }

    @Test
     void UnitTestComputeExemptionAllowanceTable5() throws Exception
    {
        CAComputeExemptionAllowanceTable CAComputeExemptionAllowanceTable = new CAComputeExemptionAllowanceTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeExemptionAllowanceTable.initialize();

        double estimatedDeduction = CAComputeExemptionAllowanceTable.estimatedExemptionAllowance(4, CAPayrollPeriodEnum.QUARTERLY);
        System.out.println(estimatedDeduction);

        assertTrue(estimatedDeduction >= 136.40 && estimatedDeduction <= 136.40, "new w4 standard HH");
    }
    
    @Test

     void UnitTestComputeExemptionAllowanceTable6() throws Exception
    {
        CAComputeExemptionAllowanceTable CAComputeExemptionAllowanceTable = new CAComputeExemptionAllowanceTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeExemptionAllowanceTable.initialize();

        double estimatedDeduction = CAComputeExemptionAllowanceTable.estimatedExemptionAllowance(4, CAPayrollPeriodEnum.SEMI_ANNUAL);
        System.out.println(estimatedDeduction);

        assertTrue(estimatedDeduction >= 272.80 && estimatedDeduction <= 272.80, "new w4 standard HH");
    }
    
    @Test

     void UnitTestComputeExemptionAllowanceTable7() throws Exception
    {
        CAComputeExemptionAllowanceTable CAComputeExemptionAllowanceTable = new CAComputeExemptionAllowanceTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeExemptionAllowanceTable.initialize();

        double estimatedDeduction = CAComputeExemptionAllowanceTable.estimatedExemptionAllowance(8, CAPayrollPeriodEnum.ANNUAL);
        System.out.println(estimatedDeduction);

        assertTrue(estimatedDeduction >= 1091.20 && estimatedDeduction <= 1091.20, "new w4 standard HH");
    }
}