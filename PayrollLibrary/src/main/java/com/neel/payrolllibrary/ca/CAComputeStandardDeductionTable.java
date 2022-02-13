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

public class CAComputeStandardDeductionTable
{
    private HashMap<Integer, HashMap<String, CAStandardDeductionTable>> hashMap = new HashMap<>();
    private final String filePath = "StandardDeductionTable.txt";
    private int year = 0;
    private String dataPath = "";
    private String computeStandardDeductionTableFileNameWithPath = "";


    public CAComputeStandardDeductionTable(int year, String dataPath)
    {
        this.year = year;
        this.dataPath = dataPath;

        computeStandardDeductionTableFileNameWithPath = dataPath + "\\witholding\\" + Integer.toString(year) + "\\CA\\" + filePath;
    }

    public void initialize() throws IOException
    {
        storeStandardDeductionTable(computeStandardDeductionTableFileNameWithPath);

    }

    private void storeStandardDeductionTable(String computeStandardDeductionTableFileNameWithPath) throws IOException
    {
        try
        {

            HashMap<String, CAStandardDeductionTable> hashMapPayrollPeriodsOneYear = new HashMap<>();
            FileReader fileReader = new FileReader(computeStandardDeductionTableFileNameWithPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            String readLine = "";

            String [] splitString = readLine.split(",");
            bufferedReader.readLine();
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

                CAStandardDeductionTable CAStandardDeductionTable = new CAStandardDeductionTable(payrollPeriod,singleDualIncomeMarriedMarriedWithMultipleEmployers,
                        marriedO1Allowance,married2MoreAllowance,unmarriedHeadOfHousehold);


                hashMapPayrollPeriodsOneYear.put(payrollPeriod, CAStandardDeductionTable);

            }

            hashMap.put(year,hashMapPayrollPeriodsOneYear);
        }
        catch (IOException e)
        {
            System.out.println("Could not open file " + computeStandardDeductionTableFileNameWithPath + " for reading. " + e.getMessage());
            throw e;
        }

    }

    public double estimatedStandardDeduction(CALowIncome_StandardDeductionTableEnum inputCALowIncomeStandardDeductionTableEnum, CAPayrollPeriodEnum inputCAPayrollPeriodEnum)
    {
        double standardDeduction = 0.0;
        if (inputCAPayrollPeriodEnum == CAPayrollPeriodEnum.DAILY_MISC)
        {
           standardDeduction = estimatedStandardDeductionDaily(inputCALowIncomeStandardDeductionTableEnum, inputCAPayrollPeriodEnum);
        }
        else
        {
           standardDeduction = estimatedStandardDeductionElse(inputCALowIncomeStandardDeductionTableEnum, inputCAPayrollPeriodEnum);
        }

        return standardDeduction;
    }

    public double estimatedStandardDeductionElse(CALowIncome_StandardDeductionTableEnum inputCALowIncomeStandardDeductionTableEnum, CAPayrollPeriodEnum inputCAPayrollPeriodEnum)
    {
        HashMap<String, CAStandardDeductionTable> hashmap1 = hashMap.get(year);
        CAStandardDeductionTable CAStandardDeductionTable = hashmap1.get("Annual");

        double standardDeduction = 0.0;
        switch (inputCALowIncomeStandardDeductionTableEnum)
        {
            case SINGLEDUALINCOMEMARRIEDMARRIEDWITHMULTIPLEEMPLOYERS:
                standardDeduction = CAStandardDeductionTable.getSingleDualIncomeMarriedMarriedMultipleEmployers();
                break;
            case MARRIEDOOR1:
                standardDeduction = CAStandardDeductionTable.getSingleDualIncomeMarriedMarriedMultipleEmployers();
                break;
            case MARRIED2ORMORE:
                standardDeduction = CAStandardDeductionTable.getSingleDualIncomeMarriedMarriedMultipleEmployers() * 2;
                break;

            case UNMARRIEDHEADOFHOUSEHOLD:
                standardDeduction = CAStandardDeductionTable.getSingleDualIncomeMarriedMarriedMultipleEmployers() * 2;
                break;
        }

        switch (inputCAPayrollPeriodEnum)
        {
            case WEEKLY:
                standardDeduction = standardDeduction / 52;
                break;

            case BI_WEEKLY:
                standardDeduction = standardDeduction / 26;
                break;

            case SEMI_MONTHLY:
                standardDeduction = standardDeduction / 24;
                break;
            case MONTHLY:
                standardDeduction = standardDeduction / 12;
                break;

            case QUARTERLY:
                standardDeduction = standardDeduction / 4;
                break;
            case SEMI_ANNUAL:
                standardDeduction = standardDeduction / 2;
            case ANNUAL:
                standardDeduction = standardDeduction;
                break;


        }

        return Math.round(standardDeduction * 100.0) / 100.0;
    }

    public double estimatedStandardDeductionDaily(CALowIncome_StandardDeductionTableEnum inputCALowIncomeStandardDeductionTableEnum, CAPayrollPeriodEnum inputCAPayrollPeriodEnum)
    {
        HashMap<String, CAStandardDeductionTable> hashmap1 = hashMap.get(year);
        CAStandardDeductionTable CAStandardDeductionTable = hashmap1.get("Daily");

        double standardDeduction = 0.0;
        switch (inputCALowIncomeStandardDeductionTableEnum)
        {
            case SINGLEDUALINCOMEMARRIEDMARRIEDWITHMULTIPLEEMPLOYERS:
                standardDeduction = CAStandardDeductionTable.getSingleDualIncomeMarriedMarriedMultipleEmployers();
                break;
            case MARRIEDOOR1:
                standardDeduction = CAStandardDeductionTable.getSingleDualIncomeMarriedMarriedMultipleEmployers();
                break;
            case MARRIED2ORMORE:
                standardDeduction = CAStandardDeductionTable.getSingleDualIncomeMarriedMarriedMultipleEmployers() * 2;
                break;

            case UNMARRIEDHEADOFHOUSEHOLD:
                standardDeduction = CAStandardDeductionTable.getSingleDualIncomeMarriedMarriedMultipleEmployers() * 2;
                break;
        }

        return Math.round(standardDeduction*100.0)/100.0;
    }


}
