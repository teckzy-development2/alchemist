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

public class Register extends Activity
{
    APIInterface apiInterface;
    EditText etName,etEmail,etMobile,etPassword,etConfirmPassword;
    Button btnRegister;
    JSONArray jsonArray;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (etName.getText().length() == 0)
                {
                    etName.setError("Field cannot be empty");
                    etName.requestFocus();
                }
                else if (etEmail.getText().length() == 0)
                {
                    etEmail.setError("Field cannot be empty");
                    etEmail.requestFocus();
                }
                else if (etMobile.getText().length() == 0)
                {
                    etMobile.setError("Field cannot be empty");
                    etMobile.requestFocus();
                }
                else if (etPassword.getText().length() == 0)
                {
                    etPassword.setError("Field cannot be empty");
                    etPassword.requestFocus();
                }
                else if (etConfirmPassword.getText().length() == 0)
                {
                    etConfirmPassword.setError("Field cannot be empty");
                    etConfirmPassword.requestFocus();
                }
                else if (!(etPassword.getText().toString().equals(etConfirmPassword.getText().toString())))
                {
                    Toast.makeText(getApplicationContext(),"Passwords does not match !!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    apiGenerateOtp(etMobile.getText().toString());
                }
            }
        });
    }

    public void apiGenerateOtp(String mobileNo)
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<String> call = apiInterface.generateOtp(mobileNo);
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
                            intent.putExtra("name",etName.getText().toString());
                            intent.putExtra("mobileNo",mobileNo);
                            intent.putExtra("email",etEmail.getText().toString());
                            intent.putExtra("password",etPassword.getText().toString());
                            intent.putExtra("otp",jsonObject.getString("otp_no"));
                            intent.putExtra("action","register");
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
