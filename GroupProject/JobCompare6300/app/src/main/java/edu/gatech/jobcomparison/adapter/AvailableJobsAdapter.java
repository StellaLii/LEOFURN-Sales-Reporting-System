package edu.gatech.jobcomparison.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proj.jobcomparison.R;

import edu.gatech.jobcomparison.database.models.CalculationUtil;
import edu.gatech.jobcomparison.database.models.CompensationPackage;
import edu.gatech.jobcomparison.database.models.JobAndPackage;
import edu.gatech.jobcomparison.database.models.WeightDTO;

import java.util.List;

public class AvailableJobsAdapter extends RecyclerView.Adapter<AvailableJobsAdapter.ViewHolder> {
    private Context context;
    private List<JobAndPackage> dataSet;
    private final OnItemClickListener listener;

    public AvailableJobsAdapter(Context context,
                                List<JobAndPackage> dataSet,
                                OnItemClickListener listener) {
        this.context = context;
        this.dataSet = dataSet;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.job_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JobAndPackage job = dataSet.get(position);
        holder.cbJob.setText(job.title);

        WeightDTO weightDTO = new WeightDTO(job.yearlySalary, job.yearlyBonus, job.stipend, job.rsua, job.benefits);
        CompensationPackage compensationPackage = new CompensationPackage(job.yearlySalary, job.yearlyBonus, job.stipend,
                job.benefits, job.rsua, job.costOfLivingIndex);

        long jobScore =  CalculationUtil.calculateRankScore(weightDTO, compensationPackage);
        holder.jobScore.setText(Long.toString(jobScore));


        if (job.isCurrentJob == 1)
            holder.rbCurrentJob.setChecked(true);
        else
            holder.rbCurrentJob.setChecked(false);
        holder.cbJob.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean flag) {
                listener.onItemClick(holder.getAdapterPosition(), flag);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbJob;
        TextView jobScore;
        RadioButton rbCurrentJob;

        ViewHolder(View itemView) {
            super(itemView);
            cbJob = itemView.findViewById(R.id.cb_job);
            rbCurrentJob = itemView.findViewById(R.id.rb_current_job);
            jobScore = itemView.findViewById(R.id.main_job_score);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position, boolean isSelected);
    }

}
