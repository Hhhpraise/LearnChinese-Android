package com.praise.Haneasy.Screens.Forum.PostAdapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.praise.Haneasy.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    Context context;
    ArrayList<CommentEntity> commentEntities;

    public CommentAdapter(Context context, ArrayList<CommentEntity> commentEntities) {
        this.context = context;
        this.commentEntities = commentEntities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_card, parent, false);
        CommentAdapter.ViewHolder viewHolder = new CommentAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText("from: "+commentEntities.get(position).getUserName());
        holder.date.setText(": "+commentEntities.get(position).getCommentDate());
        holder.comment.setText(Html.fromHtml(commentEntities.get(position).getCommentTxt()));
    }

    @Override
    public int getItemCount() {
        return commentEntities.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name , date, comment;
         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             name = itemView.findViewById(R.id.commenetUSER);
             date = itemView.findViewById(R.id.commentTime);
             comment = itemView.findViewById(R.id.commentTXT);
         }
     }
}
