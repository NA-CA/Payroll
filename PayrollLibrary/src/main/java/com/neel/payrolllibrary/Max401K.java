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

public class Max401K
{
    private int year = 0;
    private double below50ContribtuionLimit = 0.0;
    private double above50ContributionLimit = 0.0;

    public Max401K(int year,  double below50ContribtuionLimit, double above50ContributionLimit)
    {
        this.year = year;
        this.below50ContribtuionLimit = below50ContribtuionLimit;
        this.above50ContributionLimit = above50ContributionLimit;
    }

    @Override
    public String toString()
    {
        return "Max401K{" +
                "year=" + year +
                ", below50ContribtuionLimit=" + below50ContribtuionLimit +
                ", above50ContributionLimit=" + above50ContributionLimit +
                '}';
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }



    public double getBelow50ContribtuionLimit()
    {
        return below50ContribtuionLimit;
    }

    public void setBelow50ContribtuionLimit(double below50ContribtuionLimit)
    {
        this.below50ContribtuionLimit = below50ContribtuionLimit;
    }

    public double getAbove50ContributionLimit()
    {
        return above50ContributionLimit;
    }

    public void setAbove50ContributionLimit(double above50ContributionLimit)
    {
        this.above50ContributionLimit = above50ContributionLimit;
    }
}
