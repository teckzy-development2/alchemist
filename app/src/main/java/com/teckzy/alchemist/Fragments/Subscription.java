package com.teckzy.alchemist.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.teckzy.alchemist.API.APIClient;
import com.teckzy.alchemist.API.APIInterface;
import com.teckzy.alchemist.Adapter.SubscriptionAdapter;
import com.teckzy.alchemist.Model.SubscriptionPojo;
import com.teckzy.alchemist.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Subscription extends Fragment
{
    APIInterface apiInterface;
    RecyclerView rvSubscription;
    SubscriptionAdapter subscriptionAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_subscription, container, false);

        rvSubscription = view.findViewById(R.id.rvSubscription);

        apiGetSubscription();

        return view;
    }

    public void apiGetSubscription()
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<List<SubscriptionPojo>> call = apiInterface.getSubscription();
        call.enqueue(new Callback<List<SubscriptionPojo>>()
        {
            @Override
            public void onResponse(Call<List<SubscriptionPojo>> call, Response<List<SubscriptionPojo>> response)
            {
                if (response.isSuccessful())
                {
                    generateSubscription(response.body());
                }
                else
                {
                    Toast.makeText(getActivity(),"Please try again later !!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SubscriptionPojo>> call, Throwable t)
            {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void generateSubscription(List<SubscriptionPojo> subscriptionPojoList)
    {
        subscriptionAdapter = new SubscriptionAdapter(getActivity(), subscriptionPojoList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        rvSubscription.setLayoutManager(mLayoutManager);
        rvSubscription.setItemAnimator(new DefaultItemAnimator());
        rvSubscription.setAdapter(subscriptionAdapter);
    }
}
