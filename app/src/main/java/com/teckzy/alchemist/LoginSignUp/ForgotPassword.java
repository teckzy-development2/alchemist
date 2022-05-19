package com.teckzy.alchemist.LoginSignUp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class ForgotPassword extends Activity
{
    APIInterface apiInterface;
    EditText etMobile;
    Button btnGetOtp;
    JSONArray jsonArray;
    JSONObject jsonObject;
    String mobilePattern = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etMobile = findViewById(R.id.etMobile);
        btnGetOtp = findViewById(R.id.btnGetOtp);

        btnGetOtp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (etMobile.getText().length() == 0)
                {
                    Toast.makeText(getApplicationContext(),"Please enter Mobile number",Toast.LENGTH_SHORT).show();
                }
                else if (!(etMobile.getText().toString().matches(mobilePattern)))
                {
                    Toast.makeText(getApplicationContext(),"Please enter valid Mobile number",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    apiForgotPassword(etMobile.getText().toString());
                }
            }
        });
    }

    public void apiForgotPassword(String mobileNo)
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<String> call = apiInterface.forgotPassword(mobileNo);
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

                        if (jsonObject.getString("status").equals("true"))
                        {
                            Toast.makeText(getApplicationContext(),jsonObject.getString("otp_no"),Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),OTPVerification.class);
                            intent.putExtra("mobileNo",mobileNo);
                            intent.putExtra("otp",jsonObject.getString("otp_no"));
                            intent.putExtra("action","forgot_password");
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),jsonObject.getString("msg"),Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please try again later !!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
