package com.teckzy.alchemist.LoginSignUp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.teckzy.alchemist.API.APIClient;
import com.teckzy.alchemist.API.APIInterface;
import com.teckzy.alchemist.MainActivity;
import com.teckzy.alchemist.R;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends Activity
{
    APIInterface apiInterface;
    TextView tvForgotPassword,tvRegister;
    Button btnSignIn;
    long pressedTime;
    JSONObject jsonObject,userObj;
    SharedPreferences sharedPreferences;
    public static final String ALCHEMIST = "ALCHEMIST";
    SharedPreferences.Editor editor;
    EditText etMobile,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvRegister = findViewById(R.id.tvRegister);
        btnSignIn = findViewById(R.id.btnSignIn);

        sharedPreferences = getSharedPreferences(ALCHEMIST, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getBoolean("userLogged",false))
        {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

        tvForgotPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(intent);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (etMobile.getText().length() == 0)
                {
                    etMobile.setError("Field cannot be empty");
                    etMobile.requestFocus();
                }
                else if (etPassword.getText().length() == 0)
                {
                    etPassword.setError("Field cannot be empty");
                    etPassword.requestFocus();
                }
                else
                {
                    apiCheckLogin(etMobile.getText().toString(),etPassword.getText().toString());
                }
            }
        });
    }

    public void apiCheckLogin(String mobileNo, String password)
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<String> call = apiInterface.checkLogin(mobileNo,password);
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {
                if (response.isSuccessful())
                {
                    try
                    {
                        jsonObject = new JSONObject(response.body());
                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

                        if (jsonObject.getString("status").equals("true"))
                        {
                            userObj = new JSONObject(jsonObject.getString("customer"));

                            editor.putInt("userId",userObj.getInt("customer_id"));
                            editor.putString("userName",userObj.getString("name"));
                            editor.putString("userMobile",userObj.getString("mobile_no"));
                            editor.putString("userEmail",userObj.getString("email"));
                            editor.putBoolean("userLogged",true);
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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

    @Override
    public void onBackPressed()
    {
        if (pressedTime + 2000 > System.currentTimeMillis())
        {
            super.onBackPressed();
            finishAffinity();
        }
        else
        {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}
