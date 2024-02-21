package com.praise.Haneasy.Screens.Learning.Videos.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.MyClickInterface;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    Context context;
    ArrayList<VideoModal> courseModals;
    MyClickInterface clickInterface;

    public VideoAdapter(Context context, ArrayList<VideoModal> courseModals, MyClickInterface clickInterface) {
        this.context = context;
        this.courseModals = courseModals;
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        holder.name.setText(courseModals.get(position).getName());
        holder.id.setText(courseModals.get(position).getId() + "");
        holder.duration.setText(courseModals.get(position).getMinutes() + ".mins");
    }

    @Override
    public int getItemCount() {
        return courseModals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, id, duration;
        ImageView btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.course_name);
            id = (TextView) itemView.findViewById(R.id.course_id);
            duration = (TextView) itemView.findViewById(R.id.course_duration);
            btn = (ImageView) itemView.findViewById(R.id.play);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onItemClick(getAdapterPosition());
                }
            });
        }

    }
}
