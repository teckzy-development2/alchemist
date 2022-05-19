package com.teckzy.alchemist.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.teckzy.alchemist.LessonExplanation;
import com.teckzy.alchemist.LinksVideoView;
import com.teckzy.alchemist.Model.LinksPojo;
import com.teckzy.alchemist.R;
import java.util.List;

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.MyViewHolder>
{
    Context context;
    private final List<LinksPojo> linksPojoList;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout rlLinks;
        TextView tvTitle;

        public MyViewHolder(View view)
        {
            super(view);
            rlLinks = view.findViewById(R.id.rlLinks);
            tvTitle = view.findViewById(R.id.tvTitle);
        }
    }
    public LinksAdapter(Context context, List<LinksPojo> linksPojoList)
    {
        this.context = context;
        this.linksPojoList = linksPojoList;
    }

    @NonNull
    @Override
    public LinksAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_links, parent, false);

        return new LinksAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final LinksAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        final LinksPojo linksPojo = linksPojoList.get(position);

        holder.tvTitle.setText(linksPojo.getDescription());

        holder.rlLinks.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, LinksVideoView.class);
                intent.putExtra("link",linksPojo.getLink());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return linksPojoList.size();
    }
}
