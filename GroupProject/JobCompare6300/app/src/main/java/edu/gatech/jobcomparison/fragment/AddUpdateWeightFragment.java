package edu.gatech.jobcomparison.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.proj.jobcomparison.databinding.FragmentAddUpdateWeightBinding;

import edu.gatech.jobcomparison.database.DBClient;
import edu.gatech.jobcomparison.database.dao.JobDao;
import edu.gatech.jobcomparison.database.models.Weight;

public class AddUpdateWeightFragment extends Fragment {

    private FragmentAddUpdateWeightBinding binding;
    private Weight savedJobWeight;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddUpdateWeightBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        JobDao dao = DBClient.getInstance(getContext()).getAppDatabase().jobDao();
        new GetWeightAsyncTask(dao).execute();
        binding.buttonAddUpdateWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Weight newWeight = getInputWeight();
                JobDao dao = DBClient.getInstance(getContext()).getAppDatabase().jobDao();
                new InsertWeightAsyncTask(dao).execute(newWeight);
            }
        });

    }

    private Weight getInputWeight() {
        String salaryWeight = binding.etYearlySalary.getText().toString().trim();
        String bonusWeight = binding.etYearlyBonus.getText().toString().trim();
        String benefitWeight = binding.etBenefits.getText().toString().trim();
        String stipendWeight = binding.etStipend.getText().toString().trim();
        String rsuaWeight = binding.etRsua.getText().toString().trim();
        Weight newWeight = new Weight();
        newWeight.setYearlySalaryWeight(salaryWeight);
        newWeight.setYearlyBonusWeight(bonusWeight);
        newWeight.setRetirementBenefitWeight(benefitWeight);
        newWeight.setRelocationStipendWeight(stipendWeight);
        newWeight.setRestrictedStockUnitAwardWeight(rsuaWeight);
        return newWeight;
    }

    private class InsertWeightAsyncTask extends AsyncTask<Weight, Void, Void> {

        private JobDao dao;

        public InsertWeightAsyncTask(JobDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final Weight... params) {
            if (savedJobWeight == null) {
                dao.insertWeight(params[0]);
            } else {
                params[0].setId(savedJobWeight.getId());
                dao.updateWeight(params[0]);
            }
            savedJobWeight = params[0];
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            binding.progressbar.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Weight Added Successfully", Toast.LENGTH_LONG).show();
            super.onPostExecute(result);
        }
    }

    private class GetWeightAsyncTask extends AsyncTask<Void, Void, Weight> {

        private JobDao dao;

        public GetWeightAsyncTask(JobDao dao) {
            this.dao = dao;
        }

        @Override
        protected Weight doInBackground(final Void... params) {

            return dao.getSingleWeight();
        }

        @Override
        protected void onPostExecute(Weight weight) {
            binding.progressbar.setVisibility(View.GONE);
            savedJobWeight = weight;
            if (weight != null) {
                binding.etRsua.setText(weight.getRestrictedStockUnitAwardWeight());
                binding.etStipend.setText(weight.getRelocationStipendWeight());
                binding.etYearlySalary.setText(weight.getYearlySalaryWeight());
                binding.etYearlyBonus.setText(weight.getYearlyBonusWeight());
                binding.etBenefits.setText(weight.getRetirementBenefitWeight());
                binding.buttonAddUpdateWeight.setText("Update Weight");
            }
            else
            {
                binding.buttonAddUpdateWeight.setText("Add Weight");
            }
            super.onPostExecute(weight);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}