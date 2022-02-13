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

public class W4_2019_EarlierTentativeWitholding
{
    private ArrayList<W42019Earlier> w42019EarlierArrayList = new ArrayList<>();


    private final String old2019W4HeadOfHousehold = "Old_2019_W4_IRS_Pub_15_Percentage_Method_Table_HeadOfHousehold.txt";
    private final String old2019W4MarriedFilingJointly = "Old_2019_W4_IRS_Pub_15_Percentage_Method_Table_MarriedFilingJointly.txt";
    private final String old2019W4SingleMarriedFilingSeperately = "Old_2019_W4_IRS_Pub_15_Percentage_Method_Table_SingleMarriedFilingSeperately.txt";

    private String filePath = "";

    private String witholdingFileNameWithPath = "";

    public W4_2019_EarlierTentativeWitholding(int payYear, String filePathDataDir)
    {
        witholdingFileNameWithPath = filePathDataDir + "\\witholding\\" + payYear  ;

    }


    public void initialize(FederalFilingStatusEnum federalFilingStatus, int payYear) throws IOException
    {
        if (federalFilingStatus  == FederalFilingStatusEnum.MARRIEDFILINGJOINTLY_OR_QUALIFYINGWIDOWER)
        {
            witholdingFileNameWithPath = witholdingFileNameWithPath + "\\" + old2019W4MarriedFilingJointly;
            System.out.println(witholdingFileNameWithPath);
            readFile(witholdingFileNameWithPath);
        }

        if (federalFilingStatus  == FederalFilingStatusEnum.HEADOFHOUSEHOLD)
        {
            witholdingFileNameWithPath = witholdingFileNameWithPath + "\\" + old2019W4HeadOfHousehold;
            readFile(witholdingFileNameWithPath);
        }

        if (federalFilingStatus  == FederalFilingStatusEnum.SINGLE_OR_MARRIEDFILINGSEPERATELY)
        {
            witholdingFileNameWithPath = witholdingFileNameWithPath + "\\" + old2019W4SingleMarriedFilingSeperately;
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

                W42019Earlier w42019Earlier = new W42019Earlier(minAmount, maxAmount,
                        tentativeWitholdAmount, percentage, exceededAdjustedAnnualAmount);

                w42019EarlierArrayList.add(w42019Earlier);
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
        for (W42019Earlier W42019Earlier: w42019EarlierArrayList)
        {
            if (W42019Earlier.getMinAmount() <= adjustedAnnualWageAmount && W42019Earlier.getMaxAmount() > adjustedAnnualWageAmount)
            {
                columnA = W42019Earlier.getMinAmount();
                columnC = W42019Earlier.getTentativeWitholdAmount();
                columnD = W42019Earlier.getPercentage();

                double line2e = adjustedAnnualWageAmount -columnA;
                double line2f = line2e * columnD;
                double line2g = columnC  + line2f;
                tentativeWitholdingAmount = line2g/ payPeriod;

            }

        }
        return tentativeWitholdingAmount;
    }

}
