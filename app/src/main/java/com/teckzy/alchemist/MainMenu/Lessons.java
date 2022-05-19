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
import com.teckzy.alchemist.Model.LessonPojo;
import com.teckzy.alchemist.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Lessons extends Activity
{
    APIInterface apiInterface;
    TextView tvToolbar;
    ImageView backIcon;
    RecyclerView rvLesson;
    LessonAdapter lessonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        tvToolbar = findViewById(R.id.tvToolbar);
        tvToolbar.setText("Lessons");
        backIcon = findViewById(R.id.backIcon);
        rvLesson = findViewById(R.id.rvLesson);

        backIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        apiGetLesson();
    }

    public void apiGetLesson()
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<List<LessonPojo>> call = apiInterface.getLesson();
        call.enqueue(new Callback<List<LessonPojo>>()
        {
            @Override
            public void onResponse(Call<List<LessonPojo>> call, Response<List<LessonPojo>> response)
            {
                if (response.isSuccessful())
                {
                    generateLesson(response.body());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please try again later !!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LessonPojo>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void generateLesson(List<LessonPojo> lessonPojoList)
    {
        lessonAdapter = new LessonAdapter(getApplicationContext(), lessonPojoList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvLesson.setLayoutManager(mLayoutManager);
        rvLesson.setItemAnimator(new DefaultItemAnimator());
        rvLesson.setAdapter(lessonAdapter);
    }
}
