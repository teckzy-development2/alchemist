package com.teckzy.alchemist;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.teckzy.alchemist.Adapter.LessonFavouritesAdapter;
import com.teckzy.alchemist.Model.LessonFavouritesPojo;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonFavourites extends Activity
{
    APIInterface apiInterface;
    RecyclerView rvLessonFavourites;
    int lessonId;
    TextView tvToolbar;
    ImageView backIcon;
    SharedPreferences sharedPreferences;
    public static final String ALCHEMIST = "ALCHEMIST";
    LessonFavouritesAdapter lessonFavouritesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_favourites);

        tvToolbar = findViewById(R.id.tvToolbar);
        tvToolbar.setText("Favourites");
        backIcon = findViewById(R.id.backIcon);
        rvLessonFavourites = findViewById(R.id.rvLessonFavourites);

        sharedPreferences = getSharedPreferences(ALCHEMIST, Context.MODE_PRIVATE);

        lessonId = getIntent().getIntExtra("lessonId",0);

        backIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        apiGetLessonFavourites(lessonId,sharedPreferences.getInt("userId",0));
    }

    public void apiGetLessonFavourites(int lessonId,int customerId)
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<List<LessonFavouritesPojo>> call = apiInterface.getLessonFavourites(lessonId,customerId);
        call.enqueue(new Callback<List<LessonFavouritesPojo>>()
        {
            @Override
            public void onResponse(Call<List<LessonFavouritesPojo>> call, Response<List<LessonFavouritesPojo>> response)
            {
                if (response.isSuccessful())
                {
                    generateLessonFavourites(response.body());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please try again later !!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LessonFavouritesPojo>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void generateLessonFavourites(List<LessonFavouritesPojo> lessonFavouritesPojoList)
    {
        lessonFavouritesAdapter = new LessonFavouritesAdapter(getApplicationContext(), lessonFavouritesPojoList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvLessonFavourites.setLayoutManager(mLayoutManager);
        rvLessonFavourites.setItemAnimator(new DefaultItemAnimator());
        rvLessonFavourites.setAdapter(lessonFavouritesAdapter);
    }
}
