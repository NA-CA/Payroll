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

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputeFUTATest
{

    @Description("No Tax")
    @Test
    void computeFutaTax()
    {
        ComputeFUTA computeFUTA = new ComputeFUTA(9000, 500, 0.06);
        double futaTax = computeFUTA.computeFutaTax();

        System.out.println(futaTax);

        assertTrue(futaTax >= 0 && futaTax <= 0, "futaTax");
    }

    @Description("Partial tax ")
    @Test
    void computeFutaTax2()
    {
        ComputeFUTA computeFUTA = new ComputeFUTA(5000, 500, 0.06);
        double futaTax = computeFUTA.computeFutaTax();

        System.out.println(futaTax);

        assertTrue(futaTax >= 30.0 && futaTax <= 30.0, "futaTax");
    }

    @Description("Partially taxed wages ")
    @Test
    void computeFutaTax3()
    {
        ComputeFUTA computeFUTA = new ComputeFUTA(6500, 1000, 0.06);
        double futaTax = computeFUTA.computeFutaTax();

        System.out.println(futaTax);

        assertTrue(futaTax >= 30.0 && futaTax<= 30.0, "futaTax");
    }

    @Description("Partially taxed wages ")
    @Test
    void computeFutaTax4()
    {
        ComputeFUTA computeFUTA = new ComputeFUTA(6999, 100000, 0.06);
        double futaTax = computeFUTA.computeFutaTax();

        System.out.println(futaTax);

        assertTrue(futaTax >= 0.06 && futaTax<= 0.06, "futaTax");
    }

    @Description("Partially taxed wages ")
    @Test
    void computeFutaTax5()
    {
        ComputeFUTA computeFUTA = new ComputeFUTA(0, 100000, 0.06);
        double futaTax = computeFUTA.computeFutaTax();

        System.out.println(futaTax);

        assertTrue(futaTax >= 420 && futaTax<= 420, "futaTax");
    }

}