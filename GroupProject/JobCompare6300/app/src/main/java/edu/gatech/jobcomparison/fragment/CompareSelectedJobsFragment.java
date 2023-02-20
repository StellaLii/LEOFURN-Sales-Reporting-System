package edu.gatech.jobcomparison.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.proj.jobcomparison.databinding.FragmentCompareSelectedJobsBinding;

import java.util.List;

import edu.gatech.jobcomparison.adapter.SelectedJobsForComparisonAdapter;
import edu.gatech.jobcomparison.database.models.JobAndPackage;

public class CompareSelectedJobsFragment extends Fragment {

    private FragmentCompareSelectedJobsBinding binding;
    private List<JobAndPackage> selectedJobsForComparison;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCompareSelectedJobsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setJobsAdapter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            selectedJobsForComparison = bundle.getParcelableArrayList("jobs");
        }
    }

    private void setJobsAdapter() {
        if (getContext() != null) {
            SelectedJobsForComparisonAdapter adapter = new SelectedJobsForComparisonAdapter(getContext(), selectedJobsForComparison);
            LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.rvJobs.setLayoutManager(manager);
            binding.rvJobs.setAdapter(adapter);

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}