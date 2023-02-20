package edu.gatech.jobcomparison.database;

import static edu.gatech.jobcomparison.Constants.DB_VERSION;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import edu.gatech.jobcomparison.database.dao.JobDao;
import edu.gatech.jobcomparison.database.models.Job;
import edu.gatech.jobcomparison.database.models.Package;
import edu.gatech.jobcomparison.database.models.Weight;

@Database(entities = {Job.class, Package.class, Weight.class}, version = DB_VERSION)
abstract public class JobsDb extends RoomDatabase {
    public abstract JobDao jobDao();

}

