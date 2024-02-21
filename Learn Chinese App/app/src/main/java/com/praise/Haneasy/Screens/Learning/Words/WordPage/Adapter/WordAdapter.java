package com.praise.Haneasy.Screens.Learning.Words.WordPage.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.MyClickInterface;

import java.util.ArrayList;

public class WordAdapter extends  RecyclerView.Adapter<WordAdapter.ViewHolder> {
    Context context;
    ArrayList<Word> words;
    MyClickInterface clickInterface;

    public WordAdapter(Context context, ArrayList<Word> words, MyClickInterface clickInterface) {
        this.context = context;
        this.words = words;
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_holder, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  WordAdapter.ViewHolder holder, int position) {
        holder.english.setText(words.get(position).getEnglishText());
        holder.chinese.setText(words.get(position).getChineseCharacter());
        holder.pinyin.setText(words.get(position).getPinyin());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {
        TextView english,chinese,pinyin;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            english = (TextView) itemView.findViewById(R.id.english_word);
            chinese = (TextView) itemView.findViewById(R.id.chinese_character);
            pinyin = (TextView) itemView.findViewById(R.id.pinyin_text);
            layout = (LinearLayout) itemView.findViewById(R.id.show_character);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onItemClick(getAdapterPosition());
                    layout.setVisibility(View.VISIBLE);
                }
            });
        }
    }



}
