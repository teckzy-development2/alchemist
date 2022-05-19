package com.teckzy.alchemist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Notification extends Activity
{
    TextView tvToolbar;
    ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        tvToolbar = findViewById(R.id.tvToolbar);
        tvToolbar.setText("Notification");
        backIcon = findViewById(R.id.backIcon);

        backIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }
}
