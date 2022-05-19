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
import com.teckzy.alchemist.Model.LessonFavouritesPojo;
import com.teckzy.alchemist.R;
import java.util.List;
import java.util.Locale;

public class LessonFavouritesAdapter extends RecyclerView.Adapter<LessonFavouritesAdapter.MyViewHolder>
{
    Context context;
    private final List<LessonFavouritesPojo> lessonFavouritesPojoList;
    TextToSpeech textToSpeech;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivVoiceStatements,ivVoiceTranslation,ivBoth,ivFavourites;
        TextView tvStatement,tvTranslation;

        public MyViewHolder(View view)
        {
            super(view);
            ivFavourites = view.findViewById(R.id.ivFavourites);
            ivBoth = view.findViewById(R.id.ivBoth);
            tvStatement = view.findViewById(R.id.tvStatement);
            tvTranslation = view.findViewById(R.id.tvTranslation);
            ivVoiceStatements = view.findViewById(R.id.ivVoiceStatements);
            ivVoiceTranslation = view.findViewById(R.id.ivVoiceTranslation);
        }
    }
    public LessonFavouritesAdapter(Context context, List<LessonFavouritesPojo> lessonFavouritesPojoList)
    {
        this.context = context;
        this.lessonFavouritesPojoList = lessonFavouritesPojoList;
    }

    @NonNull
    @Override
    public LessonFavouritesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_statements, parent, false);

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

        return new LessonFavouritesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final LessonFavouritesAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        final LessonFavouritesPojo lessonFavouritesPojo = lessonFavouritesPojoList.get(position);

        holder.tvStatement.setText(lessonFavouritesPojo.getStatement());
        holder.tvTranslation.setText(lessonFavouritesPojo.getTranslation());

        holder.ivFavourites.setVisibility(View.GONE);


        holder.ivVoiceStatements.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textToSpeech.speak(lessonFavouritesPojo.getStatement(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        holder.ivVoiceTranslation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textToSpeech.speak(lessonFavouritesPojo.getTranslation(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        holder.ivBoth.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textToSpeech.speak(lessonFavouritesPojo.getStatement() + lessonFavouritesPojo.getTranslation(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        holder.ivFavourites.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }

    @Override
    public int getItemCount() {
        return lessonFavouritesPojoList.size();
    }
}
