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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SocialSecurityTaxCalculatorTest
{

    @Test
    void employeeSocialSecurityTax() throws Exception
    {
        SocialSecurityTaxCalculator socialSecurityTaxCalculator = new SocialSecurityTaxCalculator(2020, PayrollLibUnitTestUtil.getDataPath());
        socialSecurityTaxCalculator.initialize();
        double socialSecurity = socialSecurityTaxCalculator.employeeSocialSecurityTax(100000, 10000);

        System.out.println(socialSecurity);

        assertTrue(socialSecurity == 620, "medicare tax check");

    }

    @Test
    void employeeSocialSecurityTax1() throws Exception
    {
        SocialSecurityTaxCalculator socialSecurityTaxCalculator = new SocialSecurityTaxCalculator(2020, PayrollLibUnitTestUtil.getDataPath());
        socialSecurityTaxCalculator.initialize();
        double socialSecurity = socialSecurityTaxCalculator.employeeSocialSecurityTax(100000, 10000000);

        System.out.println(socialSecurity);

        assertTrue(socialSecurity == 2337.4, "medicare tax check");

    }

    @Test
    void employeeSocialSecurityTax2() throws Exception
    {
        SocialSecurityTaxCalculator socialSecurityTaxCalculator = new SocialSecurityTaxCalculator(2020, PayrollLibUnitTestUtil.getDataPath());
        socialSecurityTaxCalculator.initialize();
        double socialSecurity = socialSecurityTaxCalculator.employeeSocialSecurityTax(100000, 300);

        System.out.println(socialSecurity);

        assertTrue(socialSecurity == 18.6, "medicare tax check");

    }

    @Test
    void UnitTestSocialSecurity1() throws Exception
    {
        SocialSecurityTaxCalculator socialSecurityTaxCalculator = new SocialSecurityTaxCalculator(2010, PayrollLibUnitTestUtil.getDataPath());
        socialSecurityTaxCalculator.initialize();
        double ssnWitholding = socialSecurityTaxCalculator.employeeSocialSecurityTax(105000,5000);
        double emplopyerWitholding = socialSecurityTaxCalculator.employerSocialSecurityTax(105000,5000);

        System.out.println(ssnWitholding);

        assertTrue(ssnWitholding == 111.6, "medicare tax check");
    }

    @Test
    void UnitTestSocialSecurity2() throws Exception
    {
        SocialSecurityTaxCalculator socialSecurityTaxCalculator = new SocialSecurityTaxCalculator(2010, PayrollLibUnitTestUtil.getDataPath());
        socialSecurityTaxCalculator.initialize();
        double ssnWitholding = socialSecurityTaxCalculator.employeeSocialSecurityTax(90000,5000);
        double emplopyerWitholding = socialSecurityTaxCalculator.employerSocialSecurityTax(90000,5000);

        System.out.println(ssnWitholding);

        assertTrue(ssnWitholding == 310.0, "medicare tax check");
    }

    @Test
    void UnitTestSocialSecurity3() throws Exception
    {
        SocialSecurityTaxCalculator socialSecurityTaxCalculator = new SocialSecurityTaxCalculator(2010, PayrollLibUnitTestUtil.getDataPath());
        socialSecurityTaxCalculator.initialize();
        double ssnWitholding = socialSecurityTaxCalculator.employeeSocialSecurityTax(110000,5000);
        double emplopyerWitholding = socialSecurityTaxCalculator.employerSocialSecurityTax(110000,5000);

        System.out.println(ssnWitholding);

        assertTrue(ssnWitholding == 0.0, "medicare tax check");
    }


}