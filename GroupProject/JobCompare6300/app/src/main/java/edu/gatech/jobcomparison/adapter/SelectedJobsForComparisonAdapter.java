package edu.gatech.jobcomparison.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proj.jobcomparison.R;

import edu.gatech.jobcomparison.database.models.CalculationUtil;
import edu.gatech.jobcomparison.database.models.JobAndPackage;
import edu.gatech.jobcomparison.database.models.*;

import java.util.List;

public class SelectedJobsForComparisonAdapter extends RecyclerView.Adapter<SelectedJobsForComparisonAdapter.ViewHolder> {
    private Context context;
    private List<JobAndPackage> dataSet;

    public SelectedJobsForComparisonAdapter(Context context,
                                            List<JobAndPackage> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.job_comparison_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JobAndPackage job = dataSet.get(position);

            // Calculate Job Score below.
        WeightDTO weightDTO = new WeightDTO(job.yearlySalary, job.yearlyBonus, job.stipend, job.rsua, job.benefits);
        CompensationPackage compensationPackage = new CompensationPackage(job.yearlySalary, job.yearlyBonus, job.stipend,
                job.benefits, job.rsua, job.costOfLivingIndex);

       long jobScore =  CalculationUtil.calculateRankScore(weightDTO, compensationPackage);

        holder.tvJobTitle.setText(job.title);
        holder.tvCompany.setText(job.company);
        holder.tvLocation.setText(job.location);
        holder.tvYearlySalary.setText(job.yearlySalary);
        holder.tvYearlyBonus.setText(job.yearlyBonus);
        holder.tvStipend.setText(job.stipend);
        holder.tvBenefits.setText(job.benefits);
        holder.tvLivingIndex.setText(job.costOfLivingIndex);
        if (job.isCurrentJob == 0)
            holder.tvCurrentJob.setText("No");
        else
            holder.tvCurrentJob.setText("Yes");

        holder.tvJobScore.setText(Long.toString(jobScore));

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJobTitle, tvCompany, tvLocation, tvYearlySalary, tvYearlyBonus, tvStipend, tvBenefits, tvLivingIndex, tvCurrentJob, tvJobScore;

        ViewHolder(View itemView) {
            super(itemView);
            tvJobTitle = itemView.findViewById(R.id.tv_job_title);
            tvCompany = itemView.findViewById(R.id.tv_company);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvYearlySalary = itemView.findViewById(R.id.tv_yearly_salary);
            tvYearlyBonus = itemView.findViewById(R.id.tv_yearly_bonus);
            tvStipend = itemView.findViewById(R.id.tv_stipend);
            tvBenefits = itemView.findViewById(R.id.tv_benefits);
            tvLivingIndex = itemView.findViewById(R.id.tv_living_index);
            tvCurrentJob = itemView.findViewById(R.id.tv_current_job);
            tvJobScore = itemView.findViewById(R.id.tv_job_score);
        }

    }


}
