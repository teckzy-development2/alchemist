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
import com.teckzy.alchemist.Adapter.LinksAdapter;
import com.teckzy.alchemist.Model.LessonPojo;
import com.teckzy.alchemist.Model.LinksPojo;
import com.teckzy.alchemist.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Links extends Activity
{
    APIInterface apiInterface;
    TextView tvToolbar;
    ImageView backIcon;
    RecyclerView rvLinks;
    LinksAdapter linksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);

        tvToolbar = findViewById(R.id.tvToolbar);
        tvToolbar.setText("Links");
        backIcon = findViewById(R.id.backIcon);
        rvLinks = findViewById(R.id.rvLinks);

        backIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        apiGetLinks();
    }

    public void apiGetLinks()
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<List<LinksPojo>> call = apiInterface.getLinks();
        call.enqueue(new Callback<List<LinksPojo>>()
        {
            @Override
            public void onResponse(Call<List<LinksPojo>> call, Response<List<LinksPojo>> response)
            {
                if (response.isSuccessful())
                {
                    generateLinks(response.body());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please try again later !!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LinksPojo>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void generateLinks(List<LinksPojo> linksPojoList)
    {
        linksAdapter = new LinksAdapter(getApplicationContext(), linksPojoList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvLinks.setLayoutManager(mLayoutManager);
        rvLinks.setItemAnimator(new DefaultItemAnimator());
        rvLinks.setAdapter(linksAdapter);
    }
}
