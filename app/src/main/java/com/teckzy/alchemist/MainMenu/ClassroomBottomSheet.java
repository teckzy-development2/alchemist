package com.teckzy.alchemist.MainMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.teckzy.alchemist.LessonStatement;
import com.teckzy.alchemist.R;

public class ClassroomBottomSheet extends BottomSheetDialogFragment
{
    TextView tvWords,tvSentence,tvTestUptodate;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.layout_bottom_sheet_classroom, container, false);

        tvWords = view.findViewById(R.id.tvWords);
        tvSentence = view.findViewById(R.id.tvSentence);
        tvTestUptodate = view.findViewById(R.id.tvTestUptodate);

        tvWords.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), Words.class);
                startActivity(intent);
            }
        });

        tvSentence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(),Lessons.class);
                startActivity(intent);
            }
        });

        tvTestUptodate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        return view;
    }
}
