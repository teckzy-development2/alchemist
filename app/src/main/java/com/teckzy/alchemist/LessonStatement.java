package com.teckzy.alchemist;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.teckzy.alchemist.API.APIClient;
import com.teckzy.alchemist.API.APIInterface;
import com.teckzy.alchemist.Adapter.LessonStatementAdapter;
import com.teckzy.alchemist.Model.LessonStatementPojo;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonStatement extends Activity
{
    APIInterface apiInterface;
    RelativeLayout rlLink;
    RecyclerView rvLessonStatement;
    TextView tvToolbar;
    ImageView backIcon;
    String type;
    int lessonId;
    LessonStatementAdapter lessonStatementAdapter;
    SharedPreferences sharedPreferences;
    public static final String ALCHEMIST = "ALCHEMIST";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_statement);

        tvToolbar = findViewById(R.id.tvToolbar);
        tvToolbar.setText("Lesson Statement");
        backIcon = findViewById(R.id.backIcon);
        rlLink = findViewById(R.id.rlLink);
        rvLessonStatement = findViewById(R.id.rvLessonStatement);

        sharedPreferences = getSharedPreferences(ALCHEMIST, Context.MODE_PRIVATE);

        lessonId = getIntent().getIntExtra("lessonId",0);
        type = getIntent().getStringExtra("type");

        backIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        apiGetLessonStatements(lessonId,type,sharedPreferences.getInt("userId",0));
    }

    public void apiGetLessonStatements(int lessonId, String statement, int customerId)
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<List<LessonStatementPojo>> call = apiInterface.getLessonStatement(lessonId,statement,customerId);
        call.enqueue(new Callback<List<LessonStatementPojo>>()
        {
            @Override
            public void onResponse(Call<List<LessonStatementPojo>> call, Response<List<LessonStatementPojo>> response)
            {
                if (response.isSuccessful())
                {
                    generateLessonStatement(response.body());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please try again later !!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LessonStatementPojo>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void generateLessonStatement(List<LessonStatementPojo> lessonStatementPojoList)
    {
        lessonStatementAdapter = new LessonStatementAdapter(getApplicationContext(), lessonStatementPojoList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvLessonStatement.setLayoutManager(mLayoutManager);
        rvLessonStatement.setItemAnimator(new DefaultItemAnimator());
        rvLessonStatement.setAdapter(lessonStatementAdapter);
    }
}
