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

class Compute401KTest
{


    @Test
    void employeeContribution() throws Exception
    {
        Compute401K compute401K = new Compute401K(2021, PayrollLibUnitTestUtil.getDataPath(), 50.0);
        compute401K.initialize();

        double contribution = compute401K.employeeContribution(5000, 27000, 0.5);

        assertTrue(contribution == 0.0, "medicare tax check");
    }

    @Test
    void employeeContribution1() throws Exception
    {
        Compute401K compute401K = new Compute401K(2021, PayrollLibUnitTestUtil.getDataPath(), 50.0);
        compute401K.initialize();

        double contribution = compute401K.employeeContribution(5000, 20000, 0.5);

        assertTrue(contribution == 2500.0, "medicare tax check");
    }

    @Test
    void employeeContribution2() throws Exception
    {
        Compute401K compute401K = new Compute401K(2021, PayrollLibUnitTestUtil.getDataPath(), 50.0);
        compute401K.initialize();

        double contribution = compute401K.employeeContribution(5000, 25000, 0.5);

        assertTrue(contribution == 500.0, "medicare tax check");
    }

    @Test
    void employeeContribution3() throws Exception
    {
        Compute401K compute401K = new Compute401K(2021, PayrollLibUnitTestUtil.getDataPath(), 49.0);
        compute401K.initialize();
        double contribution = compute401K.employeeContribution(5000, 18500, 0.5);

        assertTrue(contribution == 1000, "401K partial check");
    }
}