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

package com.neel.payrolllibrary.fed;

import com.neel.payrolllibrary.FederalFilingStatusEnum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class W4_2020_LaterTentativeWitholding
{
    private ArrayList<W42020Later> w42020LaterArrayList = new ArrayList<>();

    private final String new2020W4HeadOfHousehold = "New_2020_W4_IRS_Pub_15_Percentage_Method_Table_HeadOfHousehold.txt";
    private final String new2020W4MarriedFilingJointly = "New_2020_W4_IRS_Pub_15_Percentage_Method_Table_MarriedFilingJointly.txt";
    private final String new2020W4SingleMarriedFilingSeperately = "New_2020_W4_IRS_Pub_15_Percentage_Method_Table_SingleMarriedFilingSeperately.txt";

    private String filePath = "";

    private String witholdingFileNameWithPath = "";

    public W4_2020_LaterTentativeWitholding(int payYear, String filePathDataDir)
    {
        witholdingFileNameWithPath = filePathDataDir + "\\witholding\\" + payYear  ;

    }

    public void initialize(FederalFilingStatusEnum federalFilingStatus, int payYear) throws IOException
    {
        if (federalFilingStatus  == FederalFilingStatusEnum.MARRIEDFILINGJOINTLY_OR_QUALIFYINGWIDOWER)
        {
            witholdingFileNameWithPath = witholdingFileNameWithPath + "\\" + new2020W4MarriedFilingJointly;
            System.out.println(witholdingFileNameWithPath);
            readFile(witholdingFileNameWithPath);
        }

        if (federalFilingStatus  == FederalFilingStatusEnum.HEADOFHOUSEHOLD)
        {
            witholdingFileNameWithPath = witholdingFileNameWithPath + "\\" + new2020W4HeadOfHousehold;
            System.out.println(witholdingFileNameWithPath);
            readFile(witholdingFileNameWithPath);
        }

        if (federalFilingStatus  == FederalFilingStatusEnum.SINGLE_OR_MARRIEDFILINGSEPERATELY)
        {
            witholdingFileNameWithPath = witholdingFileNameWithPath + "\\" + new2020W4SingleMarriedFilingSeperately;
            System.out.println(witholdingFileNameWithPath);
            readFile(witholdingFileNameWithPath);
        }
    }


    private void readFile(String filePath) throws IOException
    {
        try
        {
            String readLine = "";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            while ((readLine = bufferedReader.readLine()) != null)
            {

                String [] splitString = readLine.split(",");

                double minAmount = Double.parseDouble(splitString[0]);
                double maxAmount = Double.parseDouble(splitString[1]);
                double tentativeWitholdAmount = Double.parseDouble(splitString[2]);
                double percentage = Double.parseDouble(splitString[3]);
                double exceededAdjustedAnnualAmount = Double.parseDouble(splitString[4]);

                W42020Later w42020Later = new W42020Later(minAmount,maxAmount,tentativeWitholdAmount,percentage,exceededAdjustedAnnualAmount);
                w42020LaterArrayList.add(w42020Later);
            }
        }
        catch (IOException e)
        {
            System.out.println("Could not open file " + filePath + " for reading. " + e.getMessage());
            throw e;
        }

    }

    public double getTentativeWitholdingAmount(double adjustedAnnualWageAmount, double payPeriod)
    {
        double columnD;
        double columnC;
        double columnA;
        double tentativeWitholdingAmount = 0.0;
        for (W42020Later w42020Later: w42020LaterArrayList)
        {

            if (w42020Later.getMinAmount() <= adjustedAnnualWageAmount && w42020Later.getMaxAmount() > adjustedAnnualWageAmount)
            {
                columnA = w42020Later.getMinAmount();
                columnC = w42020Later.getTentativeWitholdAmount();
                columnD = w42020Later.getPercentage();

                double line2e = adjustedAnnualWageAmount -columnA;
                double line2f = line2e * columnD;
                double line2g = columnC  + line2f;
                tentativeWitholdingAmount = line2g/ payPeriod;

            }

        }
        return tentativeWitholdingAmount;
    }
}
