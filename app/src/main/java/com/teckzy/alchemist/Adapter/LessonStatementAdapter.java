package com.teckzy.alchemist.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teckzy.alchemist.API.APIClient;
import com.teckzy.alchemist.API.APIInterface;
import com.teckzy.alchemist.Model.LessonStatementPojo;
import com.teckzy.alchemist.Model.SearchListPojo;
import com.teckzy.alchemist.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonStatementAdapter extends RecyclerView.Adapter<LessonStatementAdapter.MyViewHolder>
{
    APIInterface apiInterface;
    Context context;
    private final List<LessonStatementPojo> lessonStatementPojoList;
    TextToSpeech textToSpeech;
    JSONArray jsonArray;
    JSONObject jsonObject;
    SharedPreferences sharedPreferences;
    public static final String ALCHEMIST = "ALCHEMIST";

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
    public LessonStatementAdapter(Context context, List<LessonStatementPojo> lessonStatementPojoList)
    {
        this.context = context;
        this.lessonStatementPojoList = lessonStatementPojoList;
    }

    @NonNull
    @Override
    public LessonStatementAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_statements, parent, false);

        sharedPreferences = context.getSharedPreferences(ALCHEMIST, Context.MODE_PRIVATE);

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

        return new LessonStatementAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final LessonStatementAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        final LessonStatementPojo lessonStatementPojo = lessonStatementPojoList.get(position);

        holder.tvStatement.setText(lessonStatementPojo.getStatement());
        holder.tvTranslation.setText(lessonStatementPojo.getTranslation());

        if (lessonStatementPojo.getFavourite().equals("true"))
        {
            holder.ivFavourites.setImageDrawable(context.getResources().getDrawable(R.drawable.favorite_black_24dp));
        }
        else
        {
            holder.ivFavourites.setImageDrawable(context.getResources().getDrawable(R.drawable.favorite_border_black_24dp));
        }

        holder.ivVoiceStatements.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textToSpeech.speak(lessonStatementPojo.getStatement(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        holder.ivVoiceTranslation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textToSpeech.speak(lessonStatementPojo.getTranslation(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        holder.ivBoth.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textToSpeech.speak(lessonStatementPojo.getStatement() + lessonStatementPojo.getTranslation(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        holder.ivFavourites.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                apiLessonFavourites(lessonStatementPojo.getLessonId(),lessonStatementPojo.getlStateId(),
                        sharedPreferences.getInt("userId",0));

                if (lessonStatementPojo.getFavourite().equals("true"))
                {
                    holder.ivFavourites.setImageDrawable(context.getResources().getDrawable(R.drawable.favorite_border_black_24dp));
                    lessonStatementPojo.setFavourite("false");
                }
                else
                {
                    holder.ivFavourites.setImageDrawable(context.getResources().getDrawable(R.drawable.favorite_black_24dp));
                    lessonStatementPojo.setFavourite("true");
                }
            }
        });
    }

    public void apiLessonFavourites(int lessonId,int stateId,int customerId)
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<String> call = apiInterface.addLessonFavourites(lessonId,stateId,customerId);
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {
                if (response.isSuccessful())
                {
                    try
                    {
                        jsonArray = new JSONArray(response.body());
                        jsonObject = jsonArray.getJSONObject(0);
                        Toast.makeText(context,jsonObject.getString("msg"),Toast.LENGTH_SHORT).show();
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(context,"Please try again later !!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessonStatementPojoList.size();
    }
}
