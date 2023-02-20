package edu.gatech.jobcomparison.database.models;

import android.os.Parcel;
import android.os.Parcelable;

public class JobAndPackage implements Parcelable {
    public JobAndPackage(){

    }
    public int id;
    public String title;
    public String company;
    public String location;
    public String costOfLivingIndex;
    public String yearlySalary;
    public String yearlyBonus;
    public String benefits;
    public String stipend;
    public String rsua;
    public int isCurrentJob;

    protected JobAndPackage(Parcel in) {
        id = in.readInt();
        title = in.readString();
        company = in.readString();
        location = in.readString();
        costOfLivingIndex = in.readString();
        yearlySalary = in.readString();
        yearlyBonus = in.readString();
        benefits = in.readString();
        stipend = in.readString();
        rsua = in.readString();
//        byte tmpIsSelected = in.readByte();
        isCurrentJob = in.readInt();
    }

    public static final Creator<JobAndPackage> CREATOR = new Creator<JobAndPackage>() {
        @Override
        public JobAndPackage createFromParcel(Parcel in) {
            return new JobAndPackage(in);
        }

        @Override
        public JobAndPackage[] newArray(int size) {
            return new JobAndPackage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(company);
        parcel.writeString(location);
        parcel.writeString(costOfLivingIndex);
        parcel.writeString(yearlySalary);
        parcel.writeString(yearlyBonus);
        parcel.writeString(benefits);
        parcel.writeString(stipend);
        parcel.writeString(rsua);
        parcel.writeInt(isCurrentJob);
    }
}
