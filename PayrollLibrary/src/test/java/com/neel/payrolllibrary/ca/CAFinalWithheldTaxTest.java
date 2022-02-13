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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CAFinalWithheldTaxTest
{

    @Test
    @DisplayName("CA2021Example F")
    void getCaliforniaWitholding() throws IOException
    {
        // double salary, CAPayrollPeriodEnum payrollPeriod, CALowIncome_StandardDeductionTableEnum standardDeductionTable,
        //                              int caDe4Line1AregularWHStandardAllowane, int caDe4Line1BestimatedDeduction_exemptionWHAllowance, CAFilingStatusEnum caFilingStatusEnum, String dataPath)

        CAFinalWithheldTax caFinalWithheldTax = new CAFinalWithheldTax(45000/12, CAPayrollPeriodEnum.MONTHLY,
                4, 0, CAFilingStatusEnum.MARRIED, PayrollLibUnitTestUtil.getDataPath(), false);
        double caWitholding = caFinalWithheldTax.getCaliforniaWitholding();
        System.out.println(caWitholding);

        assertTrue(caWitholding >= 3 && caWitholding <= 4, "new w4 standard HH");
    }

    @Test
    @DisplayName("CA2021Example E")

    void getCaliforniaWitholding1() throws IOException
    {
        // double salary, CAPayrollPeriodEnum payrollPeriod, CALowIncome_StandardDeductionTableEnum standardDeductionTable,
        //                              int caDe4Line1AregularWHStandardAllowane, int caDe4Line1BestimatedDeduction_exemptionWHAllowance, CAFilingStatusEnum caFilingStatusEnum, String dataPath)

        CAFinalWithheldTax caFinalWithheldTax = new CAFinalWithheldTax(1800, CAPayrollPeriodEnum.SEMI_MONTHLY,
                4, 0, CAFilingStatusEnum.MARRIED, PayrollLibUnitTestUtil.getDataPath(), false);
        double caWitholding = caFinalWithheldTax.getCaliforniaWitholding();
        System.out.println(caWitholding);

        assertTrue(caWitholding >= 0.24 && caWitholding <= 0.25, "new w4 standard HH");
    }


    @Test
    @DisplayName("CA2021MethodaExampleB")


    void getCaliforniaWitholding2() throws IOException
    {
        // double salary, CAPayrollPeriodEnum payrollPeriod, CALowIncome_StandardDeductionTableEnum standardDeductionTable,
        //                              int caDe4Line1AregularWHStandardAllowane, int caDe4Line1BestimatedDeduction_exemptionWHAllowance, CAFilingStatusEnum caFilingStatusEnum, String dataPath)

        CAFinalWithheldTax caFinalWithheldTax = new CAFinalWithheldTax(3500, CAPayrollPeriodEnum.MONTHLY,
                2, 4, CAFilingStatusEnum.MARRIED, PayrollLibUnitTestUtil.getDataPath(), false);
        double caWitholding = caFinalWithheldTax.getCaliforniaWitholding();
        System.out.println(caWitholding);

        assertTrue(caWitholding >= 13 && caWitholding <= 15, "new w4 standard HH");
    }



    @Test
    @DisplayName("CA2021Example B")

    void getCaliforniaWitholding3() throws IOException
    {
        // double salary, CAPayrollPeriodEnum payrollPeriod, CALowIncome_StandardDeductionTableEnum standardDeductionTable,
        //                              int caDe4Line1AregularWHStandardAllowane, int caDe4Line1BestimatedDeduction_exemptionWHAllowance, CAFilingStatusEnum caFilingStatusEnum, String dataPath)

        CAFinalWithheldTax caFinalWithheldTax = new CAFinalWithheldTax(1250, CAPayrollPeriodEnum.BI_WEEKLY,
                2, 1, CAFilingStatusEnum.MARRIED, PayrollLibUnitTestUtil.getDataPath(), false);
        double caWitholding = caFinalWithheldTax.getCaliforniaWitholding();
        System.out.println(caWitholding);

        assertTrue(caWitholding >= 0.8 && caWitholding <= 1, "new w4 standard HH");
    }

    @Test
    @DisplayName("CA2021Example D")

    void getCaliforniaWitholding4() throws IOException
    {
        // double salary, CAPayrollPeriodEnum payrollPeriod, CALowIncome_StandardDeductionTableEnum standardDeductionTable,
        //                              int caDe4Line1AregularWHStandardAllowane, int caDe4Line1BestimatedDeduction_exemptionWHAllowance, CAFilingStatusEnum caFilingStatusEnum, String dataPath)

        CAFinalWithheldTax caFinalWithheldTax = new CAFinalWithheldTax(800, CAPayrollPeriodEnum.WEEKLY,
                3, 0, CAFilingStatusEnum.HEADOFHOUSEHOLD, PayrollLibUnitTestUtil.getDataPath(),false);
        double caWitholding = caFinalWithheldTax.getCaliforniaWitholding();
        System.out.println(caWitholding);

        assertTrue(caWitholding >= 2 && caWitholding <= 3, "new w4 standard HH");
    }

    @Test
    @DisplayName("CA2021Example C")

    void getCaliforniaWitholding5() throws IOException
    {
        // double salary, CAPayrollPeriodEnum payrollPeriod, CALowIncome_StandardDeductionTableEnum standardDeductionTable,
        //                              int caDe4Line1AregularWHStandardAllowane, int caDe4Line1BestimatedDeduction_exemptionWHAllowance, CAFilingStatusEnum caFilingStatusEnum, String dataPath)

        CAFinalWithheldTax caFinalWithheldTax = new CAFinalWithheldTax(4100, CAPayrollPeriodEnum.MONTHLY,
                5, 0, CAFilingStatusEnum.MARRIED, PayrollLibUnitTestUtil.getDataPath(),false);
        double caWitholding = caFinalWithheldTax.getCaliforniaWitholding();
        System.out.println(caWitholding);

        assertTrue(caWitholding >= 0 && caWitholding <= 1, "new w4 standard HH");
    }

    @Test
    @DisplayName("CA2021Example B")

    void getCaliforniaWitholding6() throws IOException
    {
        // double salary, CAPayrollPeriodEnum payrollPeriod, CALowIncome_StandardDeductionTableEnum standardDeductionTable,
        //                              int caDe4Line1AregularWHStandardAllowane, int caDe4Line1BestimatedDeduction_exemptionWHAllowance, CAFilingStatusEnum caFilingStatusEnum, String dataPath)

        CAFinalWithheldTax caFinalWithheldTax = new CAFinalWithheldTax(1250, CAPayrollPeriodEnum.BI_WEEKLY,
                2, 1, CAFilingStatusEnum.MARRIED, PayrollLibUnitTestUtil.getDataPath(),false);
        double caWitholding = caFinalWithheldTax.getCaliforniaWitholding();
        System.out.println(caWitholding);

        assertTrue(caWitholding >= 0 && caWitholding <= 1, "new w4 standard HH");
    }

    @Test
    @DisplayName("CA2021Example A")

    void getCaliforniaWitholding7() throws IOException
    {
        // double salary, CAPayrollPeriodEnum payrollPeriod, CALowIncome_StandardDeductionTableEnum standardDeductionTable,
        //                              int caDe4Line1AregularWHStandardAllowane, int caDe4Line1BestimatedDeduction_exemptionWHAllowance, CAFilingStatusEnum caFilingStatusEnum, String dataPath)

        CAFinalWithheldTax caFinalWithheldTax = new CAFinalWithheldTax(210, CAPayrollPeriodEnum.WEEKLY,
                1, 0, CAFilingStatusEnum.SINGLE, PayrollLibUnitTestUtil.getDataPath(),false);
        double caWitholding = caFinalWithheldTax.getCaliforniaWitholding();
        System.out.println(caWitholding);

        assertTrue(caWitholding >= 0 && caWitholding <= 0, "new w4 standard HH");
    }

    @Test
    @DisplayName("CA2021Example EXEMPT")

    void getCaliforniaWitholding8() throws IOException
    {
        // double salary, CAPayrollPeriodEnum payrollPeriod, CALowIncome_StandardDeductionTableEnum standardDeductionTable,
        //                              int caDe4Line1AregularWHStandardAllowane, int caDe4Line1BestimatedDeduction_exemptionWHAllowance, CAFilingStatusEnum caFilingStatusEnum, String dataPath)

        CAFinalWithheldTax caFinalWithheldTax = new CAFinalWithheldTax(100000, CAPayrollPeriodEnum.WEEKLY,
                1, 0, CAFilingStatusEnum.SINGLE, PayrollLibUnitTestUtil.getDataPath(),true);
        double caWitholding = caFinalWithheldTax.getCaliforniaWitholding();
        System.out.println(caWitholding);

        assertTrue(caWitholding >= 0 && caWitholding <= 0, "new w4 standard HH");
    }


}