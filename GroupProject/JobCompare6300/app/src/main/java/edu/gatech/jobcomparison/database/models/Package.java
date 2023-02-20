package edu.gatech.jobcomparison.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Job.class, parentColumns = "id", childColumns = "jobId", onDelete = ForeignKey.CASCADE)
})
public class Package {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "yearlySalary")
    private String yearlySalary;

    @ColumnInfo(name = "yearlyBonus")
    private String yearlyBonus;

    @ColumnInfo(name = "benefits")
    private String benefits;

    @ColumnInfo(name = "stipend")
    private String stipend;

    @ColumnInfo(name = "rsua")
    private String rsua;

    @ColumnInfo(name = "jobId")
    private int jobId;

    @ColumnInfo(name = "costOfLivingIndex")
    private String costOflivingIndex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(String yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public String getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(String yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getStipend() {
        return stipend;
    }

    public void setStipend(String stipend) {
        this.stipend = stipend;
    }

    public String getRsua() {
        return rsua;
    }

    public void setRsua(String rsua) {
        this.rsua = rsua;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getCostOflivingIndex() {
        return costOflivingIndex;
    }

    public void setCostOflivingIndex(String costOflivingIndex) {
        this.costOflivingIndex = costOflivingIndex;
    }
}
