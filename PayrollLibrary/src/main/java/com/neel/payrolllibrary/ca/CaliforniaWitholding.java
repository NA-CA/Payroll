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
import java.util.ArrayList;
import java.util.HashMap;

public class CaliforniaWitholding
{

    private ArrayList<CATaxRateTable> arrayList = new ArrayList<>();
    private int year = 0;
    private CAFilingStatusEnum taxRateTable;
    private CAPayrollPeriodEnum payrollPeriod;
    private String dataPath = "";
    private String cawitholdingFileNamewithPath = "";



    public CaliforniaWitholding(int year, CAFilingStatusEnum taxRateTable, CAPayrollPeriodEnum payrollPeriod, String dataPath)
    {
        this.year = year;
        this.taxRateTable = taxRateTable;
        this.payrollPeriod = payrollPeriod;
        this.dataPath = dataPath;
    }

    public void initialize() throws IOException
    {

        storeExactCalculation();
    }

    private void storeExactCalculation() throws IOException
    {

        try
        {
            if (payrollPeriod == CAPayrollPeriodEnum.DAILY_MISC)
            {
                cawitholdingFileNamewithPath = dataPath + "\\witholding\\" + Integer.toString(year) +  "\\CA\\" +
                        "DAILY"+"_"+taxRateTable.name()+".TXT";
            }
            else
            {
                cawitholdingFileNamewithPath = dataPath + "\\witholding\\" + Integer.toString(year) + "\\CA\\" +
                        "ANNUAL"+"_"+taxRateTable.name()+".TXT";

            }

            double computedTax = 0.0;
            HashMap<Integer, CATaxRateTable> hashMap1 = new HashMap<>();
            FileReader fileReader = new FileReader(cawitholdingFileNamewithPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();
            int year = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.readLine();

            String readLine = "";
            while((readLine = bufferedReader.readLine()) != null)
            {

                String [] splitString = readLine.split(",");
                int minAmount = Integer.parseInt(splitString[0]);
                int maxAmount = Integer.parseInt(splitString[1]);
                double taxRate = Double.parseDouble(splitString[2]);

                CATaxRateTable CATaxRateTable = new CATaxRateTable(minAmount,maxAmount,taxRate);
                arrayList.add(CATaxRateTable);

            }

        }
        catch (IOException e)
        {
            System.out.println("Could not open file " + cawitholdingFileNamewithPath + " for reading. " + e.getMessage());
            throw e;

        }


    }

    public double computedTax(double salary)
    {
        double computedTax = 0.0;


        switch (payrollPeriod)
        {
            case ANNUAL:
                salary = salary * 1.0;
                break;
            case DAILY_MISC:
                salary=salary*1.0;
                break;
            case WEEKLY:
                salary=salary*52.0;
                break;
            case BI_WEEKLY:
                salary=salary*26.0;
                break;
            case SEMI_MONTHLY:
                salary=salary*24.0;
                break;
            case MONTHLY:
                salary=salary*12.0;
                break;
            case SEMI_ANNUAL:
                salary=salary*2.0;
                break;
            case QUARTERLY:
                salary=salary*4.0;
        }

        for (int i = 0; i < arrayList.size(); i++)
        {

            int minSalaryBound = arrayList.get(i).getMinAmount();
            int maxSalaryBound = arrayList.get(i).getMaxAmount();

            if (salary >= maxSalaryBound)
            {
                computedTax =  computedTax + ((maxSalaryBound-minSalaryBound) * arrayList.get(i).getTaxRate());
            }
            else
            {
                computedTax = (salary-minSalaryBound) * arrayList.get(i).getTaxRate() + computedTax;
                break;
            }
        }


        switch (payrollPeriod)
        {
            case ANNUAL:
                computedTax = computedTax / 1.0;
                break;
            case DAILY_MISC:
                computedTax=computedTax/1.0;
                break;
            case WEEKLY:
                computedTax=computedTax/52.0;
                break;
            case BI_WEEKLY:
                computedTax=computedTax/26.0;
                break;
            case SEMI_MONTHLY:
                computedTax=computedTax/24.0;
                break;
            case MONTHLY:
                computedTax=computedTax/12.0;
                break;
            case SEMI_ANNUAL:
                computedTax=computedTax/2.0;
                break;
            case QUARTERLY:
                computedTax=computedTax/4.0;
        }

        return computedTax;

    }


}
