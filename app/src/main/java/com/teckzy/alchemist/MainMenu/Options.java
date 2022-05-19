package com.teckzy.alchemist.MainMenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.teckzy.alchemist.R;

public class Options extends Activity
{
    TextView tvToolbar;
    ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        tvToolbar = findViewById(R.id.tvToolbar);
        tvToolbar.setText("Options");
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
