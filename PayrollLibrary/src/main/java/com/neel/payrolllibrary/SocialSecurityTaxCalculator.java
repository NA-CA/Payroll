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

public class SocialSecurityTaxCalculator
{
    private HashMap<Integer, SocialSecurity> hashMap = new HashMap<>();
    private final String ssFileName = "SocialSecurity.txt";
    private int year = 0;
    private String getSsFileNameWithPath = "";

    public SocialSecurityTaxCalculator(int year, String ssFilePath)
    {
        this.year = year;
        this.getSsFileNameWithPath = ssFilePath + "\\" + ssFileName;
    }


    public void initialize() throws IOException
    {
        readSocialSecurityTaxFile();
    }

    private void readSocialSecurityTaxFile() throws IOException
    {
        try
        {

            String readLine = "";
            FileReader fileReader = new FileReader(getSsFileNameWithPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();

            while ((readLine = bufferedReader.readLine()) != null)
            {
                String [] splitString = readLine.split(",");
                int year = Integer.parseInt(splitString[0]);
                double employeeRate = Double.parseDouble(splitString[1]);
                double employerRate = Double.parseDouble(splitString[2]);
                double maxAmount = Double.parseDouble(splitString[3]);

                SocialSecurity socialSecurity = new SocialSecurity(year, employeeRate,employerRate,maxAmount);
                hashMap.put(year, socialSecurity);
            }

        }
        catch (IOException e)
        {
            System.out.println("Could not open file " + getSsFileNameWithPath + " for reading. " + e.getMessage());
            throw e;
        }

    }

    public double employeeSocialSecurityTax(double grossWagesYTD, double grossWagesCurrPayPeriod) throws Exception
    {
        validate(grossWagesYTD,grossWagesCurrPayPeriod);

        double yearlyAmount = hashMap.get(year).getSocialSecurityWageLimit();
        double employeeDeduction = 0.0;

        if (grossWagesYTD > yearlyAmount)
        {
            return 0.0;
        }
        else if (grossWagesYTD + grossWagesCurrPayPeriod > yearlyAmount)
        {
            employeeDeduction = (yearlyAmount - grossWagesYTD) * hashMap.get(year).getEmployeeRate();

        }
        else
        {
            employeeDeduction = grossWagesCurrPayPeriod * hashMap.get(year).getEmployeeRate();
        }

        return employeeDeduction;
    }

    public double employerSocialSecurityTax(double grossWagesYTD, double grossWagesCurrPayPeriod) throws Exception
    {
        validate(grossWagesYTD,grossWagesCurrPayPeriod);

        double yearlyAmount = hashMap.get(year).getSocialSecurityWageLimit();
        double employeeDeduction = 0.0;

        if (grossWagesYTD > yearlyAmount)
        {
            return 0.0;
        }
        else if (grossWagesYTD + grossWagesCurrPayPeriod > yearlyAmount)
        {
            employeeDeduction = (yearlyAmount - grossWagesYTD) * hashMap.get(year).getEmployerRate();

        }
        else
        {
            employeeDeduction = grossWagesCurrPayPeriod * hashMap.get(year).getEmployerRate();
        }

        return employeeDeduction;
    }

    private void validateInputParameter(double grossWagesYTD, double grossWagesCurrPayPeriod) throws Exception
    {
        if (!hashMap.containsKey(year))
        {
            throw new Exception("File" + getSsFileNameWithPath + " does not have year" + year);
        }

        if (grossWagesYTD < 0)
        {
            throw new Exception("Your salary is invalid");
        }
        if (grossWagesCurrPayPeriod < 0)
        {
            throw new Exception("Your weekly salary is invalid");
        }
    }

    private void validateSSFileReading() throws Exception
    {

        if (hashMap.size() == 0)
        {
            throw new Exception("Failed to read social security tax file");
        }

        if (year <= 0)
        {
            throw new Exception("Your year is invalid");

        }
    }

    private void validate(double grossWagesYTD, double grossWagesCurrPayPeriod) throws Exception
    {
        validateSSFileReading();
        validateInputParameter(grossWagesYTD,grossWagesCurrPayPeriod);
    }


}
