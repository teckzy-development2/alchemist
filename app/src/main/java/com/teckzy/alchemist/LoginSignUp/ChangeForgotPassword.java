package com.teckzy.alchemist.LoginSignUp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teckzy.alchemist.API.APIClient;
import com.teckzy.alchemist.API.APIInterface;
import com.teckzy.alchemist.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeForgotPassword extends Activity
{
    APIInterface apiInterface;
    Button btnResetPassword;
    String mobileNo,action;
    EditText etOldPassword,etPassword,etConfirmPassword;
    JSONArray jsonArray;
    JSONObject jsonObject;
    Boolean isOldPasswordVisible = false, isPasswordVisible = false, isConfirmPasswordVisible = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_forgot_password);

        etOldPassword = findViewById(R.id.etOldPassword);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        mobileNo = getIntent().getStringExtra("mobileNo");
        action = getIntent().getStringExtra("action");

        if (action.equals("forgot_password"))
        {
            etOldPassword.setVisibility(View.GONE);
        }

        etOldPassword.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                final int RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    if (event.getRawX() >= (etOldPassword.getRight() - etOldPassword.getCompoundDrawables()[RIGHT].getBounds().width())) {
                        int selection = etOldPassword.getSelectionEnd();
                        if (isOldPasswordVisible)
                        {
                            etOldPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                            etOldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            isOldPasswordVisible = false;
                        }
                        else
                        {
                            etOldPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                            etOldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            isOldPasswordVisible = true;
                        }
                        etOldPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        etPassword.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                final int RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    if (event.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[RIGHT].getBounds().width())) {
                        int selection = etPassword.getSelectionEnd();
                        if (isPasswordVisible)
                        {
                            etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            isPasswordVisible = false;
                        }
                        else
                        {
                            etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            isPasswordVisible = true;
                        }
                        etPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        etConfirmPassword.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                final int RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    if (event.getRawX() >= (etConfirmPassword.getRight() - etConfirmPassword.getCompoundDrawables()[RIGHT].getBounds().width())) {
                        int selection = etConfirmPassword.getSelectionEnd();
                        if (isConfirmPasswordVisible)
                        {
                            etConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                            etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            isConfirmPasswordVisible = false;
                        }
                        else
                        {
                            etConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                            etConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            isConfirmPasswordVisible = true;
                        }
                        etConfirmPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        btnResetPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (action.equals("forgot_password"))
                {
                    if (etPassword.getText().length() == 0)
                    {
                        etPassword.setError("Field cannot be empty");
                        etPassword.requestFocus();
                    }
                    else if (etConfirmPassword.getText().length() == 0)
                    {
                        etConfirmPassword.setError("Please confirm password");
                        etConfirmPassword.requestFocus();
                    }
                    else if(!(etPassword.getText().toString().equals(etConfirmPassword.getText().toString())))
                    {
                        Toast.makeText(getApplicationContext(),"Passwords does not match !!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        apiChangeForgotPassword(mobileNo,etPassword.getText().toString());
                    }
                }
                else if (action.equals("change_password"))
                {
                    if (etOldPassword.getText().length() == 0)
                    {
                        etOldPassword.setError("Field cannot be empty");
                        etOldPassword.requestFocus();
                    }
                    else if (etPassword.getText().length() == 0)
                    {
                        etPassword.setError("Field cannot be empty");
                        etPassword.requestFocus();
                    }
                    else if (etConfirmPassword.getText().length() == 0)
                    {
                        etConfirmPassword.setError("Please confirm password");
                        etConfirmPassword.requestFocus();
                    }
                    else if(!(etPassword.getText().toString().equals(etConfirmPassword.getText().toString())))
                    {
                        Toast.makeText(getApplicationContext(),"Passwords does not match !!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        apiChangeForgotPassword(mobileNo,etPassword.getText().toString());
                    }
                }
            }
        });
    }

    public void apiChangeForgotPassword(String mobileNo, String password)
    {
        apiInterface = APIClient.getAPIClient().create(APIInterface.class);
        Call<String> call = apiInterface.changeForgotPassword(mobileNo, password);
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
                        Toast.makeText(getApplicationContext(),jsonObject.getString("msg"),Toast.LENGTH_SHORT).show();

                        if (jsonObject.getString("status").equals("true"))
                        {
                            Intent intent = new Intent(getApplicationContext(),Login.class);
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
