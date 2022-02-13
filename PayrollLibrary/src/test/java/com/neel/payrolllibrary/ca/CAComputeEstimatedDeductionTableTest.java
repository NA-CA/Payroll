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

class CAComputeEstimatedDeductionTableTest
{

    @Test
    void UnitTestComputeEstimatedDeductionTable1() throws Exception
    {
        CAComputeEstimatedDeductionTable CAComputeEstimatedDeductionTable = new CAComputeEstimatedDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeEstimatedDeductionTable.initiliaze();
        int storeDeduction = CAComputeEstimatedDeductionTable.estimatedDeduction(11, CAPayrollPeriodEnum.WEEKLY);
        System.out.println(storeDeduction);

        assertTrue(storeDeduction >= 212 && storeDeduction <= 212, "new w4 standard HH");
    }

    @Test
    void UnitTestComputeEstimatedDeductionTable2() throws Exception
    {
        CAComputeEstimatedDeductionTable CAComputeEstimatedDeductionTable = new CAComputeEstimatedDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeEstimatedDeductionTable.initiliaze();
        int storeDeduction = CAComputeEstimatedDeductionTable.estimatedDeduction(3, CAPayrollPeriodEnum.BI_WEEKLY);
        System.out.println(storeDeduction);

        assertTrue(storeDeduction >= 115 && storeDeduction <= 115, "new w4 standard HH");
    }

    @Test
    void UnitTestComputeEstimatedDeductionTable3() throws Exception
    {
        CAComputeEstimatedDeductionTable CAComputeEstimatedDeductionTable = new CAComputeEstimatedDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeEstimatedDeductionTable.initiliaze();
        int storeDeduction = CAComputeEstimatedDeductionTable.estimatedDeduction(6, CAPayrollPeriodEnum.SEMI_MONTHLY);
        System.out.println(storeDeduction);

        assertTrue(storeDeduction >= 250 && storeDeduction <= 250, "new w4 standard HH");
    }

    @Test
    void UnitTestComputeEstimatedDeductionTable4() throws Exception
    {
        CAComputeEstimatedDeductionTable CAComputeEstimatedDeductionTable = new CAComputeEstimatedDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeEstimatedDeductionTable.initiliaze();
        int storeDeduction = CAComputeEstimatedDeductionTable.estimatedDeduction(8, CAPayrollPeriodEnum.MONTHLY);
        System.out.println(storeDeduction);

        assertTrue(storeDeduction >= 667 && storeDeduction <= 667, "new w4 standard HH");
    }

    @Test
    void UnitTestComputeEstimatedDeductionTable5() throws Exception
    {
        CAComputeEstimatedDeductionTable CAComputeEstimatedDeductionTable = new CAComputeEstimatedDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeEstimatedDeductionTable.initiliaze();
        int storeDeduction = CAComputeEstimatedDeductionTable.estimatedDeduction(3, CAPayrollPeriodEnum.QUARTERLY);
        System.out.println(storeDeduction);

        assertTrue(storeDeduction >= 750 && storeDeduction <= 750, "new w4 standard HH");
    }

    @Test
    void UnitTestComputeEstimatedDeductionTable6() throws Exception
    {
        CAComputeEstimatedDeductionTable CAComputeEstimatedDeductionTable = new CAComputeEstimatedDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeEstimatedDeductionTable.initiliaze();
        int storeDeduction = CAComputeEstimatedDeductionTable.estimatedDeduction(10, CAPayrollPeriodEnum.SEMI_ANNUAL);
        System.out.println(storeDeduction);

        assertTrue(storeDeduction >= 5000 && storeDeduction <= 5000, "new w4 standard HH");
    }

    @Test
    void UnitTestComputeEstimatedDeductionTable7() throws Exception
    {
        CAComputeEstimatedDeductionTable CAComputeEstimatedDeductionTable = new CAComputeEstimatedDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeEstimatedDeductionTable.initiliaze();
        int storeDeduction = CAComputeEstimatedDeductionTable.estimatedDeduction(4, CAPayrollPeriodEnum.ANNUAL);
        System.out.println(storeDeduction);

        assertTrue(storeDeduction >= 4000 && storeDeduction <= 4000, "new w4 standard HH");
    }

    @Test
    void UnitTestComputeEstimatedDeductionTable8() throws Exception
    {
        CAComputeEstimatedDeductionTable CAComputeEstimatedDeductionTable = new CAComputeEstimatedDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeEstimatedDeductionTable.initiliaze();
        int storeDeduction = CAComputeEstimatedDeductionTable.estimatedDeduction(6, CAPayrollPeriodEnum.DAILY_MISC);
        System.out.println(storeDeduction);

        assertTrue(storeDeduction >= 23 && storeDeduction <= 23, "new w4 standard HH");
    }
}