package com.praise.Haneasy.Screens.Learning.Words.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.MyClickInterface;

import java.util.ArrayList;

public class WordClassAdapter extends RecyclerView.Adapter<WordClassAdapter.ViewHolder>{
    Context context;
    ArrayList<WordClass> classes;
    MyClickInterface clickInterface;

    public WordClassAdapter(Context context, ArrayList<WordClass> classes, MyClickInterface clickInterface) {
        this.context = context;
        this.classes = classes;
        this.clickInterface = clickInterface;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  WordClassAdapter.ViewHolder holder, int position) {
        holder.name.setText(classes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
