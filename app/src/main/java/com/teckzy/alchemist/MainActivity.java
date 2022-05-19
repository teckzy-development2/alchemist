package com.teckzy.alchemist;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.teckzy.alchemist.Fragments.About;
import com.teckzy.alchemist.Fragments.ContactUs;
import com.teckzy.alchemist.Fragments.Favourites;
import com.teckzy.alchemist.Fragments.Feedback;
import com.teckzy.alchemist.Fragments.Home;
import com.teckzy.alchemist.Fragments.PrivacyPolicy;
import com.teckzy.alchemist.Fragments.Subscription;
import com.teckzy.alchemist.Fragments.TermsAndConditions;
import com.teckzy.alchemist.LoginSignUp.ChangeForgotPassword;
import com.teckzy.alchemist.LoginSignUp.Login;
import com.teckzy.alchemist.MainMenu.Lessons;
import com.teckzy.alchemist.MainMenu.SearchBottomSheet;

public class MainActivity extends AppCompatActivity
{
    Fragment fragment = null;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    ImageView notification;
    TextView navigationHome,tvUsername,tvUserMobile,tvUserEmail;
    private long pressedTime;
    SharedPreferences sharedPreferences;
    public static final String ALCHEMIST = "ALCHEMIST";
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        notification = findViewById(R.id.notification);
        navigationHome = findViewById(R.id.navigationHome);
        View header = navigationView.getHeaderView(0);
        tvUsername = header.findViewById(R.id.tvUsername);
        tvUserMobile = header.findViewById(R.id.tvUserMobile);
        tvUserEmail = header.findViewById(R.id.tvUserEmail);

        bottomNavigationView.setItemIconTintList(null);
        navigationView.setItemIconTintList(null);

        sharedPreferences = getSharedPreferences(ALCHEMIST, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        tvUsername.setText(sharedPreferences.getString("userName",""));
        tvUserMobile.setText(sharedPreferences.getString("userMobile",""));
        tvUserEmail.setText(sharedPreferences.getString("userEmail",""));

        notification.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Notification.class);
                startActivity(intent);
            }
        });

        navigationHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                fragment = new Home();
                loadFragment(fragment);
            }
        });

        if (fragment == null)
        {
            fragment = new Home();
            loadFragment(fragment);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem ->
        {
            displaySelectedScreen(menuItem.getItemId());
            return true;
        });

        navigationView.setNavigationItemSelectedListener(menuItem ->
        {
            displaySelectedScreen(menuItem.getItemId());
            return true;
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.menu);
        toggle.syncState();

        toggle.setToolbarNavigationClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
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

    @SuppressLint("NonConstantResourceId")
    private void displaySelectedScreen(int itemId)
    {
        switch (itemId)
        {
            case R.id.navigationSearch:
                SearchBottomSheet searchBottomSheet = new SearchBottomSheet();
                searchBottomSheet.show(getSupportFragmentManager(),"Bottom Sheet");
                break;
            case R.id.navigationHome:
                fragment = new Home();
                loadFragment(fragment);
                break;
            case R.id.navigationLearning:
                Intent intent = new Intent(getApplicationContext(), Lessons.class);
                startActivity(intent);
                break;
            case R.id.navigationFavourites:
                fragment = new Favourites();
                loadFragment(fragment);
                break;
            case R.id.navigationSubscription:
                fragment = new Subscription();
                loadFragment(fragment);
                break;
            case R.id.navigationShareApp:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                String shareMessage = "*CONSULT TOP AYURVEDA & SIDDHA DOCTORS ONLINE*" + "\n" +
                        "Consult Online Doctors, Medicine Delivery, Online Pharmacy" + "\n" +
                        "Download Eezaa Mobile app from" + "\n" +
                        "https://play.google.com/store/apps/details?id=com.teckzy.eezaa";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
                break;
            case R.id.navigationPrivacyPolicy:
                fragment = new PrivacyPolicy();
                loadFragment(fragment);
                break;
            case R.id.navigationAbout:
                fragment = new About();
                loadFragment(fragment);
                break;
            case R.id.navigationChangePassword:
                intent = new Intent(getApplicationContext(), ChangeForgotPassword.class);
                intent.putExtra("action","change_password");
                startActivity(intent);
                break;
            case R.id.navigationTermsConditions:
                fragment = new TermsAndConditions();
                loadFragment(fragment);
                break;
            case R.id.navigationContactUs:
                fragment = new ContactUs();
                loadFragment(fragment);
                break;
            case R.id.navigationFeedback:
                fragment = new Feedback();
                loadFragment(fragment);
                break;
            case R.id.navigationLogout:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setMessage("Are you sure you want to Logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        editor.putInt("userId",0);
                        editor.putString("userName","");
                        editor.putString("userMobile","");
                        editor.putString("userEmail","");
                        editor.putBoolean("userLogged",false);
                        editor.commit();
                        Toast.makeText(getApplicationContext(),"Logout Successful !!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
                AlertDialog alert=builder.create();
                alert.show();
                break;
        }
    }

    public void loadFragment(Fragment fragment)
    {
        if (fragment != null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, fragment);
            ft.commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}