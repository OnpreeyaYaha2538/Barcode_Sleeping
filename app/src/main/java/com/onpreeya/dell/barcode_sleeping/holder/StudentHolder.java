package com.onpreeya.dell.barcode_sleeping.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onpreeya.dell.barcode_sleeping.R;

import java.security.PublicKey;


public class StudentHolder extends RecyclerView.ViewHolder{

    public TextView tvCode;
    public TextView tvFixname;
    public TextView tvName;
    public TextView tv_Surname;
    public LinearLayout studenCardView;

    public StudentHolder(View itemView) {
        super(itemView);
        tvCode = itemView.findViewById(R.id.tv_code);
        tvFixname = itemView.findViewById(R.id.tv_fixname);
        tvName = itemView.findViewById(R.id.tv_name);
        tv_Surname = itemView.findViewById(R.id.tv_surname);
        studenCardView = itemView.findViewById(R.id.studen_card_view);

    }

}
