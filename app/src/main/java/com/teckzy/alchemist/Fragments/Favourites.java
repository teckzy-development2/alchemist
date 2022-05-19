package com.teckzy.alchemist.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.teckzy.alchemist.R;

public class Favourites extends Fragment
{
    RecyclerView rvLessonFavourites,rvWordFavourites;

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        rvLessonFavourites = view.findViewById(R.id.rvLessonFavourites);
        rvWordFavourites = view.findViewById(R.id.rvWordFavourites);

        return view;
    }
}
