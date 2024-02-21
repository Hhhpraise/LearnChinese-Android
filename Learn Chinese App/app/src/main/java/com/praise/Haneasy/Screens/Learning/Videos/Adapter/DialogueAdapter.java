package com.praise.Haneasy.Screens.Learning.Videos.Adapter;

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

public class DialogueAdapter extends RecyclerView.Adapter<DialogueAdapter.ViewHolder> {
    Context context;
    ArrayList<Dialogue> dialogues;

    public DialogueAdapter(Context context, ArrayList<Dialogue> dialogues) {
        this.context = context;
        this.dialogues = dialogues;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation, parent, false);
        ViewHolder viewHolder = new DialogueAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  DialogueAdapter.ViewHolder holder, int position) {
        holder.speakerA.setText(Html.fromHtml(
                dialogues.get(position).getSpeakerOne()
        ));
        holder.speakerB.setText(Html.fromHtml(
                dialogues.get(position).getSpeakerTwo()
        ));
    }

    @Override
    public int getItemCount() {
        return dialogues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView speakerA,speakerB;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
          speakerA = itemView.findViewById(R.id.txtOne);
          speakerB =itemView.findViewById(R.id.txtTwo);
        }

    }
}
