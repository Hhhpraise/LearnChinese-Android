package com.praise.Haneasy.Screens.Culture.Types.Adapter;

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

import java.util.ArrayList;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder>{
    Context context;
    ArrayList<CultureTypes> cultureTypes;
    TypeClickInterface clickInterface;

    public TypeAdapter(Context context, ArrayList<CultureTypes> cultureTypes, TypeClickInterface clickInterface) {
        this.context = context;
        this.cultureTypes = cultureTypes;
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
    public void onBindViewHolder(@NonNull  TypeAdapter.ViewHolder holder, int position) {
        holder.name.setText(cultureTypes.get(position).getName());
        Glide.with(context).load(cultureTypes.get(position).getImg()).placeholder(R.drawable.black_bg).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return cultureTypes.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.category_name);
            img = itemView.findViewById(R.id.category_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
