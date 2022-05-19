package com.teckzy.alchemist.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.teckzy.alchemist.Model.SearchListPojo;
import com.teckzy.alchemist.R;
import java.util.List;
import java.util.Locale;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.MyViewHolder>
{
    Context context;
    private final List<SearchListPojo> searchListPojoList;
    TextToSpeech textToSpeech;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivVoiceStatements,ivVoiceTranslation,ivBoth;
        TextView tvStatement,tvTranslation;

        public MyViewHolder(View view)
        {
            super(view);
            ivBoth = view.findViewById(R.id.ivBoth);
            tvStatement = view.findViewById(R.id.tvStatement);
            tvTranslation = view.findViewById(R.id.tvTranslation);
            ivVoiceStatements = view.findViewById(R.id.ivVoiceStatements);
            ivVoiceTranslation = view.findViewById(R.id.ivVoiceTranslation);
        }
    }
    public SearchListAdapter(Context context, List<SearchListPojo> searchListPojoList)
    {
        this.context = context;
        this.searchListPojoList = searchListPojoList;
    }

    @NonNull
    @Override
    public SearchListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_search_list, parent, false);

        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int i)
            {
                if(i!=TextToSpeech.ERROR)
                {
                    textToSpeech.setLanguage(new Locale("ta","IN"));
                }
            }
        });

        return new SearchListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        final SearchListPojo searchListPojo = searchListPojoList.get(position);

        holder.tvStatement.setText(searchListPojo.getStatements());
        holder.tvTranslation.setText(searchListPojo.getTranslation());

        holder.ivVoiceStatements.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textToSpeech.speak(searchListPojo.getStatements(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        holder.ivVoiceTranslation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textToSpeech.speak(searchListPojo.getTranslation(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        holder.ivBoth.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textToSpeech.speak(searchListPojo.getStatements() + searchListPojo.getTranslation(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchListPojoList.size();
    }
}
