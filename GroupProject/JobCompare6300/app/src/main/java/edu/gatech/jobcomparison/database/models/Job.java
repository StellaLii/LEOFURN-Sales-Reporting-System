package edu.gatech.jobcomparison.database.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Job implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "company")
    private String company;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "isCurrentJob")
    private int isCurrentJob;

    public Job() {

    }

    protected Job(Parcel in) {
        id = in.readInt();
        title = in.readString();
        company = in.readString();
        location = in.readString();
        isCurrentJob = in.readInt();
    }

    public static final Creator<Job> CREATOR = new Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getIsCurrentJob() {
        return isCurrentJob;
    }

    public void setIsCurrentJob(int isCurrentJob) {
        this.isCurrentJob = isCurrentJob;
    }

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
        parcel.writeInt(isCurrentJob);
    }
}
