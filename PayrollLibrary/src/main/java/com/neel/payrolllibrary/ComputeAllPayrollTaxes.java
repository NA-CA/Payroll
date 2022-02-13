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

import com.neel.payrolllibrary.ca.CAFinalWithheldTax;

import java.io.IOException;

public class ComputeAllPayrollTaxes
{
    private PayrollInfo payrollInfo = new PayrollInfo();

    public ComputeAllPayrollTaxes()
    {

    }

    public ComputeAllPayrollTaxes(PayrollInfo payrollInfo)
    {
        this.payrollInfo = payrollInfo;
    }

    public void computeAllTaxes() throws Exception
    {
        EmployeePayrollTax employeePayrollTax = new EmployeePayrollTax();
        payrollInfo.setEmployeePayrollTax(employeePayrollTax);

        CompanyPayrollTax companyPayrollTax = new CompanyPayrollTax();
        payrollInfo.setCompanyPayrollTax(companyPayrollTax);


        computeFUTA();
        computeMedicareTax();
        computeSocialSecurityTax();
        compute401KDeferall();
        computeSDITax();


        computeFederalWitholding();
        computeStateWitholding();


    }

    private void computeFUTA()
    {
        double wagesSubjectedToFUTA = wagesForFUTAComputation();
        ComputeFUTA computeFUTA = new ComputeFUTA(
                payrollInfo.getEmployeePayrollInfo().getGrossWagesYTD(),
                wagesSubjectedToFUTA,
                payrollInfo.getCompanyPayrollRate().getFutaRate());

        double futaTax = computeFUTA.computeFutaTax();

        double roundedFutaTax = round2Decimals(futaTax);
        payrollInfo.getCompanyPayrollTax().setFutaTax(roundedFutaTax);


    }

    private void computeStateWitholding() throws IOException
    {

        double wagesSubjctedStateWitholding = wagesForStateWitholding();


        CAFinalWithheldTax caFinalWithheldTax = new CAFinalWithheldTax(
                wagesSubjctedStateWitholding,
                payrollInfo.getEmployeePayrollInfo().getCade4().getPayrollPeriod(),
                payrollInfo.getEmployeePayrollInfo().getCade4().getCaDe4Line1AregularWHStandardAllowane(),
                payrollInfo.getEmployeePayrollInfo().getCade4().getCaDe4Line1BestimatedDeduction_exemptionWHAllowance(),
                payrollInfo.getEmployeePayrollInfo().getCade4().getCaFilingStatusEnum(),
                payrollInfo.getDataPath(),
                payrollInfo.getEmployeePayrollInfo().getCade4().getEXEMPT());

        double statewitholding = caFinalWithheldTax.getCaliforniaWitholding();

        double roundedStateWitholding = round2Decimals(statewitholding);

        payrollInfo.getEmployeePayrollTax().setState_wh(roundedStateWitholding);


    }

    private void computeFederalWitholding() throws Exception
    {
        double wagesSubjectedFederalWitholding = wagesForFederalWitholding();

        ComputeFederalWithholding computeFederalWithholding = new ComputeFederalWithholding(
                payrollInfo.getYear(),
                wagesSubjectedFederalWitholding,
                payrollInfo.getPayrollPeriodEnum(),
                payrollInfo.getEmployeePayrollInfo().getEmployeeW4().getSubmittedAnyYearW4(),
                payrollInfo.getEmployeePayrollInfo().getEmployeeW4().getNew2020W4Step4Line4aOtherIncome(),
                payrollInfo.getEmployeePayrollInfo().getEmployeeW4().getNew2020W4Step4Line4bDeductions(),
                payrollInfo.getEmployeePayrollInfo().getEmployeeW4().getNew2020W4step2MultipleJobs(),
                payrollInfo.getEmployeePayrollInfo().getEmployeeW4().getOldW4NumOfAllowances(),
                payrollInfo.getEmployeePayrollInfo().getEmployeeW4().getFederalFilingStatusEnum(),
                payrollInfo.getEmployeePayrollInfo().getEmployeeW4().getW42020OrLater(),
                payrollInfo.getEmployeePayrollInfo().getEmployeeW4().getNew2020W4Step3Line3Dependents(),
                payrollInfo.getEmployeePayrollInfo().getEmployeeW4().getOldW4AdduitionWithHold(),
                payrollInfo.getEmployeePayrollInfo().getEmployeeW4().getNew2020W4Step4Line4aOtherIncome(),
                payrollInfo.getDataPath(),
                payrollInfo.getEmployeePayrollInfo().getEmployeeW4().getEXEMPT());

        double federalWitholding = computeFederalWithholding.federalWitholding();

        double roundedFederalWitholding = round2Decimals(federalWitholding);

        payrollInfo.getEmployeePayrollTax().setFederal_wh(roundedFederalWitholding);

    }

    private void compute401KDeferall() throws Exception
    {

        double wagesSubjctedTo401K = wagesFor401K();

        double age = 0.0;
        if (payrollInfo.getEmployeePayrollInfo().isFiftyPlus())
        {
            age = 50.0;
        }
        else
        {
            age = 25.0;
        }
        Compute401K compute401K = new Compute401K(payrollInfo.getYear(), payrollInfo.getDataPath(), age);
        compute401K.initialize();

        double emp401KContribtuion = compute401K.employeeContribution(
                wagesSubjctedTo401K,
                payrollInfo.getEmployeePayrollInfo().getEmployee401KYTD(),
                payrollInfo.getEmployeePayrollInfo().getEmployee401KDeferallRate());
        payrollInfo.getEmployeePayrollTax().setEmployee_401k(emp401KContribtuion);

        double roundedemp401KContribtuion = round2Decimals(emp401KContribtuion);

        payrollInfo.getEmployeePayrollTax().setEmployee_401k(roundedemp401KContribtuion);
    }

    private void computeSocialSecurityTax() throws Exception
    {
        double wagesSubjectedToSocialSecurity = wagesForSocialSecurityComputation();


        SocialSecurityTaxCalculator socialSecurityTaxCalculator = new SocialSecurityTaxCalculator(payrollInfo.getYear(), payrollInfo.getDataPath());
        socialSecurityTaxCalculator.initialize();

        double empSSTax = socialSecurityTaxCalculator.employeeSocialSecurityTax(payrollInfo.getEmployeePayrollInfo().getGrossWagesYTD(),
                wagesSubjectedToSocialSecurity);
        double employerSSTax = socialSecurityTaxCalculator.employeeSocialSecurityTax(payrollInfo.getEmployeePayrollInfo().getGrossWagesYTD(),
                wagesSubjectedToSocialSecurity);

        double roundedempSSTax = round2Decimals(empSSTax);
        double roundedemployerSSTax = round2Decimals(employerSSTax);

        payrollInfo.getEmployeePayrollTax().setSs(roundedempSSTax);
        payrollInfo.getCompanyPayrollTax().setSsTax(roundedemployerSSTax);


    }

    private void computeMedicareTax()
    {

        double wagesSubjectedToMedicare =  wagesForMedicareComputation();

        MedicareTaxCalculator medicareTaxCalculator = new MedicareTaxCalculator();

        double empMedicareTax = medicareTaxCalculator.computeMedicareEmployee(payrollInfo.getEmployeePayrollInfo().getGrossWagesYTD(), wagesSubjectedToMedicare);
        double roundedempMedicareTax = round2Decimals(empMedicareTax);
        payrollInfo.getEmployeePayrollTax().setMedicare(roundedempMedicareTax);

        double employerMedicareTax = medicareTaxCalculator.computeEmployerMedicareTax(payrollInfo.getEmployeePayrollInfo().getGrossWagesYTD(), wagesSubjectedToMedicare);
        double roundedemployerMedicareTax = round2Decimals(employerMedicareTax);
        payrollInfo.getCompanyPayrollTax().setMedicareTax(roundedemployerMedicareTax);

    }



    private void computeSDITax() throws Exception
    {

        double wagesSubjectedSDI = wagesForSDIWitholding();

        ComputeStateDisability computeStateDisability = new ComputeStateDisability(payrollInfo.getYear(), payrollInfo.getDataPath());
        computeStateDisability.initialize();

        double employeeSDITax = computeStateDisability.employeeDeduction(

                payrollInfo.getEmployeePayrollInfo().getGrossWagesYTD(),
                wagesSubjectedSDI,
                payrollInfo.getEmployeePayrollInfo().getSdiRate());

        double roundedemployeeSDITax = round2Decimals(employeeSDITax);
        payrollInfo.getEmployeePayrollTax().setSdi(roundedemployeeSDITax);


    }

    private double round2Decimals(double unroundedValue)
    {
        double roundedValue = Math.round(unroundedValue * 100.0) / 100.0;
        return roundedValue;


    }

    private double wagesForFUTAComputation()
    {
        // in the future account for additions and deductions like Section 125 and other benefits and employer contributions
        double additionalFUTAWages = 0.0;
        double futaDeductionsWages = 0.0;

       double grossWagesCurrPayPeriod =  payrollInfo.getEmployeePayrollInfo().getGrossWagesCurrPayPeriod();
       return grossWagesCurrPayPeriod + additionalFUTAWages - futaDeductionsWages;
    }

    private double wagesForMedicareComputation()
    {
        // in the future account for additions and deductions like Section 125 and other benefits and employer contributions
        double additionalMedicareWages = 0.0;
        double medicareDeductionsWages = 0.0;

        double wagesSubjectedToMedicare = payrollInfo.getEmployeePayrollInfo().getGrossWagesCurrPayPeriod();
        return wagesSubjectedToMedicare + additionalMedicareWages - medicareDeductionsWages;
    }

    private double wagesForSocialSecurityComputation()
    {
        double additionalSocialSecurityWages = 0.0;
        double deductionsSocialSecurityWages = 0.0;

        double wagesSubjctedToSocialSecurity = payrollInfo.getEmployeePayrollInfo().getGrossWagesCurrPayPeriod();
        return wagesSubjctedToSocialSecurity + additionalSocialSecurityWages - deductionsSocialSecurityWages;
    }

    private double wagesFor401K()
    {
        double additional401KWages = 0.0;
        double deductions401KWages = 0.0;

        double wagesSubjectedTo401K = payrollInfo.getEmployeePayrollInfo().getGrossWagesCurrPayPeriod();
        return wagesSubjectedTo401K + additional401KWages - deductions401KWages;
    }

    private double wagesForFederalWitholding()
    {
        double additionalFederalWitholdingWages = 0.0;
        double deductionsFederalWitholdingWages = payrollInfo.getEmployeePayrollTax().getEmployee_401k();

        double wagesSubjctedToFederalWitholding = payrollInfo.getEmployeePayrollInfo().getGrossWagesCurrPayPeriod();
        return wagesSubjctedToFederalWitholding + additionalFederalWitholdingWages - deductionsFederalWitholdingWages;
    }

    private double wagesForStateWitholding()
    {
        double additionalStateWitholdingWages = 0.0;
        double deductionsStateWitholdingWages = payrollInfo.getEmployeePayrollTax().getEmployee_401k();

        double wagesSubjctedToStateWitholding = payrollInfo.getEmployeePayrollInfo().getGrossWagesCurrPayPeriod();
        return wagesSubjctedToStateWitholding + additionalStateWitholdingWages - deductionsStateWitholdingWages;
    }

    private double wagesForSDIWitholding()
    {
        double additionalSDIWitholding = 0.0;
        double deductionsSDIWitholding = 0.0;

        double wagesSubjctedToSDIWitholding = payrollInfo.getEmployeePayrollInfo().getGrossWagesCurrPayPeriod();
        return wagesSubjctedToSDIWitholding + additionalSDIWitholding - deductionsSDIWitholding;
    }


}
