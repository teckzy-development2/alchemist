package com.teckzy.alchemist.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.teckzy.alchemist.MainMenu.ClassroomBottomSheet;
import com.teckzy.alchemist.MainMenu.Lessons;
import com.teckzy.alchemist.MainMenu.Links;
import com.teckzy.alchemist.MainMenu.Options;
import com.teckzy.alchemist.MainMenu.Words;
import com.teckzy.alchemist.R;
import com.teckzy.alchemist.MainMenu.SearchBottomSheet;

public class Home extends Fragment
{
    CardView cvLessons,cvWords,cvSearch,cvClassroom,cvLinks,cvOptions;

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cvLessons = view.findViewById(R.id.cvLessons);
        cvWords = view.findViewById(R.id.cvWords);
        cvSearch = view.findViewById(R.id.cvSearch);
        cvClassroom = view.findViewById(R.id.cvClassroom);
        cvLinks = view.findViewById(R.id.cvLinks);
        cvOptions = view.findViewById(R.id.cvOptions);

        cvLessons.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), Lessons.class);
                startActivity(intent);
            }
        });

        cvWords.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), Words.class);
                startActivity(intent);
            }
        });

        cvSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SearchBottomSheet searchBottomSheet = new SearchBottomSheet();
                searchBottomSheet.show(getActivity().getSupportFragmentManager(),"Bottom Sheet");
            }
        });

        cvClassroom.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ClassroomBottomSheet classroomBottomSheet = new ClassroomBottomSheet();
                classroomBottomSheet.show(getActivity().getSupportFragmentManager(),"Bottom Sheet");
            }
        });

        cvLinks.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), Links.class);
                startActivity(intent);
            }
        });

        cvOptions.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), Options.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
