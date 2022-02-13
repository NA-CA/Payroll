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

public class CAComputeExemptionAllowanceTable
{
    private HashMap<Integer, HashMap<Integer, CAExemptionAllowanceTable>> hashMap = new HashMap<>();
    private String filePath = "ExemptionAllowanceTable.txt";
    private int oneWitholdingAllowance = 1;
    private int year = 0;
    private String dataPath = "";

    private String computeExemptionAllowanceTableFileNameWithPath = "";

    public CAComputeExemptionAllowanceTable(int year, String dataPath)
    {
        this.year = year;
        this.dataPath = dataPath;

        computeExemptionAllowanceTableFileNameWithPath = dataPath + "\\witholding\\" + Integer.toString(year) + "\\CA\\" + filePath;
        System.out.println(computeExemptionAllowanceTableFileNameWithPath);
    }

    public void initialize() throws IOException
    {
        storeExemptionAllowanceTable(computeExemptionAllowanceTableFileNameWithPath);

    }

    public void storeExemptionAllowanceTable(String computeExemptionAllowanceTableFileNameWithPath) throws IOException
    {
        try
        {
            HashMap<Integer, CAExemptionAllowanceTable> hashMapExemption = new HashMap<>();
            FileReader fileReader = new FileReader(computeExemptionAllowanceTableFileNameWithPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();
            int year = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.readLine();

            String readLine = "";

            while((readLine = bufferedReader.readLine()) != null)
            {
                String [] splitString = readLine.split(",");
                int withHolding = Integer.parseInt(splitString[0]);
                double annual = Double.parseDouble(splitString[1]);
                double dailyMisc = Double.parseDouble(splitString[2]);

                CAExemptionAllowanceTable CAExemptionAllowanceTable = new CAExemptionAllowanceTable(withHolding,annual,dailyMisc);
                hashMapExemption.put(withHolding, CAExemptionAllowanceTable);
            }

            hashMap.put(year,hashMapExemption);
        }
        catch (IOException e)
        {
            System.out.println("Could not open file " + computeExemptionAllowanceTableFileNameWithPath + " for reading. " + e.getMessage());
            throw e;
        }


    }

    public double estimatedExemptionAllowance(int numAllowance, CAPayrollPeriodEnum payrollPeriod)
    {
        if (numAllowance == 0)
        {
            return 0.0;
        }
        double estimatedException = 0.0;
        HashMap<Integer, CAExemptionAllowanceTable> hashMap1 = hashMap.get(year);
        CAExemptionAllowanceTable CAExemptionAllowanceTable = hashMap1.get(oneWitholdingAllowance);

        switch (payrollPeriod)
        {
            case WEEKLY:
                double weekly = CAExemptionAllowanceTable.getAnnual() /52;
                estimatedException = weekly * numAllowance;
                break;
            case BI_WEEKLY:
                double biWeekly = CAExemptionAllowanceTable.getAnnual() /26;
                estimatedException = biWeekly * numAllowance;
                break;
            case SEMI_MONTHLY:
                double semiMonthly = CAExemptionAllowanceTable.getAnnual() /24;
                estimatedException = semiMonthly * numAllowance;
                break;
            case MONTHLY:
                double monthly = CAExemptionAllowanceTable.getAnnual() /12;
                estimatedException = monthly * numAllowance;
                break;
            case QUARTERLY:
                double quarterly = CAExemptionAllowanceTable.getAnnual() /4;
                estimatedException = quarterly * numAllowance;
                break;
            case SEMI_ANNUAL:
                double semiAnnual = CAExemptionAllowanceTable.getAnnual() /2;
                estimatedException = semiAnnual * numAllowance;
                break;
            case ANNUAL:
                double annual = CAExemptionAllowanceTable.getAnnual() /1;
                estimatedException = annual * numAllowance;
                break;
            case DAILY_MISC:
                double dailyMisc = CAExemptionAllowanceTable.getDailyMisc();
                estimatedException = dailyMisc * numAllowance;
                break;
        }

        estimatedException = Math.round(estimatedException * 100.0)/100.0;
        return estimatedException;
    }

}
