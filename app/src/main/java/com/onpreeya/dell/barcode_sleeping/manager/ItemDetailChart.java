package com.onpreeya.dell.barcode_sleeping.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.CardView;

import com.onpreeya.dell.barcode_sleeping.ItemAdapter;
import com.onpreeya.dell.barcode_sleeping.R;
import com.onpreeya.dell.barcode_sleeping.dao.DetailChartDao;
import com.onpreeya.dell.barcode_sleeping.manager.http.ActivityListDao;

import java.util.List;

public class ItemDetailChart extends RecyclerView.Adapter<ItemDetailChart.ItemViewHolder> {
    private Context context;
    private List<DetailChartDao> itemList;


    public ItemDetailChart(){
        this.context = context;
        this.itemList = itemList;
    }

    public ItemDetailChart.ItemViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.item_carddetail_layout,parent,false);

        ItemDetailChart.ItemViewHolder itemViewHolder = new ItemDetailChart.ItemViewHolder(view);
        return  itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemDetailChart.ItemViewHolder holder, int position) {
        DetailChartDao item = itemList.get(position);

        holder.txtView1.setText(item.getStdCode());
        holder.txtView2.setText(item.getPrefixName());
        holder.txtView3.setText(item.getStdName());
        holder.txtView4.setText(item.getStdSurname());
    }

    @Override
    public int getItemCount() {
        if(itemList != null){
            return itemList.size();
        }
        return 0;
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public CardView cvItemDetail;
        public TextView txtView1;
        public TextView txtView2;
        public TextView txtView3;
        public TextView txtView4;

        public ItemViewHolder(View itemView){
            super(itemView);
            cvItemDetail = (CardView) itemView.findViewById(R.id.rvItemDetail);
            txtView1 = (TextView) itemView.findViewById(R.id.tvText1);
            txtView2 = (TextView) itemView.findViewById(R.id.tvText2);
            txtView3 = (TextView) itemView.findViewById(R.id.tvText3);
            txtView4 = (TextView) itemView.findViewById(R.id.tvText4);

        }
    }
}

