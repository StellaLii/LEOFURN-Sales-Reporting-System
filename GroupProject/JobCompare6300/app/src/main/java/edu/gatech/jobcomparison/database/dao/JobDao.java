package edu.gatech.jobcomparison.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import edu.gatech.jobcomparison.database.models.Job;
import edu.gatech.jobcomparison.database.models.JobAndPackage;
import edu.gatech.jobcomparison.database.models.Package;
import edu.gatech.jobcomparison.database.models.Weight;

import java.util.List;

@Dao
public interface JobDao {
    @Query("SELECT * FROM job ORDER BY id desc")
    List<Job> getAllJobs();

    @Query("SELECT * FROM job WHERE id = :jobId")
    Job getJobById(int jobId);

    @Transaction
    @Query("SELECT * FROM Job inner join Package on job.id= package.jobId")
    List<JobAndPackage> getAllJobsAndPackages();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertJob(Job job);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPackage(Package pack);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeight(Weight weight);

    @Update
    void updateWeight(Weight weight);

    @Query("SELECT * FROM weight LIMIT 1")
    Weight getSingleWeight();

    @Query("SELECT 1 FROM job WHERE isCurrentJob = 1")
    Boolean dbHasCurrentJob();

}
