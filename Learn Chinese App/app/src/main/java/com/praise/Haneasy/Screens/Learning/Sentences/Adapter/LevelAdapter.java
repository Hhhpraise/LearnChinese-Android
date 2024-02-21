package com.praise.Haneasy.Screens.Learning.Sentences.Adapter;

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
    ArrayList<LevelModal> modals;
    MyClickInterface clickInterface;
    String imgUrl = "https://images.pexels.com/photos/4065623/pexels-photo-4065623.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1";

    public LevelAdapter(Context context, ArrayList<LevelModal> modals, MyClickInterface clickInterface) {
        this.context = context;
        this.modals = modals;
        this.clickInterface = clickInterface;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_card, parent, false);
       ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        holder.name.setText(modals.get(position).getName());
        Glide.with(context).load(imgUrl).placeholder(R.drawable.four__big).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return modals.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //imgview = (ImageView) itemView.findViewById(R.id.level_img);
            name = (TextView) itemView.findViewById(R.id.level_name);
            img = (ImageView) itemView.findViewById(R.id.level_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
