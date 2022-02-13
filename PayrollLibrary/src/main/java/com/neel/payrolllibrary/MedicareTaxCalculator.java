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

public class MedicareTaxCalculator
{

    /*

    401K salary deferral is subject to social security and medicare taxes
     */

    /*
    Additional Medicare Tax applies to an individual's Medicare wages that exceed a threshold amount based on the taxpayer's filing status.
    Employers are responsible for withholding the 0.9% Additional Medicare Tax on an individual's wages paid in excess of
    $200,000 in a calendar year, without regard to filing status.
    An employer is required to begin withholding Additional Medicare Tax in the pay period in which it pays wages in excess
     of $200,000 to an employee and continue to withhold it each pay period until the end of the calendar year.
     There's no employer match for Additional Medicare Tax.
    For more information, see the Instructions for Form 8959 and Questions and Answers for the Additional Medicare Tax.
     */

    private static final double medicareTaxRate = 0.0145;
    private static final double additionalMedicareTaxRate = 0.009;


    public  double computeMedicareEmployee(double ytdGrossWages, double grossWagesCurrPayPeriod)
    {
        double flatMedicareTax = grossWagesCurrPayPeriod * medicareTaxRate;

        double additionalMedicareTax = 0.0;
        if (ytdGrossWages >= 200000)
        {
            additionalMedicareTax = grossWagesCurrPayPeriod * additionalMedicareTaxRate;
        }
        else if (ytdGrossWages + grossWagesCurrPayPeriod >= 200000)
        {
            double medicareLowerRateAdditionalWages = (200000-ytdGrossWages);
            double  medicareHigherRateWages = grossWagesCurrPayPeriod - medicareLowerRateAdditionalWages;
            additionalMedicareTax = medicareHigherRateWages * additionalMedicareTaxRate;
        }

        return additionalMedicareTax + flatMedicareTax;
    }

    public  double computeEmployerMedicareTax(double ytdGrossWages, double grossWagesCurrPayPeriod)
    {
        double medicareTax = 0.0;
        medicareTax = grossWagesCurrPayPeriod * medicareTaxRate;

        return medicareTax;
    }

}
