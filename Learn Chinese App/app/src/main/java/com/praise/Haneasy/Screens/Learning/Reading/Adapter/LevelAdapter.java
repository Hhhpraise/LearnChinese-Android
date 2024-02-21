package com.praise.Haneasy.Screens.Learning.Reading.Adapter;

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

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ViewHolder> {

    Context context;
    ArrayList<LevelModal> levelModalArrayList;
    MyClickInterface clickInterface;


    public LevelAdapter(Context context, ArrayList<LevelModal> levelModalArrayList, MyClickInterface clickInterface) {
        this.context = context;
        this.levelModalArrayList = levelModalArrayList;
        this.clickInterface = clickInterface;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  LevelAdapter.ViewHolder holder, int position) {
        holder.name.setText(levelModalArrayList.get(position).getName());
        Glide.with(context).load(levelModalArrayList.get(position).getImage()).placeholder(R.drawable.four__big).into(holder.imgview);
    }

    @Override
    public int getItemCount() {
        return levelModalArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgview;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgview = (ImageView) itemView.findViewById(R.id.level_img);
            name = (TextView) itemView.findViewById(R.id.level_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
