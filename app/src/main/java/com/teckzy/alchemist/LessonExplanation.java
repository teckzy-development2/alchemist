package com.teckzy.alchemist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LessonExplanation extends Activity
{
    TextView tvToolbar,tvFavourites,tvTestFromLesson,tvPositiveStatements,tvNegativeStatements,
            tvPositiveQuestions,tvNegativeQuestions,tvWhQuestions,tvIndirectSpeech,tvPassiveVoice;
    ImageView backIcon;
    int lessonId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_explanation);

        tvToolbar = findViewById(R.id.tvToolbar);
        tvToolbar.setText("Lessons");
        backIcon = findViewById(R.id.backIcon);
        tvFavourites = findViewById(R.id.tvFavourites);
        tvTestFromLesson = findViewById(R.id.tvTestFromLesson);
        tvPositiveStatements = findViewById(R.id.tvPositiveStatements);
        tvNegativeStatements = findViewById(R.id.tvNegativeStatements);
        tvPositiveQuestions = findViewById(R.id.tvPositiveQuestions);
        tvNegativeQuestions = findViewById(R.id.tvNegativeQuestions);
        tvWhQuestions = findViewById(R.id.tvWhQuestions);
        tvIndirectSpeech = findViewById(R.id.tvIndirectSpeech);
        tvPassiveVoice = findViewById(R.id.tvPassiveVoice);

        lessonId = getIntent().getIntExtra("lessonId",0);

        backIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        tvFavourites.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),LessonFavourites.class);
                intent.putExtra("lessonId",lessonId);
                startActivity(intent);
            }
        });

        tvTestFromLesson.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        tvPositiveStatements.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), LessonStatement.class);
                intent.putExtra("lessonId",lessonId);
                intent.putExtra("type","PS");
                startActivity(intent);
            }
        });

        tvNegativeStatements.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), LessonStatement.class);
                intent.putExtra("lessonId",lessonId);
                intent.putExtra("type","NS");
                startActivity(intent);
            }
        });

        tvPositiveQuestions.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), LessonStatement.class);
                intent.putExtra("lessonId",lessonId);
                intent.putExtra("type","PQ");
                startActivity(intent);
            }
        });

        tvNegativeQuestions.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), LessonStatement.class);
                intent.putExtra("lessonId",lessonId);
                intent.putExtra("type","NQ");
                startActivity(intent);
            }
        });

        tvWhQuestions.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), LessonStatement.class);
                intent.putExtra("lessonId",lessonId);
                intent.putExtra("type","WHQ");
                startActivity(intent);
            }
        });

        tvIndirectSpeech.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), LessonStatement.class);
                intent.putExtra("lessonId",lessonId);
                intent.putExtra("type","IS");
                startActivity(intent);
            }
        });

        tvPassiveVoice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), LessonStatement.class);
                intent.putExtra("lessonId",lessonId);
                intent.putExtra("type","PV");
                startActivity(intent);
            }
        });
    }
}
