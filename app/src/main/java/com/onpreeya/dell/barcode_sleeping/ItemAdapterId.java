package com.onpreeya.dell.barcode_sleeping;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onpreeya.dell.barcode_sleeping.dao.StudentDao;

import java.util.List;

/**
 * Created by DELL on 21/3/2561.
 */

public class ItemAdapterId extends RecyclerView.Adapter<ItemAdapterId.ItemViewHolder> {
    Context context;
    //List<StudentDao> studentList;
    List<String> studentList;

    public ItemAdapterId(Context context, List<String> studentList){
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public ItemAdapterId.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext())
                .inflate(R.layout.item_cardid_layout, parent, false);

        ItemAdapterId.ItemViewHolder itemViewHolder = new ItemAdapterId.ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemAdapterId.ItemViewHolder holder, int position) {
        holder.tvId.setText(studentList.get(position));
    }

    @Override
    public int getItemCount() {
        if(studentList != null){
            return studentList.size();
        }
        return 0;

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView tvId;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvId = (TextView)itemView.findViewById(R.id.tvId);
        }
    }


}
