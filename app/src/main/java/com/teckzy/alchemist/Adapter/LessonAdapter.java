package com.teckzy.alchemist.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teckzy.alchemist.LessonExplanation;
import com.teckzy.alchemist.Model.LessonPojo;
import com.teckzy.alchemist.R;
import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.MyViewHolder>
{
    Context context;
    private final List<LessonPojo> lessonPojoList;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvLesson;

        public MyViewHolder(View view)
        {
            super(view);
            tvLesson = view.findViewById(R.id.tvLesson);
        }
    }
    public LessonAdapter(Context context, List<LessonPojo> lessonPojoList)
    {
        this.context = context;
        this.lessonPojoList = lessonPojoList;
    }

    @NonNull
    @Override
    public LessonAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_lesson, parent, false);

        return new LessonAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final LessonAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        final LessonPojo lessonPojo = lessonPojoList.get(position);

        holder.tvLesson.setText(lessonPojo.getLesson());

        holder.tvLesson.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, LessonExplanation.class);
                intent.putExtra("lessonId",lessonPojo.getLessonId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessonPojoList.size();
    }
}
