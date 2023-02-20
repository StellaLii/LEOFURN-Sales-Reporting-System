package edu.gatech.jobcomparison.database.models;

public class CalculationUtil {

    public static long calculateRankScore(WeightDTO weights, CompensationPackage compensationPackage) {
        // write your code here
        WeightDTO weight = new WeightDTO(weights.getW_yearlySalary(),
                weights.getW_yearlyBonus(),
                weights.getW_relocationStripend(),
                weights.getW_rsu(),
                weights.getW_retirementBenifits());
        CompensationPackage p= new CompensationPackage(compensationPackage.getYearlySalary(),
                compensationPackage.getYearlyBonus(),
                compensationPackage.getRelocationStripend(),
                compensationPackage.getRetirementBenifits(),
                compensationPackage.getrSU(),
                compensationPackage.getCostOfLivingIdex());
        long rankScore;
        int sum=weight.getSum();
        rankScore=(p.getA_yearlyBonus()*weight.getW_yearlyBonus()+
                p.getA_yearlySalary()*weight.getW_yearlySalary()+
                p.getA_retirementBenifits()* weight.getW_retirementBenifits()+
                p.getA_rSU()* weight.getW_rsu()+
                p.getRelocationStripend()*weight.getW_relocationStripend())/sum;

        return rankScore;
    }
}
