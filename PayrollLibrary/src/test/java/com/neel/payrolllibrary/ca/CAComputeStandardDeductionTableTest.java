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

class CAComputeStandardDeductionTableTest
{

    @Test
     void UnitTestComputeStandardDeductionTable1() throws Exception
    {
        CAComputeStandardDeductionTable CAComputeStandardDeductionTable = new CAComputeStandardDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeStandardDeductionTable.initialize();

        double storeDeduction = CAComputeStandardDeductionTable.estimatedStandardDeductionElse(CALowIncome_StandardDeductionTableEnum.SINGLEDUALINCOMEMARRIEDMARRIEDWITHMULTIPLEEMPLOYERS,
                CAPayrollPeriodEnum.WEEKLY);

        System.out.println(storeDeduction);
        assertTrue(storeDeduction >= 88.48 && storeDeduction <= 88.48, "new w4 standard HH");
    }

    @Test
     void UnitTestComputeStandardDeductionTable2() throws Exception
    {
        CAComputeStandardDeductionTable CAComputeStandardDeductionTable = new CAComputeStandardDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeStandardDeductionTable.initialize();

        double storeDeduction = CAComputeStandardDeductionTable.estimatedStandardDeductionElse(CALowIncome_StandardDeductionTableEnum.UNMARRIEDHEADOFHOUSEHOLD,
                CAPayrollPeriodEnum.BI_WEEKLY);

        System.out.println(storeDeduction);
        assertTrue(storeDeduction >= 353.92 && storeDeduction <= 353.92, "new w4 standard HH");
    }

    @Test
     void UnitTestComputeStandardDeductionTable3() throws Exception
    {
        CAComputeStandardDeductionTable CAComputeStandardDeductionTable = new CAComputeStandardDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeStandardDeductionTable.initialize();

        double storeDeduction = CAComputeStandardDeductionTable.estimatedStandardDeductionElse(CALowIncome_StandardDeductionTableEnum.MARRIED2ORMORE,
                CAPayrollPeriodEnum.SEMI_MONTHLY);

        System.out.println(storeDeduction);
        assertTrue(storeDeduction >= 383.42 && storeDeduction <= 383.42, "new w4 standard HH");
    }

    @Test

     void UnitTestComputeStandardDeductionTable4() throws Exception
    {
        CAComputeStandardDeductionTable CAComputeStandardDeductionTable = new CAComputeStandardDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeStandardDeductionTable.initialize();

        double storeDeduction = CAComputeStandardDeductionTable.estimatedStandardDeductionElse(CALowIncome_StandardDeductionTableEnum.SINGLEDUALINCOMEMARRIEDMARRIEDWITHMULTIPLEEMPLOYERS,
                CAPayrollPeriodEnum.MONTHLY);

        System.out.println(storeDeduction);
        assertTrue(storeDeduction >= 383.42 && storeDeduction <= 383.42, "new w4 standard HH");
    }

    @Test
     void UnitTestComputeStandardDeductionTable5() throws Exception
    {
        CAComputeStandardDeductionTable CAComputeStandardDeductionTable = new CAComputeStandardDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeStandardDeductionTable.initialize();

        double storeDeduction = CAComputeStandardDeductionTable.estimatedStandardDeductionElse(CALowIncome_StandardDeductionTableEnum.MARRIEDOOR1,
                CAPayrollPeriodEnum.QUARTERLY);

        System.out.println(storeDeduction);
        assertTrue(storeDeduction >= 1150.25 && storeDeduction <= 1150.25, "new w4 standard HH");
    }

    @Test
     void UnitTestComputeStandardDeductionTable6() throws Exception
    {
        CAComputeStandardDeductionTable CAComputeStandardDeductionTable = new CAComputeStandardDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeStandardDeductionTable.initialize();

        double storeDeduction = CAComputeStandardDeductionTable.estimatedStandardDeductionElse(CALowIncome_StandardDeductionTableEnum.MARRIEDOOR1,
                CAPayrollPeriodEnum.SEMI_ANNUAL);

        System.out.println(storeDeduction);
        assertTrue(storeDeduction >= 2300.5 && storeDeduction <= 2300.5, "new w4 standard HH");
    }

    @Test
     void UnitTestComputeStandardDeductionTable7() throws Exception
    {
        CAComputeStandardDeductionTable CAComputeStandardDeductionTable = new CAComputeStandardDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeStandardDeductionTable.initialize();

        double storeDeduction = CAComputeStandardDeductionTable.estimatedStandardDeductionElse(CALowIncome_StandardDeductionTableEnum.SINGLEDUALINCOMEMARRIEDMARRIEDWITHMULTIPLEEMPLOYERS,
                CAPayrollPeriodEnum.ANNUAL);

        System.out.println(storeDeduction);
        assertTrue(storeDeduction >= 4601 && storeDeduction <= 4601, "new w4 standard HH");
    }

    @Test
     void UnitTestComputeStandardDeductionTable8() throws Exception
    {
        CAComputeStandardDeductionTable CAComputeStandardDeductionTable = new CAComputeStandardDeductionTable(2021, PayrollLibUnitTestUtil.getDataPath());
        CAComputeStandardDeductionTable.initialize();

        double storeDeduction= CAComputeStandardDeductionTable.estimatedStandardDeduction(CALowIncome_StandardDeductionTableEnum.SINGLEDUALINCOMEMARRIEDMARRIEDWITHMULTIPLEEMPLOYERS,
                CAPayrollPeriodEnum.DAILY_MISC);

        System.out.println(storeDeduction);
        assertTrue(storeDeduction >= 18 && storeDeduction <= 18, "new w4 standard HH");
    }
}