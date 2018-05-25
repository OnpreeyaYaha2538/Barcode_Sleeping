package com.onpreeya.dell.barcode_sleeping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onpreeya.dell.barcode_sleeping.R;
import com.onpreeya.dell.barcode_sleeping.dao.DetailChart4YearDao;
import com.onpreeya.dell.barcode_sleeping.dao.DetailChartDao;
import com.onpreeya.dell.barcode_sleeping.holder.StudentHolder;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentHolder> {

    List<DetailChartDao> dao;
    Context context;

    public StudentAdapter(List<DetailChartDao> dao) {
        this.dao = dao;
    }

    @Override
    public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext())
                .inflate(R.layout.item_cardview_student_layout, parent, false);

       StudentHolder studentHolder = new StudentHolder(view);
        return studentHolder;
    }

    @Override
    public void onBindViewHolder(StudentHolder holder, int position) {

        holder.tvCode.setText(dao.get(position).getStdCode());
        holder.tvFixname.setText(dao.get(position).getPrefixName());
        holder.tvName.setText(dao.get(position).getStdName());
        holder.tv_Surname.setText(dao.get(position).getStdSurname());


    }

    @Override
    public int getItemCount() {
        if(dao != null)
        return dao.size();

        return 0;
    }
}
