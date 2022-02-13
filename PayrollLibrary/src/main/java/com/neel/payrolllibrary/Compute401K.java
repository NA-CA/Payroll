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

package com.neel.payrolllibrary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Compute401K
{
    private HashMap<Integer, Max401K> hashMap = new HashMap<>();
    private final String fileName401K = "401K.txt";
    private int year = 0;
    private double age = 0.0;
    private String ge401kFileNameWithPath = "";
    private double deferallPercentage = 0.0;

    public Compute401K(int year, String filePath401k, double age)
    {
        this.year = year;
        this.age = age;
        this.ge401kFileNameWithPath = filePath401k + "\\" + fileName401K;

    }

    public void initialize() throws IOException
    {
        compute401KContribution();
    }


    public void compute401KContribution() throws IOException
    {
        try
        {
            FileReader fileReader = new FileReader(ge401kFileNameWithPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readLine = "";

            bufferedReader.readLine();
            while((readLine = bufferedReader.readLine()) != null)
            {
                String [] splitString = readLine.split(",");
                int year = Integer.parseInt(splitString[0]);
                double below50ContributionLimit = Double.parseDouble(splitString[1]);
                double above50ContributionLimit = Double.parseDouble(splitString[2]);
                Max401K max401K = new Max401K(year,below50ContributionLimit,above50ContributionLimit);
                hashMap.put(year, max401K);

            }
        }
        catch (IOException e)
        {
            System.out.println("Could not open file " + ge401kFileNameWithPath + " for reading. " + e.getMessage());
            throw e;
        }
    }

    public double employeeContribution( double grossSalary, double ytd401K, double deferalPercentage) throws Exception
    {
        if (!hashMap.containsKey(year))
        {
            throw new Exception("File" + ge401kFileNameWithPath + " does not have year" + year);
        }
        double contribution = 0.0;

        if (age >= 50.0)
        {
            if (ytd401K > hashMap.get(year).getAbove50ContributionLimit())
            {
                return 0.0;
            }
            else if (ytd401K + grossSalary * deferalPercentage > hashMap.get(year).getAbove50ContributionLimit())
            {
                contribution = (hashMap.get(year).getAbove50ContributionLimit() - ytd401K) * deferalPercentage;
            }
            else
            {
                contribution = grossSalary * deferalPercentage;
            }

        }
        else
        {
            if (ytd401K > hashMap.get(year).getBelow50ContribtuionLimit())
            {
                return 0.0;
            }
            else if ((ytd401K + (grossSalary * deferalPercentage)) > hashMap.get(year).getBelow50ContribtuionLimit())
            {
                contribution = (hashMap.get(year).getBelow50ContribtuionLimit() - ytd401K);
            }
            else
            {
                contribution = grossSalary * deferalPercentage;
            }
        }

        return contribution;
    }
}
