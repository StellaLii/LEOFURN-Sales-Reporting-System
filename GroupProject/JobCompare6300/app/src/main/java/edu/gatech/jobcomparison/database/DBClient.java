package edu.gatech.jobcomparison.database;

import static edu.gatech.jobcomparison.Constants.DB_NAME;

import android.content.Context;

import androidx.room.Room;

public class DBClient {
    private Context mCtx;
    private static DBClient mInstance;

    private JobsDb appDatabase;

    private DBClient(Context mCtx) {
        this.mCtx = mCtx;

        appDatabase = Room.databaseBuilder(mCtx, JobsDb.class, DB_NAME)
                .build();
    }

    public static synchronized DBClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DBClient(mCtx);
        }
        return mInstance;
    }

    public JobsDb getAppDatabase() {
        return appDatabase;
    }
}
