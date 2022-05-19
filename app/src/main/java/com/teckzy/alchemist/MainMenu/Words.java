package com.teckzy.alchemist.MainMenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teckzy.alchemist.API.APIClient;
import com.teckzy.alchemist.API.APIInterface;
import com.teckzy.alchemist.Adapter.LessonAdapter;
import com.teckzy.alchemist.Adapter.WordAdapter;
import com.teckzy.alchemist.Model.LessonPojo;
import com.teckzy.alchemist.Model.WordPojo;
import com.teckzy.alchemist.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Words extends Activity
{
    APIInterface apiInterface;
    TextView tvToolbar;
    ImageView backIcon;
    WordAdapter wordAdapter;
    RecyclerView rvWord;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        tvToolbar = findViewById(R.id.tvToolbar);
        tvToolbar.setText("Words");
        backIcon = findViewById(R.id.backIcon);
        rvWord = findViewById(R.id.rvWord);

        backIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        apiGetWord();
    }

    public void apiGetWord()
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<List<WordPojo>> call = apiInterface.getWord();
        call.enqueue(new Callback<List<WordPojo>>()
        {
            @Override
            public void onResponse(Call<List<WordPojo>> call, Response<List<WordPojo>> response)
            {
                if (response.isSuccessful())
                {
                    generateWord(response.body());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please try again later !!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<WordPojo>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void generateWord(List<WordPojo> wordPojoList)
    {
        wordAdapter = new WordAdapter(getApplicationContext(), wordPojoList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvWord.setLayoutManager(mLayoutManager);
        rvWord.setItemAnimator(new DefaultItemAnimator());
        rvWord.setAdapter(wordAdapter);
    }
}
