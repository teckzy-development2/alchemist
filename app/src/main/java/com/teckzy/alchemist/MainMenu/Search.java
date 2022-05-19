package com.teckzy.alchemist.MainMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.teckzy.alchemist.R;
import com.teckzy.alchemist.Search.SearchList;

public class Search extends Activity
{
    EditText etStarts,etEnds,etContains;
    ImageView backIcon;
    TextView tvToolbar;
    Button btnNext;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        tvToolbar = findViewById(R.id.tvToolbar);
        backIcon = findViewById(R.id.backIcon);
        etStarts = findViewById(R.id.etStarts);
        etEnds = findViewById(R.id.etEnds);
        etContains = findViewById(R.id.etContains);
        btnNext = findViewById(R.id.btnNext);

        type = getIntent().getStringExtra("type");

        if (type.equals("sentence"))
        {
            tvToolbar.setText("Search Sentence");
        }
        else if (type.equals("word"))
        {
            tvToolbar.setText("Search Word");
            etStarts.setVisibility(View.GONE);
            etEnds.setVisibility(View.GONE);
            etContains.setHint("Search Word");
        }

        backIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), SearchList.class);
                intent.putExtra("start",etStarts.getText().toString());
                intent.putExtra("end",etEnds.getText().toString());
                intent.putExtra("contains",etContains.getText().toString());
                intent.putExtra("type",type);
                startActivity(intent);
            }
        });
    }
}
