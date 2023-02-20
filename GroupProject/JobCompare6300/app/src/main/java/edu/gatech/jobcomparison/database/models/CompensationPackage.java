package edu.gatech.jobcomparison.database.models;


public class CompensationPackage {
    private int yearlySalary;
    private int yearlyBonus;
    private int relocationStripend;
    private int retirementBenifits;
    private int rSU;
    private int costOfLivingIdex;

    public CompensationPackage(String yearlySalary, String yearlyBonus, String relocationStripend, String retirementBenifits, String rSU, String costOfLivingIdex) {
        this.yearlySalary = Integer.parseInt(yearlySalary);
        this.yearlyBonus = Integer.parseInt(yearlyBonus);
        this.relocationStripend = Integer.parseInt(relocationStripend);
        this.retirementBenifits = Integer.parseInt(retirementBenifits);
        this.rSU = Integer.parseInt(rSU);
        this.costOfLivingIdex = Integer.parseInt(costOfLivingIdex);
    }

    public CompensationPackage(int yearlySalary, int yearlyBonus, int relocationStripend, int retirementBenifits, int rSU, int costOfLivingIdex) {
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.relocationStripend = relocationStripend;
        this.retirementBenifits = retirementBenifits;
        this.rSU = rSU;
        this.costOfLivingIdex = costOfLivingIdex;
    }

    private long costOfLivingAdjustment(int value){
        return value/this.costOfLivingIdex*100;
    }
    public long getA_yearlySalary() {
        return costOfLivingAdjustment(yearlySalary);
    }

    public long getA_yearlyBonus() {
        return costOfLivingAdjustment(yearlyBonus);
    }

    public int getRelocationStripend() {
        return relocationStripend;
    }

    public long getA_retirementBenifits() {
        return costOfLivingAdjustment(retirementBenifits*yearlySalary/100);
    }

    public long getA_rSU() {
        return rSU/4;
    }

    public int getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(int yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public int getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(int yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }


    public void setRelocationStripend(int relocationStripend) {
        this.relocationStripend = relocationStripend;
    }

    public int getRetirementBenifits() {
        return retirementBenifits;
    }

    public void setRetirementBenifits(int retirementBenifits) {
        this.retirementBenifits = retirementBenifits;
    }

    public int getrSU() {
        return rSU;
    }

    public void setrSU(int rSU) {
        this.rSU = rSU;
    }

    public int getCostOfLivingIdex() {
        return costOfLivingIdex;
    }

    public void setCostOfLivingIdex(int costOfLivingIdex) {
        this.costOfLivingIdex = costOfLivingIdex;
    }
}
