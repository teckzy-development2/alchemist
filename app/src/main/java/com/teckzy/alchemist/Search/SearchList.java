package com.teckzy.alchemist.Search;

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
import com.teckzy.alchemist.Adapter.SearchListAdapter;
import com.teckzy.alchemist.Model.SearchListPojo;
import com.teckzy.alchemist.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchList extends Activity
{
    TextView tvToolbar;
    ImageView backIcon;
    SearchListAdapter searchListAdapter;
    APIInterface apiInterface;
    RecyclerView rvSearch;
    String start,end,contains,type;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        tvToolbar = findViewById(R.id.tvToolbar);
        backIcon = findViewById(R.id.backIcon);
        rvSearch = findViewById(R.id.rvSearch);

        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        contains = getIntent().getStringExtra("contains");
        type = getIntent().getStringExtra("type");

        backIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        if (type.equals("sentence"))
        {
            tvToolbar.setText("Searched Sentences");
            apiGetSearchSentence(start,end,contains);
        }
        else if (type.equals("word"))
        {
            tvToolbar.setText("Searched Words");
            apiGetSearchWord(contains);
        }
    }

    public void apiGetSearchSentence(String start, String end, String contains)
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<List<SearchListPojo>> call = apiInterface.searchSentence(start,end,contains);
        call.enqueue(new Callback<List<SearchListPojo>>()
        {
            @Override
            public void onResponse(Call<List<SearchListPojo>> call, Response<List<SearchListPojo>> response)
            {
                if (response.isSuccessful())
                {
                    generateSearchList(response.body());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please try again later !!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SearchListPojo>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void apiGetSearchWord(String contains)
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<List<SearchListPojo>> call = apiInterface.searchWord(contains);
        call.enqueue(new Callback<List<SearchListPojo>>()
        {
            @Override
            public void onResponse(Call<List<SearchListPojo>> call, Response<List<SearchListPojo>> response)
            {
                if (response.isSuccessful())
                {
                    generateSearchList(response.body());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please try again later !!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SearchListPojo>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void generateSearchList(List<SearchListPojo> searchListPojoList)
    {
        searchListAdapter = new SearchListAdapter(getApplicationContext(), searchListPojoList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvSearch.setLayoutManager(mLayoutManager);
        rvSearch.setItemAnimator(new DefaultItemAnimator());
        rvSearch.setAdapter(searchListAdapter);
    }
}
