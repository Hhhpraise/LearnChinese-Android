package com.praise.Haneasy.Screens.Bot.Modal;

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

public class ChatAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ChatModal> chatsModalArrayList;
    MyClickInterface messageClickInterface;

    public ChatAdapter(Context context, ArrayList<ChatModal> chatsModalArrayList, MyClickInterface messageClickInterface) {
        this.context = context;
        this.chatsModalArrayList = chatsModalArrayList;
        this.messageClickInterface = messageClickInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg, parent, false);
                return new UserViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg, parent, false);
                return new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatModal chatsModal = chatsModalArrayList.get(position);
        switch (chatsModal.getSender()) {
            case "user":
                ((UserViewHolder) holder).userText.setText(chatsModal.getMessage());
                ((UserViewHolder) holder).time.setText(chatsModal.getTime());
                break;
            case "bot":
                ((BotViewHolder) holder).botTv.setText(chatsModal.getMessage());
                ((BotViewHolder) holder).time.setText(chatsModal.getTime());
                break;

        }
    }

    @Override
    public int getItemCount() {
        return chatsModalArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (chatsModalArrayList.get(position).getSender()) {
            case "user":
                return 0;
            case "bot":
                return 1;
            default:
                return -1;
        }
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userText,time;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userText = itemView.findViewById(R.id.commentTXT);
            time = itemView.findViewById(R.id.user_time);

        }
    }

    public class BotViewHolder extends RecyclerView.ViewHolder {
        TextView botTv,time;

        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botTv = itemView.findViewById(R.id.idTVBot);
            time = itemView.findViewById(R.id.bot_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageClickInterface.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}
