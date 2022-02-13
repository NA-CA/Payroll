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

class ComputeStateDisabilityTest
{


    @Test
    void employeeDeduction() throws Exception
    {
        ComputeStateDisability computeStateDisability = new ComputeStateDisability(2020, PayrollLibUnitTestUtil.getDataPath());
        computeStateDisability.initialize();

        double employeeDeduction = computeStateDisability.employeeDeduction(100000, 5000, 0.13);
        System.out.println(employeeDeduction);

        assertTrue(employeeDeduction == 650.0, "medicare tax check");

    }

    @Test
    void UnitTestStateDisability2() throws Exception
    {
        ComputeStateDisability computeStateDisability = new ComputeStateDisability(2020, PayrollLibUnitTestUtil.getDataPath());
        computeStateDisability.initialize();
        double sdi = computeStateDisability.employeeDeduction(120000,5000, 0.13);
        System.out.println(sdi);

        assertTrue(sdi == 378.17, "medicare tax check");
    }

    @Test
    void UnitTestStateDisability3() throws Exception
    {
        ComputeStateDisability computeStateDisability = new ComputeStateDisability(2020, PayrollLibUnitTestUtil.getDataPath());
        computeStateDisability.initialize();
        double sdi = computeStateDisability.employeeDeduction(130000,5000, 0.13);
        System.out.println(sdi);

        assertTrue(sdi == 0.0, "medicare tax check");
    }
}