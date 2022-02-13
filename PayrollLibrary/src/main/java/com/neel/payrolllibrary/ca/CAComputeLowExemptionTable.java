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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CAComputeLowExemptionTable
{
    private HashMap<Integer, HashMap<CAPayrollPeriodEnum, CALowIncomeExemptionTable>> hashMap = new HashMap<>();

    private String filePath = "LowIncomeExemptionTable.txt";

    private int year = 0;

    private String dataPath = "";
    private String computeLowIncomeExemtionFileNameWithPath = "";

    public CAComputeLowExemptionTable(int year, String dataPath)
    {
        this.year = year;
        this.dataPath = dataPath;

        computeLowIncomeExemtionFileNameWithPath = dataPath + "\\witholding\\" + Integer.toString(year) + "\\CA\\" + filePath;
    }

    public void initiliaze() throws IOException
    {
        storeLowIncomeExceptionTable(computeLowIncomeExemtionFileNameWithPath);

    }

    private void storeLowIncomeExceptionTable(String computeLowIncomeExemtionFileNameWithPath) throws IOException
    {
        try
        {
            HashMap<CAPayrollPeriodEnum, CALowIncomeExemptionTable> hashMapPayrollPeriodsOneYear = new HashMap<>();
            FileReader fileReader = new FileReader(computeLowIncomeExemtionFileNameWithPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();
            String readLine = "";

            String [] splitString = readLine.split(",");
            int year = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.readLine();

            while ((readLine = bufferedReader.readLine()) != null)
            {
                splitString = readLine.split(",");
                String payrollPeriod = splitString[0];
                double singleDualIncomeMarriedMarriedWithMultipleEmployers = Double.parseDouble(splitString[1]);
                double marriedO1Allowance = Double.parseDouble(splitString[2]);
                double married2MoreAllowance = Double.parseDouble(splitString[3]);
                double unmarriedHeadOfHousehold = Double.parseDouble(splitString[4]);

                if (payrollPeriod.equals(CAPayrollPeriodEnum.ANNUAL.name()))
                {
                    CALowIncomeExemptionTable CALowIncomeExemptionTable = new CALowIncomeExemptionTable(payrollPeriod, singleDualIncomeMarriedMarriedWithMultipleEmployers
                            ,marriedO1Allowance,married2MoreAllowance,unmarriedHeadOfHousehold);
                    hashMapPayrollPeriodsOneYear.put(CAPayrollPeriodEnum.ANNUAL, CALowIncomeExemptionTable);
                }
                else
                {
                    CALowIncomeExemptionTable CALowIncomeExemptionTable = new CALowIncomeExemptionTable(payrollPeriod, singleDualIncomeMarriedMarriedWithMultipleEmployers
                            ,marriedO1Allowance,married2MoreAllowance,unmarriedHeadOfHousehold);
                    hashMapPayrollPeriodsOneYear.put(CAPayrollPeriodEnum.DAILY_MISC, CALowIncomeExemptionTable);
                }
            }

            hashMap.put(year,hashMapPayrollPeriodsOneYear);
        }
        catch (IOException e)
        {
            System.out.println("Could not open file " + computeLowIncomeExemtionFileNameWithPath + " for reading. " + e.getMessage());
            throw e;
        }

        allPayrollPeriod();
    }

    private void allPayrollPeriod()
    {
        HashMap<CAPayrollPeriodEnum, CALowIncomeExemptionTable> hashMap1 = hashMap.get(year);
        System.out.println(hashMap1);
        CALowIncomeExemptionTable CALowIncomeExemptionTable = hashMap1.get(CAPayrollPeriodEnum.ANNUAL);


        double singleDualIncomeMarriedMarriedWithMultipleEmployers = CALowIncomeExemptionTable.getSingleDualIncomeMarriedMarriedWithMultipleEmployers();
        double marriedO1Allowance = CALowIncomeExemptionTable.getMarried01Allowance();
        double married2MoreAllowance = CALowIncomeExemptionTable.getMarried2MoreAllowance();
        double unmarriedHeadOfHousehold = CALowIncomeExemptionTable.getUnmarriedHeadOfHousehold();

        CALowIncomeExemptionTable CALowIncomeExemptionTable1 = new CALowIncomeExemptionTable("WEEKLY", singleDualIncomeMarriedMarriedWithMultipleEmployers/52.0,
                marriedO1Allowance/52.0,married2MoreAllowance/52.0,unmarriedHeadOfHousehold/52.0);
        hashMap1.put(CAPayrollPeriodEnum.WEEKLY, CALowIncomeExemptionTable1);

        CALowIncomeExemptionTable CALowIncomeExemptionTable2 = new CALowIncomeExemptionTable("BI_WEEKLY", singleDualIncomeMarriedMarriedWithMultipleEmployers/26.0,
                marriedO1Allowance/26.0,married2MoreAllowance/26.0,unmarriedHeadOfHousehold/26.0);
        hashMap1.put(CAPayrollPeriodEnum.BI_WEEKLY, CALowIncomeExemptionTable2);

        CALowIncomeExemptionTable CALowIncomeExemptionTable3 = new CALowIncomeExemptionTable("SEMI_MONTHLY", singleDualIncomeMarriedMarriedWithMultipleEmployers/24.0,
                marriedO1Allowance/24.0,married2MoreAllowance/24.0,unmarriedHeadOfHousehold/24.0);
        hashMap1.put(CAPayrollPeriodEnum.SEMI_MONTHLY, CALowIncomeExemptionTable3);

        CALowIncomeExemptionTable CALowIncomeExemptionTable4 = new CALowIncomeExemptionTable("MONTHLY", singleDualIncomeMarriedMarriedWithMultipleEmployers/12.0,
                marriedO1Allowance/12.0,married2MoreAllowance/12.0,unmarriedHeadOfHousehold/12.0);
        hashMap1.put(CAPayrollPeriodEnum.MONTHLY, CALowIncomeExemptionTable4);

        CALowIncomeExemptionTable CALowIncomeExemptionTable5 = new CALowIncomeExemptionTable("QUARTERLY", singleDualIncomeMarriedMarriedWithMultipleEmployers/4.0,
                marriedO1Allowance/4.0,married2MoreAllowance/4.0,unmarriedHeadOfHousehold/4.0);
        hashMap1.put(CAPayrollPeriodEnum.QUARTERLY, CALowIncomeExemptionTable5);

        CALowIncomeExemptionTable CALowIncomeExemptionTable6 = new CALowIncomeExemptionTable("SEMI_ANNUAL", singleDualIncomeMarriedMarriedWithMultipleEmployers/2.0,
                marriedO1Allowance/2.0,married2MoreAllowance/2.0,unmarriedHeadOfHousehold/2.0);
        hashMap1.put(CAPayrollPeriodEnum.SEMI_ANNUAL, CALowIncomeExemptionTable6);

        hashMap.put(year, hashMap1);
    }

    public double lowIncomeException(CAPayrollPeriodEnum payrollPeriod, int withHoldingAllowance, CALowIncome_StandardDeductionTableEnum standardDeductionTable)
    {
        double lowIncomeException = 0.0;

        HashMap<CAPayrollPeriodEnum, CALowIncomeExemptionTable> hashMap1 = hashMap.get(year);
        CALowIncomeExemptionTable CALowIncomeExemptionTable = hashMap1.get(payrollPeriod);

        if (withHoldingAllowance < 2 && standardDeductionTable == CALowIncome_StandardDeductionTableEnum.MARRIEDOOR1)
        {
            lowIncomeException = CALowIncomeExemptionTable.getMarried01Allowance();
        }
        else if (withHoldingAllowance >= 2 && standardDeductionTable == CALowIncome_StandardDeductionTableEnum.MARRIED2ORMORE)
        {
            lowIncomeException = CALowIncomeExemptionTable.getMarried2MoreAllowance();
        }
        else if (standardDeductionTable == CALowIncome_StandardDeductionTableEnum.UNMARRIEDHEADOFHOUSEHOLD)
        {
            lowIncomeException = CALowIncomeExemptionTable.getUnmarriedHeadOfHousehold();
        }
        else if (standardDeductionTable == CALowIncome_StandardDeductionTableEnum.SINGLEDUALINCOMEMARRIEDMARRIEDWITHMULTIPLEEMPLOYERS)
        {
            lowIncomeException = CALowIncomeExemptionTable.getSingleDualIncomeMarriedMarriedWithMultipleEmployers();
        }



        return lowIncomeException;
    }
}
