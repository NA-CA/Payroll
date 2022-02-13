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

public class ComputeStateDisability
{
    private HashMap<Integer, StateDisability> hashMap = new HashMap<>();
    private final String fileNameSDI = "CAStateDisability.txt";
    private int year = 0;
    private String getSDIFileNameWithPath = "";

    public ComputeStateDisability(int year, String filePathSDI)
    {
        this.year = year;
        this.getSDIFileNameWithPath = filePathSDI + "\\" + fileNameSDI;
    }

    public void initialize() throws IOException
    {
        storeStateDisability();
    }

    public void storeStateDisability() throws IOException
    {
        try
        {
            String readLine = "";
            FileReader fileReader = new FileReader(getSDIFileNameWithPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();

            while((readLine = bufferedReader.readLine()) != null)
            {
                String [] splitString = readLine.split(",");
                int year = Integer.parseInt(splitString[0]);
                double maxAmount = Double.parseDouble(splitString[1]);
                StateDisability stateDisability = new StateDisability(year,maxAmount);
                hashMap.put(year,stateDisability);

            }

        }
        catch (IOException e)
        {
            System.out.println("Could not open file " + getSDIFileNameWithPath + " for reading. " + e.getMessage());
            throw e;
        }
    }

    public double employeeDeduction(double ytdSalary, double grossSalary, double sdiRate) throws Exception
    {
        validate(ytdSalary,grossSalary);

        double maxYearlyAmount = hashMap.get(year).getMaxAmount();
        double employeeDeduction = 0.0;

        if (ytdSalary > maxYearlyAmount)
        {
            return 0.0;
        }


        else if (ytdSalary + grossSalary > maxYearlyAmount)
        {
            employeeDeduction = (maxYearlyAmount - ytdSalary) * sdiRate;

        }
        else
        {
            employeeDeduction = grossSalary * sdiRate;
        }

        return employeeDeduction;
    }

    private void validateInputParameter(double employeeCummalativeSalary, double weeklySalary) throws Exception
    {
        if (employeeCummalativeSalary < 0)
        {
            throw new Exception("Your salary is invalid");
        }
        if (weeklySalary < 0)
        {
            throw new Exception("Your weekly salary is invalid");
        }
    }

    private void validateInitilaizing() throws Exception
    {

        if (hashMap.size() == 0)
        {
            throw new Exception("Your hashmap is empty");
        }

        if (year <= 0)
        {
            throw new Exception("Your year is invalid");

        }
    }

    private void validate(double employeeCummalativeSalary, double weeklySalary) throws Exception
    {
        validateInitilaizing();
        validateInputParameter(employeeCummalativeSalary,weeklySalary);
    }
}
