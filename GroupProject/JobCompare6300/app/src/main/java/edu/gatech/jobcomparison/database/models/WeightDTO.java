package edu.gatech.jobcomparison.database.models;

public class WeightDTO {
    private int w_yearlySalary;
    private int w_yearlyBonus;
    private int w_relocationStripend;
    private int w_rsu;
    private int w_retirementBenifits;

    public WeightDTO(int w_yearlySalary, int w_yearlyBonus, int w_relocationStripend, int w_rsu, int w_retirementBenifits) {
        this.w_yearlySalary = w_yearlySalary;
        this.w_yearlyBonus = w_yearlyBonus;
        this.w_relocationStripend = w_relocationStripend;
        this.w_rsu = w_rsu;
        this.w_retirementBenifits = w_retirementBenifits;
    }

    public WeightDTO(String w_yearlySalary, String w_yearlyBonus, String w_relocationStripend, String w_rsu, String w_retirementBenifits) {
        this.w_yearlySalary = Integer.parseInt(w_yearlySalary);
        this.w_yearlyBonus = Integer.parseInt( w_yearlyBonus);
        this.w_relocationStripend = Integer.parseInt(w_relocationStripend);
        this.w_rsu = Integer.parseInt(w_rsu);
        this.w_retirementBenifits = Integer.parseInt(w_retirementBenifits);
    }

    public int getSum(){
        return this.w_relocationStripend+this.w_retirementBenifits+this.w_rsu+this.w_yearlyBonus+this.w_yearlyBonus;
    }
    public int getW_yearlySalary() {
        return w_yearlySalary;
    }

    public void setW_yearlySalary(int w_yearlySalary) {
        this.w_yearlySalary = w_yearlySalary;
    }

    public int getW_yearlyBonus() {
        return w_yearlyBonus;
    }

    public void setW_yearlyBonus(int w_yearlyBonus) {
        this.w_yearlyBonus = w_yearlyBonus;
    }

    public int getW_relocationStripend() {
        return w_relocationStripend;
    }

    public void setW_relocationStripend(int w_relocationStripend) {
        this.w_relocationStripend = w_relocationStripend;
    }

    public int getW_rsu() {
        return w_rsu;
    }

    public void setW_rsu(int w_rsu) {
        this.w_rsu = w_rsu;
    }

    public int getW_retirementBenifits() {
        return w_retirementBenifits;
    }

    public void setW_retirementBenifits(int w_retirementBenifits) {
        this.w_retirementBenifits = w_retirementBenifits;
    }


}