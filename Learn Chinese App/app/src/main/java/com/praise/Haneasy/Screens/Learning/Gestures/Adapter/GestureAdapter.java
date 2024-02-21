package com.praise.Haneasy.Screens.Learning.Gestures.Adapter;

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

public class GestureAdapter extends RecyclerView.Adapter<GestureAdapter.ViewHolder> {

    Context context;
    ArrayList<Gesture> gestures;

    public GestureAdapter(Context context, ArrayList<Gesture> gestures) {
        this.context = context;
        this.gestures = gestures;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gesture_holder, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GestureAdapter.ViewHolder holder, int position) {
        holder.gesture.setText(gestures.get(position).getGesture());
        holder.info.setText(gestures.get(position).getInfo());
        Glide.with(context).load(gestures.get(position).getImgUrl()).placeholder(R.drawable.black_bg).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return gestures.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        TextView gesture,info;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gesture =itemView.findViewById(R.id.gesture);
            info =itemView.findViewById(R.id.gesture_info);
            img = itemView.findViewById(R.id.gesture_img);

        }
    }
}
