package com.praise.Haneasy.Screens.Culture.Culture_Adapter;

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

public class CultureAdapter extends RecyclerView.Adapter<CultureAdapter.ViewHolder>{
    Context context;
    ArrayList<CultureModal> cultureModals;
    MyClickInterface clickInterface;

    public CultureAdapter(Context context, ArrayList<CultureModal> cultureModals, MyClickInterface clickInterface) {
        this.context = context;
        this.cultureModals = cultureModals;
        this.clickInterface = clickInterface;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.culture_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull  CultureAdapter.ViewHolder holder, int position) {
        //holder.img.setImageResource(cultureModals.get(position).getImg());
        holder.name.setText(cultureModals.get(position).getName());
        Glide.with(context).load(cultureModals.get(position).getImg()).placeholder(R.drawable.black_bg).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return cultureModals.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView img;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.culture_name);
            img = itemView.findViewById(R.id.culture_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
