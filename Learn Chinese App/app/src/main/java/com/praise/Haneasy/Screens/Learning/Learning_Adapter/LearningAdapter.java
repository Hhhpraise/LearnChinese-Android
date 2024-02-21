package com.praise.Haneasy.Screens.Learning.Learning_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.MyClickInterface;

import java.util.ArrayList;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.ViewHolder> {
    Context context;
    ArrayList<LearnEntities> learnEntities;
    MyClickInterface clickInterface;

    public LearningAdapter(Context context, ArrayList<LearnEntities> exercises, MyClickInterface clickInterface) {
        this.context = context;
        this.learnEntities = exercises;
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearningAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(learnEntities.get(position).getImg()).placeholder(R.drawable.black_bg).into(holder.imgview);
        holder.name.setText(learnEntities.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return learnEntities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgview;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgview = (ImageView) itemView.findViewById(R.id.category_img);
            name = (TextView) itemView.findViewById(R.id.category_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
