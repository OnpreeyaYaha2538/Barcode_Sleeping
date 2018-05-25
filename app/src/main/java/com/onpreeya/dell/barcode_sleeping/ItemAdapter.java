package com.onpreeya.dell.barcode_sleeping;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onpreeya.dell.barcode_sleeping.manager.http.ActivityListDao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DELL on 16/3/2561.
 */
import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private Context context;
    private List<ActivityListDao> itemList;
    private String typeSelect;
    private String yearSelect;

    public ItemAdapter(Context context, List<ActivityListDao> itemList ,String typeSelect,String yearSelect){
        this.context = context;
        this.itemList = itemList;
        this.typeSelect = typeSelect;
        this.yearSelect = yearSelect;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext())
                .inflate(R.layout.item_cardview_layout, parent, false);

        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        //Item item = itemList.get(position);
        final ActivityListDao item = itemList.get(position);
//        Picasso.get()
//                .load(item.img)
//                .placeholder(R.drawable.view)
//                .error(android.R.drawable.stat_notify_error)
//                .into(holder.ivImg);

        holder.tvText.setText(item.getAct_name());
        holder.linCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", item.getAct_id());
                intent.putExtra("typeSelect",typeSelect);
                intent.putExtra("actName",item.getAct_name());
                intent.putExtra("yearSelect",yearSelect);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(itemList != null){
            return itemList.size();
        }
        return 0;
    }

    //ViewHolder class
    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        public CardView cvItem;
        public TextView tvText;
        public LinearLayout linCard;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cvItem = itemView.findViewById(R.id.cvItem);
            tvText = itemView.findViewById(R.id.tvText);
            linCard = itemView.findViewById(R.id.lin_card);
        }
    }
}
