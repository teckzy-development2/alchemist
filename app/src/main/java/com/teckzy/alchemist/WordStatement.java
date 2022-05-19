package com.teckzy.alchemist;

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
import com.teckzy.alchemist.Adapter.WordStatementAdapter;
import com.teckzy.alchemist.Model.WordStatementPojo;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordStatement extends Activity
{
    TextView tvToolbar;
    ImageView backIcon;
    int wordId;
    APIInterface apiInterface;
    RecyclerView rvWordStatement;
    WordStatementAdapter wordStatementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_statement);

        tvToolbar = findViewById(R.id.tvToolbar);
        tvToolbar.setText("Word Statement");
        backIcon = findViewById(R.id.backIcon);
        rvWordStatement = findViewById(R.id.rvWordStatement);

        wordId = getIntent().getIntExtra("wordId",0);

        backIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        apiGetWordStatements(wordId);
    }

    public void apiGetWordStatements(int wordId)
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<List<WordStatementPojo>> call = apiInterface.getWordStatement(wordId);
        call.enqueue(new Callback<List<WordStatementPojo>>()
        {
            @Override
            public void onResponse(Call<List<WordStatementPojo>> call, Response<List<WordStatementPojo>> response)
            {
                if (response.isSuccessful())
                {
                    generateWordStatement(response.body());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please try again later !!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<WordStatementPojo>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void generateWordStatement(List<WordStatementPojo> wordStatementPojoList)
    {
        wordStatementAdapter = new WordStatementAdapter(getApplicationContext(), wordStatementPojoList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvWordStatement.setLayoutManager(mLayoutManager);
        rvWordStatement.setItemAnimator(new DefaultItemAnimator());
        rvWordStatement.setAdapter(wordStatementAdapter);
    }
}
