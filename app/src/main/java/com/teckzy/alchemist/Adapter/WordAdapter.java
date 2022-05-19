package com.teckzy.alchemist.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.teckzy.alchemist.Model.WordPojo;
import com.teckzy.alchemist.R;
import com.teckzy.alchemist.WordStatement;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.MyViewHolder>
{
    Context context;
    private final List<WordPojo> wordPojoList;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvWord;

        public MyViewHolder(View view)
        {
            super(view);
            tvWord = view.findViewById(R.id.tvWord);
        }
    }
    public WordAdapter(Context context, List<WordPojo> wordPojoList)
    {
        this.context = context;
        this.wordPojoList = wordPojoList;
    }

    @NonNull
    @Override
    public WordAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_word, parent, false);

        return new WordAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final WordAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        final WordPojo wordPojo = wordPojoList.get(position);

        holder.tvWord.setText(wordPojo.getWords());

        holder.tvWord.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, WordStatement.class);
                intent.putExtra("wordId",wordPojo.getWordId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordPojoList.size();
    }
}
