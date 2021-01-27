package com.example.attendancesystem.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancesystem.R;

import java.util.List;

public class perstudentAdapter extends RecyclerView.Adapter<perstudentAdapter.ViewHolder>{
    private List<student> listdata;

    // RecyclerView recyclerView;
    public perstudentAdapter(List<student> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.perstudent, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final student myListData = listdata.get(position);
        holder.t1.setText(listdata.get(position).getSid());
        holder.t2.setText(listdata.get(position).getSname());
        holder.t3.setText(listdata.get(position).getAtt());
        holder.t4.setText(listdata.get(position).getRate());
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView t1,t2,t3,t4;
        public ViewHolder(View itemView) {
            super(itemView);
            this.t1 = (TextView) itemView.findViewById(R.id.sid);
            this.t2 = (TextView) itemView.findViewById(R.id.sname);
            this.t3 = (TextView) itemView.findViewById(R.id.att);
            this.t4 = (TextView) itemView.findViewById(R.id.rate);
        }
    }
}