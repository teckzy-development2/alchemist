package com.teckzy.alchemist.Model;

import com.google.gson.annotations.SerializedName;

public class SubscriptionPojo
{
    @SerializedName("subscription_id")
    int subscriptionId;
    @SerializedName("subscription_name")
    String subscriptionName;
    @SerializedName("subscription_value")
    String subscriptionValue;
    @SerializedName("description")
    String description;

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public String getSubscriptionValue() {
        return subscriptionValue;
    }

    public void setSubscriptionValue(String subscriptionValue) {
        this.subscriptionValue = subscriptionValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
