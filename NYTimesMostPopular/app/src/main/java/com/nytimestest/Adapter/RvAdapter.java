package com.nytimestest.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nytimestest.Data.Rvdata;
import com.nytimestest.DetailsActivity;
import com.nytimestest.R;

import java.util.ArrayList;

/**
 * Created by Jency Mathew on 27-09-2018.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder>{
    Context context;
    ArrayList<Rvdata> rvdatas;
    Dialog dialog;
    public RvAdapter(Context context,ArrayList<Rvdata> rvdatas){
        this.context = context;
        this.rvdatas = rvdatas;
    }
    View view;
    @Override
    public RvAdapter.RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
        RvViewHolder rvViewHolder = new RvViewHolder(view);
        return rvViewHolder;
    }
    @Override
    public void onBindViewHolder(RvAdapter.RvViewHolder holder, int position) {
        final Rvdata  rvdata = rvdatas.get(position);
       holder.title.setText(rvdata.getTitle());
        holder.byline.setText(rvdata.getByline());
        holder.tv_date.setText(rvdata.getPublished_date());

       final String  details = rvdatas.get(position).getSource();

        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("details",details);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return rvdatas.size();
    }
    public class RvViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView byline;
        TextView tv_date;
        ImageView arrow;

        LinearLayout llItem;
        public RvViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            byline = (TextView)itemView.findViewById(R.id.byline);
            tv_date = (TextView)itemView.findViewById(R.id.tv_date);
            arrow= (ImageView)itemView.findViewById(R.id.arrow);
        }
    }
}