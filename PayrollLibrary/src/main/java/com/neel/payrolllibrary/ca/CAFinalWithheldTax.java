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

import java.io.IOException;

public class CAFinalWithheldTax
{
    private double salary = 0.0;
    private CAPayrollPeriodEnum payrollPeriod;
    private CALowIncome_StandardDeductionTableEnum lowIncomeExceptionTableEnum;
    private int caDe4Line1AregularWHStandardAllowane = 0;
    private int caDe4Line1BestimatedDeduction_exemptionWHAllowance = 0;
    private String dataPath = "";


    private CAFilingStatusEnum caFilingStatusEnum;
    private boolean EXEMPT = false;


    public CAFinalWithheldTax(double salary, CAPayrollPeriodEnum payrollPeriod,
                              int caDe4Line1AregularWHStandardAllowane, int caDe4Line1BestimatedDeduction_exemptionWHAllowance, CAFilingStatusEnum caFilingStatusEnum, String dataPath, boolean EXEMPT)
    {
        this.salary = salary;
        this.payrollPeriod = payrollPeriod;
        this.caDe4Line1AregularWHStandardAllowane = caDe4Line1AregularWHStandardAllowane;
        this.caDe4Line1BestimatedDeduction_exemptionWHAllowance = caDe4Line1BestimatedDeduction_exemptionWHAllowance;
        this.caFilingStatusEnum = caFilingStatusEnum;
        this.dataPath = dataPath;
        this.EXEMPT = EXEMPT;



        lowIncomeExceptionTableEnum(caDe4Line1AregularWHStandardAllowane, caFilingStatusEnum);

    }

    private void lowIncomeExceptionTableEnum(int caDe4Line1AregularWHStandardAllowane, CAFilingStatusEnum caFilingStatusEnum)
    {
        if (caFilingStatusEnum == CAFilingStatusEnum.SINGLE)
        {
            lowIncomeExceptionTableEnum = CALowIncome_StandardDeductionTableEnum.SINGLEDUALINCOMEMARRIEDMARRIEDWITHMULTIPLEEMPLOYERS;
        }
        if (caFilingStatusEnum == CAFilingStatusEnum.MARRIED && caDe4Line1AregularWHStandardAllowane < 2)
        {
            lowIncomeExceptionTableEnum = CALowIncome_StandardDeductionTableEnum.MARRIEDOOR1;
        }
        if (caFilingStatusEnum == CAFilingStatusEnum.MARRIED && caDe4Line1AregularWHStandardAllowane >= 2)
        {
            lowIncomeExceptionTableEnum = CALowIncome_StandardDeductionTableEnum.MARRIED2ORMORE;
        }
        if (caFilingStatusEnum == CAFilingStatusEnum.HEADOFHOUSEHOLD)
        {
            lowIncomeExceptionTableEnum = CALowIncome_StandardDeductionTableEnum.UNMARRIEDHEADOFHOUSEHOLD;
        }

    }

    public double getCaliforniaWitholding() throws IOException
    {
        if (EXEMPT == true)
        {
            return 0.0;
        }
        double computedLowIncomeException = checkLowIncomeExceptionHigherLower();
        double estimatedDeduction = 0.0;
        double standardDeduction = 0.0;

        if (salary < computedLowIncomeException)
        {
            return 0.0;
        }

        else
        {
            if (caDe4Line1BestimatedDeduction_exemptionWHAllowance != 0)
            {
                estimatedDeduction = estimatedDeductionTable();
            }
        }

        standardDeduction = standardDeductionTable();

        double salaryAfterDeduction = salary-estimatedDeduction-standardDeduction;

        double taxRateTableTax = getTaxRateTableTax(salaryAfterDeduction);
        double exemptionAllowance = exemptionAllowanceTable();
        double californiaWitholding = getFinalWitholding(taxRateTableTax,exemptionAllowance);

        return californiaWitholding;

    }

    private double checkLowIncomeExceptionHigherLower() throws IOException
    {
        CAComputeLowExemptionTable CAComputeLowExemptionTable = new CAComputeLowExemptionTable(2021, dataPath);
        CAComputeLowExemptionTable.initiliaze();
        double computedLowIncomeException = CAComputeLowExemptionTable.lowIncomeException(payrollPeriod,caDe4Line1AregularWHStandardAllowane,lowIncomeExceptionTableEnum);

        return computedLowIncomeException;
    }

    private double estimatedDeductionTable() throws IOException
    {
        CAComputeEstimatedDeductionTable CAComputeEstimatedDeductionTable = new CAComputeEstimatedDeductionTable(2021, dataPath);
        CAComputeEstimatedDeductionTable.initiliaze();

        int estimatedDeduction = CAComputeEstimatedDeductionTable.estimatedDeduction(caDe4Line1BestimatedDeduction_exemptionWHAllowance,payrollPeriod);
        return estimatedDeduction;

    }

    private double standardDeductionTable() throws IOException
    {
        CAComputeStandardDeductionTable CAComputeStandardDeductionTable = new CAComputeStandardDeductionTable(2021, dataPath);
        CAComputeStandardDeductionTable.initialize();

        double computedStandardDeduction = CAComputeStandardDeductionTable.estimatedStandardDeduction(lowIncomeExceptionTableEnum,payrollPeriod);
        return computedStandardDeduction;


    }

    private double getTaxRateTableTax(double updatedSalary) throws IOException
    {
        CaliforniaWitholding californiaWitholding = new CaliforniaWitholding(2021,caFilingStatusEnum,payrollPeriod, dataPath);
        californiaWitholding.initialize();

        double computedTaxRateTable = californiaWitholding.computedTax(updatedSalary);
        return computedTaxRateTable;

    }

    private double exemptionAllowanceTable() throws IOException
    {
        CAComputeExemptionAllowanceTable CAComputeExemptionAllowanceTable = new CAComputeExemptionAllowanceTable(2021, dataPath);
        CAComputeExemptionAllowanceTable.initialize();

        double estimatedExemptionAllowance = CAComputeExemptionAllowanceTable.estimatedExemptionAllowance((caDe4Line1AregularWHStandardAllowane + caDe4Line1BestimatedDeduction_exemptionWHAllowance) -
                        caDe4Line1BestimatedDeduction_exemptionWHAllowance,payrollPeriod);

        return estimatedExemptionAllowance;


    }

    private double getFinalWitholding(double taxRateTableTax,double estimatedExemptionAllowance)
    {
        double netTax = taxRateTableTax - estimatedExemptionAllowance;
        return netTax;
    }


}
