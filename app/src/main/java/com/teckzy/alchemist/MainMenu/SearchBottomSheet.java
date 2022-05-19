package com.teckzy.alchemist.MainMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.teckzy.alchemist.R;

public class SearchBottomSheet extends BottomSheetDialogFragment
{
    LinearLayout llSearchBySentence,llSearchByWord;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.layout_bottom_sheet_search, container, false);

        llSearchBySentence = view.findViewById(R.id.llSearchBySentence);
        llSearchByWord = view.findViewById(R.id.llSearchByWord);

        llSearchBySentence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), Search.class);
                intent.putExtra("type","sentence");
                startActivity(intent);
            }
        });

        llSearchByWord.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), Search.class);
                intent.putExtra("type","word");
                startActivity(intent);
            }
        });

        return view;
    }
}
