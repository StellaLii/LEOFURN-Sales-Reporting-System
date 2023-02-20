package edu.gatech.jobcomparison.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.proj.jobcomparison.R;
import com.proj.jobcomparison.databinding.FragmentAddJobBinding;

import edu.gatech.jobcomparison.database.DBClient;
import edu.gatech.jobcomparison.database.dao.JobDao;
import edu.gatech.jobcomparison.database.models.Job;
import edu.gatech.jobcomparison.database.models.Weight;

public class AddJobFragment extends Fragment {

    private FragmentAddJobBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddJobBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCompensationPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JobDao dao = DBClient.getInstance(getContext()).getAppDatabase().jobDao();
                new ValidateExistingCurrentJob(dao).execute();

                Job newJob = getJob();
                if (newJob.getTitle().isEmpty())
                    Toast.makeText(getContext(), "Pease enter job title", Toast.LENGTH_LONG).show();
                else if (newJob.getCompany().isEmpty())
                    Toast.makeText(getContext(), "Pease enter company", Toast.LENGTH_LONG).show();
                else if (newJob.getLocation().isEmpty())
                    Toast.makeText(getContext(), "Pease enter location", Toast.LENGTH_LONG).show();
                else {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("job", newJob);
                    NavHostFragment.findNavController(AddJobFragment.this)
                            .navigate(R.id.action_EditManageJobFragment_to_packageFragment, bundle);
                }
            }
        });

    }

    private Job getJob() {
        String jobTitle = binding.etJobTitle.getText().toString().trim();
        String company = binding.etCompany.getText().toString().trim();
        String location = binding.etLocation.getText().toString().trim();
        Job newJob = new Job();
        newJob.setTitle(jobTitle);
        newJob.setCompany(company);
        newJob.setLocation(location);
        if (binding.swchCurrentJob.isChecked())
            newJob.setIsCurrentJob(1);
        else
            newJob.setIsCurrentJob(0);
        return newJob;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private class ValidateExistingCurrentJob extends AsyncTask<Void, Void, Boolean> {

        private JobDao dao;

        public ValidateExistingCurrentJob(JobDao dao) {
            this.dao = dao;
        }

        @Override
        protected Boolean doInBackground(final Void... params ) {
            return dao.dbHasCurrentJob();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            Toast.makeText(getContext(), "Mistake: A Job already marked Current", Toast.LENGTH_LONG).show();
            super.onPostExecute(result);
        }
    }
}