package com.praise.Haneasy.Screens.Learning.Pinyin.Adapter;

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

public class PinyinClassAdapter extends RecyclerView.Adapter<PinyinClassAdapter.ViewHolder> {
    Context context;
    ArrayList<PinyinClassModal> modals;
    MyClickInterface clickInterface;

    public PinyinClassAdapter(Context context, ArrayList<PinyinClassModal> modals, MyClickInterface clickInterface) {
        this.context = context;
        this.modals = modals;
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  PinyinClassAdapter.ViewHolder holder, int position) {
        holder.name.setText(modals.get(position).getName());
        Glide.with(context).load(modals.get(position).getImg_url()).placeholder(R.drawable.black_gradient).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return modals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {

        TextView name;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //imgview = (ImageView) itemView.findViewById(R.id.level_img);
            name = (TextView) itemView.findViewById(R.id.category_name);
            img = (ImageView) itemView.findViewById(R.id.category_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
