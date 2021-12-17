package com.paysky.readtextfile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.paysky.readtextfile.models.PairEmployees;

import java.util.List;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.MyViewHolder> {
    List<PairEmployees> pairEmployeesList;

    public EmployeesAdapter(List<PairEmployees> transactionModels) {

        this.pairEmployeesList = transactionModels;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_pair_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final PairEmployees pairEmployees = pairEmployeesList.get(position);
        holder.emp1.setText(pairEmployees.getEmpId1());
        holder.emp2.setText(pairEmployees.getEmpId2());
        holder.projId.setText(pairEmployees.getProjId());
        holder.workDays.setText(pairEmployees.getWorkDuration());

    }


    @Override
    public int getItemCount() {
        if (pairEmployeesList != null)
            return pairEmployeesList.size();
        else return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView emp1;
        TextView emp2;
        TextView projId;
        TextView workDays;

        public MyViewHolder(View view) {
            super(view);
            emp1 = view.findViewById(R.id.tvEmpId1);
            emp2 = view.findViewById(R.id.tvEmpId2);
            projId = view.findViewById(R.id.tvProjId);
            workDays = view.findViewById(R.id.tvWorkDays);

        }
    }

}
