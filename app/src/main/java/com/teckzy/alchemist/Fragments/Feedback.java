package com.teckzy.alchemist.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.teckzy.alchemist.API.APIClient;
import com.teckzy.alchemist.API.APIInterface;
import com.teckzy.alchemist.MainActivity;
import com.teckzy.alchemist.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feedback extends Fragment
{
    APIInterface apiInterface;
    ImageView ivRating1,ivRating2,ivRating3,ivRating4,ivRating5;
    TextView tvRating;
    Button btnSubmit;
    EditText etFeedback;
    JSONArray jsonArray;
    JSONObject jsonObject;
    int rating = 0;
    SharedPreferences sharedPreferences;
    public static final String ALCHEMIST = "ALCHEMIST";

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        ivRating1 = view.findViewById(R.id.ivRating1);
        ivRating2 = view.findViewById(R.id.ivRating2);
        ivRating3 = view.findViewById(R.id.ivRating3);
        ivRating4 = view.findViewById(R.id.ivRating4);
        ivRating5 = view.findViewById(R.id.ivRating5);
        tvRating = view.findViewById(R.id.tvRating);
        etFeedback = view.findViewById(R.id.etFeedback);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        sharedPreferences = getActivity().getSharedPreferences(ALCHEMIST, Context.MODE_PRIVATE);

        ivRating1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setRating1();
                rating = 1;
            }
        });

        ivRating2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setRating2();
                rating = 2;
            }
        });

        ivRating3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setRating3();
                rating = 3;
            }
        });

        ivRating4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setRating4();
                rating = 4;
            }
        });

        ivRating5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setRating5();
                rating = 5;
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (rating == 0)
                {
                    Toast.makeText(getActivity(),"Please enter rating !!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    apiAddFeedback(sharedPreferences.getInt("userId",0),rating,etFeedback.getText().toString());
                }
            }
        });

        return view;
    }

    public void apiAddFeedback(int customerId, int rateValue, String message)
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<String> call = apiInterface.addFeedback(customerId,rateValue,message);
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {
                if (response.isSuccessful())
                {
                    try {
                        jsonArray = new JSONArray(response.body());
                        jsonObject = jsonArray.getJSONObject(0);
                        Toast.makeText(getActivity(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();

                        if (jsonObject.getString("status").equals("true"))
                        {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
                else
                {
                    Toast.makeText(getActivity(),"Please try again later !!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setRating1()
    {
        ivRating1.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating2.setImageDrawable(getResources().getDrawable(R.drawable.favourites_border));
        ivRating3.setImageDrawable(getResources().getDrawable(R.drawable.favourites_border));
        ivRating4.setImageDrawable(getResources().getDrawable(R.drawable.favourites_border));
        ivRating5.setImageDrawable(getResources().getDrawable(R.drawable.favourites_border));
    }
    public void setRating2()
    {
        ivRating1.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating2.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating3.setImageDrawable(getResources().getDrawable(R.drawable.favourites_border));
        ivRating4.setImageDrawable(getResources().getDrawable(R.drawable.favourites_border));
        ivRating5.setImageDrawable(getResources().getDrawable(R.drawable.favourites_border));
    }
    public void setRating3()
    {
        ivRating1.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating2.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating3.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating4.setImageDrawable(getResources().getDrawable(R.drawable.favourites_border));
        ivRating5.setImageDrawable(getResources().getDrawable(R.drawable.favourites_border));
    }
    public void setRating4()
    {
        ivRating1.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating2.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating3.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating4.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating5.setImageDrawable(getResources().getDrawable(R.drawable.favourites_border));
    }
    public void setRating5()
    {
        ivRating1.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating2.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating3.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating4.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
        ivRating5.setImageDrawable(getResources().getDrawable(R.drawable.favourites_filled));
    }
}
