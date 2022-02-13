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

class MedicareTaxCalculatorTest
{



    @Test
    void computeMedicareEmployee()
    {
        MedicareTaxCalculator medicareTaxCalculator = new MedicareTaxCalculator();
        double medicareTax = medicareTaxCalculator.computeMedicareEmployee(1000, 500);
        System.out.println(medicareTax);

        assertTrue(medicareTax == 7.25, "medicare tax check");
    }

    @Test
    void computeMedicareEmployee2()
    {
        MedicareTaxCalculator medicareTaxCalculator = new MedicareTaxCalculator();
        double medicareTax = medicareTaxCalculator.computeMedicareEmployee(199000, 10000);
        System.out.println(medicareTax);

        assertTrue(medicareTax == 226.0, "medicare tax check");
    }

    @Test
    void computeMedicareEmployee3()
    {
        MedicareTaxCalculator medicareTaxCalculator = new MedicareTaxCalculator();
        double medicareTax = medicareTaxCalculator.computeMedicareEmployee(700, 500000);
        System.out.println(medicareTax);

        assertTrue(medicareTax == 9956.3, "medicare tax check");
    }

    @Test
    void computeMedicareEmployee4()
    {
        MedicareTaxCalculator medicareTaxCalculator = new MedicareTaxCalculator();
        double medicareTax = medicareTaxCalculator.computeMedicareEmployee(199999, 100000);
        System.out.println(medicareTax);

        assertTrue(medicareTax == 2349.991, "medicare tax check");
    }

    @Test
    void computeEmployerMedicareTax()
    {
        MedicareTaxCalculator medicareTaxCalculator = new MedicareTaxCalculator();
        double medicareTax = medicareTaxCalculator.computeEmployerMedicareTax(199999, 100000);
        System.out.println(medicareTax);

        assertTrue(medicareTax == 1450, "medicare tax check");
    }

    @Test
    void computeEmployerMedicareTax2()
    {
        MedicareTaxCalculator medicareTaxCalculator = new MedicareTaxCalculator();
        double medicareTax = medicareTaxCalculator.computeEmployerMedicareTax(199999, 500);
        System.out.println(medicareTax);

        assertTrue(medicareTax == 7.25, "medicare tax check");
    }
}