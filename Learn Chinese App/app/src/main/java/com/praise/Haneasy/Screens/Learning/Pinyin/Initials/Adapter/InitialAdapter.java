package com.praise.Haneasy.Screens.Learning.Pinyin.Initials.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Learning.Pinyin.Adapter.PinyinModal;
import com.praise.Haneasy.Screens.MyClickInterface;
import java.util.ArrayList;

public class InitialAdapter  extends RecyclerView.Adapter<InitialAdapter.ViewHolder>{
    Context context;
    ArrayList<PinyinModal> modals;
    MyClickInterface clickInterface;

    public InitialAdapter(Context context, ArrayList<PinyinModal> modals, MyClickInterface clickInterface) {
        this.context = context;
        this.modals = modals;
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pinyin_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InitialAdapter.ViewHolder holder, int position) {
        holder.name.setText(modals.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return modals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.pinyin_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
