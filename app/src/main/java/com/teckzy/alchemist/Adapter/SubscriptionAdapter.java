package com.teckzy.alchemist.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.teckzy.alchemist.Model.SubscriptionPojo;
import com.teckzy.alchemist.R;
import java.util.List;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.MyViewHolder>
{
    Context context;
    private final List<SubscriptionPojo> subscriptionPojoList;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName,tvValue,tvDescription;

        public MyViewHolder(View view)
        {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvValue = view.findViewById(R.id.tvValue);
            tvDescription = view.findViewById(R.id.tvDescription);
        }
    }
    public SubscriptionAdapter(Context context, List<SubscriptionPojo> subscriptionPojoList)
    {
        this.context = context;
        this.subscriptionPojoList = subscriptionPojoList;
    }

    @NonNull
    @Override
    public SubscriptionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_subscription, parent, false);

        return new SubscriptionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SubscriptionAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        final SubscriptionPojo subscriptionPojo = subscriptionPojoList.get(position);

        holder.tvName.setText(subscriptionPojo.getSubscriptionName());
        holder.tvValue.setText("₹ " + subscriptionPojo.getSubscriptionValue());
        holder.tvDescription.setText(subscriptionPojo.getDescription());
    }

    @Override
    public int getItemCount() {
        return subscriptionPojoList.size();
    }
}
