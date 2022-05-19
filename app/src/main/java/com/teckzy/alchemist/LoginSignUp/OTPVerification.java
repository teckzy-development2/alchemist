package com.teckzy.alchemist.LoginSignUp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class OTPVerification extends Activity
{
    APIInterface apiInterface;
    EditText etOtp1,etOtp2,etOtp3,etOtp4;
    Button btnVerify;
    String otp,mobileNo,action,name,email,password;
    JSONObject jsonObject,userObj;
    SharedPreferences sharedPreferences;
    public static final String ALCHEMIST = "ALCHEMIST";
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        etOtp1 = findViewById(R.id.etOtp1);
        etOtp2 = findViewById(R.id.etOtp2);
        etOtp3 = findViewById(R.id.etOtp3);
        etOtp4 = findViewById(R.id.etOtp4);
        btnVerify = findViewById(R.id.btnVerify);

        sharedPreferences = getSharedPreferences(ALCHEMIST, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        EditText[] edit = {etOtp1, etOtp2, etOtp3, etOtp4};

        etOtp1.addTextChangedListener(new GenericTextWatcher(etOtp1, edit));
        etOtp2.addTextChangedListener(new GenericTextWatcher(etOtp2, edit));
        etOtp3.addTextChangedListener(new GenericTextWatcher(etOtp3, edit));
        etOtp4.addTextChangedListener(new GenericTextWatcher(etOtp4, edit));

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        mobileNo = getIntent().getStringExtra("mobileNo");
        otp = getIntent().getStringExtra("otp");
        action = getIntent().getStringExtra("action");

        btnVerify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (etOtp1.getText().length() == 0 || etOtp2.getText().length() == 0 ||
                        etOtp3.getText().length() == 0 || etOtp4.getText().length() == 0)
                {
                    Toast.makeText(getApplicationContext(),"Please enter OTP !!",Toast.LENGTH_SHORT).show();
                }
                else if (!(otp.equals(etOtp1.getText().toString() + etOtp2.getText().toString() +
                        etOtp3.getText().toString() + etOtp4.getText().toString())))
                {
                    Toast.makeText(getApplicationContext(),"OTP does not match !!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (action.equals("register"))
                    {
                        apiRegister(name,password,email,mobileNo);
                    }
                    else
                    {
                        Intent intent = new Intent(getApplicationContext(),ChangeForgotPassword.class);
                        intent.putExtra("mobileNo",mobileNo);
                        intent.putExtra("action",action);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public void apiRegister(String name,String password,String email,String mobile)
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<String> call = apiInterface.register(name,password,email,mobile);
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
                        Toast.makeText(getApplicationContext(),jsonObject.getString("msg"),Toast.LENGTH_SHORT).show();

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
}
