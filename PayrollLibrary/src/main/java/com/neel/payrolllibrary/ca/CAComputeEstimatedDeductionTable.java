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

public class CAComputeEstimatedDeductionTable
{

    private HashMap<Integer, HashMap<Integer, CAEstimatedDeductionTable>> hashMap = new HashMap<>();
    private final int oneWitholdingAllowance = 1;

    private String filePath = "EstimatedDeductionTable.txt";

    private int year = 0;
    private String dataPath = "";

    private String estimatedDeductionTableFileNameWithPath = "";


    public CAComputeEstimatedDeductionTable(int year, String dataPath)
    {
        this.year = year;
        this.dataPath = dataPath;

        estimatedDeductionTableFileNameWithPath = dataPath + "\\witholding\\" + Integer.toString(year) + "\\CA\\" + filePath;
        System.out.println(estimatedDeductionTableFileNameWithPath);
    }

    public void initiliaze() throws IOException
    {
        storeLowIncomeExceptionTable(estimatedDeductionTableFileNameWithPath);

    }

    private void storeLowIncomeExceptionTable(String estimatedDeductionTableFileNameWithPath) throws IOException
    {
        try
        {
            HashMap<Integer, CAEstimatedDeductionTable> hashMapAllowances = new HashMap<>();
            FileReader fileReader = new FileReader(estimatedDeductionTableFileNameWithPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();
            int year = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.readLine();

            String readLine = "";

            while((readLine = bufferedReader.readLine()) != null)
            {
                String [] splitString = readLine.split(",");
                int additionalWitholding = Integer.parseInt(splitString[0]);
                double annual = Double.parseDouble(splitString[1]);
                double daily = Double.parseDouble(splitString[2]);

                CAEstimatedDeductionTable CAEstimatedDeductionTable = new CAEstimatedDeductionTable(additionalWitholding, annual,daily);
                hashMapAllowances.put(additionalWitholding, CAEstimatedDeductionTable);
            }

            hashMap.put(year, hashMapAllowances);

        }
        catch (IOException e)
        {
            System.out.println("Could not open file " + estimatedDeductionTableFileNameWithPath + " for reading. " + e.getMessage());
            throw e;
        }


    }


    public int estimatedDeduction(int additionalWitholdingAllowances, CAPayrollPeriodEnum payrollPeriod)
    {
        double calculatedDeduction = 0.0;
        HashMap<Integer, CAEstimatedDeductionTable> hashMap1 = hashMap.get(year);
        CAEstimatedDeductionTable CAEstimatedDeductionTable = hashMap1.get(oneWitholdingAllowance);


        switch (payrollPeriod)
        {
            case WEEKLY:
                double weekly = CAEstimatedDeductionTable.getAnnual()/52.0;
                calculatedDeduction = weekly * additionalWitholdingAllowances;
                break;
            case BI_WEEKLY:
                double biWeekly = CAEstimatedDeductionTable.getAnnual()/26.0;
                calculatedDeduction = biWeekly * additionalWitholdingAllowances;
                break;
            case SEMI_MONTHLY:
                double semiMonthly = CAEstimatedDeductionTable.getAnnual()/24.0;
                calculatedDeduction = semiMonthly * additionalWitholdingAllowances;
                break;
            case MONTHLY:
                double monthly = CAEstimatedDeductionTable.getAnnual()/12.0;
                calculatedDeduction = monthly * additionalWitholdingAllowances;
                break;
            case QUARTERLY:
                double quarterly = CAEstimatedDeductionTable.getAnnual()/4.0;
                calculatedDeduction = quarterly * additionalWitholdingAllowances;
                break;
            case SEMI_ANNUAL:
                double semiAnnual = CAEstimatedDeductionTable.getAnnual()/2.0;
                calculatedDeduction = semiAnnual * additionalWitholdingAllowances;
                break;
            case ANNUAL:
                double annual = CAEstimatedDeductionTable.getAnnual();
                calculatedDeduction = annual * additionalWitholdingAllowances;
                break;
            case DAILY_MISC:
                double dailyMisc = CAEstimatedDeductionTable.getDailyMisc();
                calculatedDeduction = dailyMisc * additionalWitholdingAllowances;
                break;
        }

        long roundedValue = Math.round(calculatedDeduction);

        return (int)roundedValue;
    }


}
