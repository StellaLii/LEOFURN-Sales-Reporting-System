package edu.gatech.jobcomparison.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.proj.jobcomparison.R;
import com.proj.jobcomparison.databinding.FragmentAvailableJobsBinding;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.jobcomparison.adapter.AvailableJobsAdapter;
import edu.gatech.jobcomparison.database.DBClient;
import edu.gatech.jobcomparison.database.dao.JobDao;
import edu.gatech.jobcomparison.database.models.JobAndPackage;

public class AvailableJobsFragment extends Fragment {

    private FragmentAvailableJobsBinding binding;
    private List<JobAndPackage> availableJobs;
    private List<JobAndPackage> selectedJobsForComparison;
    private Map<Integer, Integer> selectedPositions;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAvailableJobsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectedPositions = new LinkedHashMap<Integer, Integer>();
        getAllJobs();
        binding.buttonCompareJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedJobs();
            }
        });
    }

    private void getSelectedJobs() {
        if (!selectedPositions.isEmpty()) {
            selectedJobsForComparison = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : selectedPositions.entrySet()) {
                Integer key = entry.getKey();
                selectedJobsForComparison.add(availableJobs.get(key));
            }
            launchComparisonDetailScreen();
        } else {
            Toast.makeText(getContext(), "Pease select jobs", Toast.LENGTH_LONG).show();
        }

    }

    private void launchComparisonDetailScreen() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("jobs", (ArrayList<? extends Parcelable>) selectedJobsForComparison);
        NavHostFragment.findNavController(AvailableJobsFragment.this)
                .navigate(R.id.action_availableJobsFragment_to_comparisonFragment, bundle);
    }

    private void getAllJobs() {
        binding.progressbar.setVisibility(View.VISIBLE);
        JobDao dao = DBClient.getInstance(getContext()).getAppDatabase().jobDao();
        new GetJobsAsyncTask(dao).execute();
    }

    private void setJobsAdapter() {
        if (getContext() != null) {
            AvailableJobsAdapter adapter = new AvailableJobsAdapter(getContext(), availableJobs, new AvailableJobsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position, boolean isSelected) {
                    if (!isSelected)
                        selectedPositions.remove(position);
                    else
                        selectedPositions.put(position, position);
                }
            });
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            binding.rvJobs.setLayoutManager(manager);
            binding.rvJobs.setAdapter(adapter);

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class GetJobsAsyncTask extends AsyncTask<Void, Void, List<JobAndPackage>> {

        private JobDao dao;

        public GetJobsAsyncTask(JobDao dao) {
            this.dao = dao;
        }

        @Override
        protected List<JobAndPackage> doInBackground(final Void... params) {

            List<JobAndPackage> jobs = dao.getAllJobsAndPackages();
            return jobs;
        }

        @Override
        protected void onPostExecute(List<JobAndPackage> jobs) {
            binding.progressbar.setVisibility(View.GONE);
            if (!jobs.isEmpty()) {
                availableJobs = jobs;
                setJobsAdapter();
            }

            super.onPostExecute(jobs);
        }
    }

}