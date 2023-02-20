package edu.gatech.jobcomparison.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Weight {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "yearly_salary_weight")
    private String yearlySalaryWeight;
    @ColumnInfo(name = "yearly_bonus_weight")
    private String yearlyBonusWeight;
    @ColumnInfo(name = "retirement_benefit_weight")
    private String retirementBenefitWeight;
    @ColumnInfo(name = "relocation_stipend_weight")
    private String relocationStipendWeight;
    @ColumnInfo(name = "restricted_stock_unit_award_weight")
    private String restrictedStockUnitAwardWeight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYearlySalaryWeight() {
        return yearlySalaryWeight;
    }

    public void setYearlySalaryWeight(String yearlySalaryWeight) {
        this.yearlySalaryWeight = yearlySalaryWeight;
    }

    public String getYearlyBonusWeight() {
        return yearlyBonusWeight;
    }

    public void setYearlyBonusWeight(String yearlyBonusWeight) {
        this.yearlyBonusWeight = yearlyBonusWeight;
    }

    public String getRetirementBenefitWeight() {
        return retirementBenefitWeight;
    }

    public void setRetirementBenefitWeight(String retirementBenefitWeight) {
        this.retirementBenefitWeight = retirementBenefitWeight;
    }

    public String getRelocationStipendWeight() {
        return relocationStipendWeight;
    }

    public void setRelocationStipendWeight(String relocationStipendWeight) {
        this.relocationStipendWeight = relocationStipendWeight;
    }

    public String getRestrictedStockUnitAwardWeight() {
        return restrictedStockUnitAwardWeight;
    }

    public void setRestrictedStockUnitAwardWeight(String restrictedStockUnitAwardWeight) {
        this.restrictedStockUnitAwardWeight = restrictedStockUnitAwardWeight;
    }
}
