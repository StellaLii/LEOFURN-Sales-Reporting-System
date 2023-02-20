package edu.gatech.jobcomparison.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.proj.jobcomparison.R;
import com.proj.jobcomparison.databinding.FragmentAddPackageBinding;

import edu.gatech.jobcomparison.database.DBClient;
import edu.gatech.jobcomparison.database.dao.JobDao;
import edu.gatech.jobcomparison.database.models.Job;
import edu.gatech.jobcomparison.database.models.Package;

public class AddPackageFragment extends Fragment {

    private FragmentAddPackageBinding binding;
    private Job job;
    private Package jobPackage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            job = bundle.getParcelable("job");
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddPackageBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSaveJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatePackage();
            }
        });
    }

    private void validatePackage() {
        jobPackage = getPackage();
        if (jobPackage.getYearlySalary().isEmpty())
            Toast.makeText(getContext(), "Pease enter yearly salary", Toast.LENGTH_LONG).show();
        else if (jobPackage.getYearlyBonus().isEmpty())
            Toast.makeText(getContext(), "Pease enter yearly bonus", Toast.LENGTH_LONG).show();
        else if (jobPackage.getBenefits().isEmpty())
            Toast.makeText(getContext(), "Pease enter benefits", Toast.LENGTH_LONG).show();
        else if (jobPackage.getStipend().isEmpty())
            Toast.makeText(getContext(), "Pease enter stipend", Toast.LENGTH_LONG).show();
        else if (jobPackage.getRsua().isEmpty())
            Toast.makeText(getContext(), "Pease enter RSUA", Toast.LENGTH_LONG).show();
        else if (jobPackage.getCostOflivingIndex().isEmpty())
            Toast.makeText(getContext(), "Pease enter cost of living index", Toast.LENGTH_LONG).show();
        else {
            binding.progressbar.setVisibility(View.VISIBLE);
            saveJob();
        }
    }

    private void savePackage() {
        JobDao dao = DBClient.getInstance(getContext()).getAppDatabase().jobDao();
        new InsertPackageAsyncTask(dao).execute(jobPackage);
    }

    private void saveJob() {
        JobDao dao = DBClient.getInstance(getContext()).getAppDatabase().jobDao();
        new InsertJobAsyncTask(dao).execute(job);
    }

    private Package getPackage() {
        String bonus = binding.etYearlyBonus.getText().toString().trim();
        String salary = binding.etYearlySalary.getText().toString().trim();
        String benefits = binding.etBenefits.getText().toString().trim();
        String stipend = binding.etStipend.getText().toString().trim();
        String rsua = binding.etRsua.getText().toString().trim();
        String costOfLivingIndex = binding.etLivingIndex.getText().toString().trim();

        Package jobPackage = new Package();
        jobPackage.setJobId(job.getId());
        jobPackage.setBenefits(benefits);
        jobPackage.setRsua(rsua);
        jobPackage.setStipend(stipend);
        jobPackage.setYearlyBonus(bonus);
        jobPackage.setYearlySalary(salary);
        jobPackage.setCostOflivingIndex(costOfLivingIndex);
        return jobPackage;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class InsertPackageAsyncTask extends AsyncTask<Package, Void, Long> {

        private JobDao dao;

        public InsertPackageAsyncTask(JobDao dao) {
            this.dao = dao;
        }

        @Override
        protected Long doInBackground(final Package... params) {

            long id = dao.insertPackage(params[0]);
            return id;
        }

        @Override
        protected void onPostExecute(Long id) {
            binding.progressbar.setVisibility(View.GONE);
            if (id != 0 || id != -1) {
                Toast.makeText(getContext(), "Job Added Successfully", Toast.LENGTH_LONG).show();
                NavHostFragment.findNavController(AddPackageFragment.this)
                        .navigate(R.id.action_packageFragment_to_MenuFragment);
            } else
                Toast.makeText(getContext(), "Something went wrong while saving job", Toast.LENGTH_LONG).show();
            super.onPostExecute(id);
        }
    }

    private class InsertJobAsyncTask extends AsyncTask<Job, Void, Long> {

        private JobDao dao;

        public InsertJobAsyncTask(JobDao dao) {
            this.dao = dao;
        }

        @Override
        protected Long doInBackground(final Job... params) {

            long id = dao.insertJob(params[0]);
            return id;
        }

        @Override
        protected void onPostExecute(Long id) {
            if (id != 0 || id != -1) {
                job.setId(Math.toIntExact(id));
                jobPackage.setJobId(job.getId());
                savePackage();
            } else {
                Toast.makeText(getContext(), "Something went wrong while saving job", Toast.LENGTH_LONG).show();
                binding.progressbar.setVisibility(View.GONE);
            }

            super.onPostExecute(id);
        }
    }

}