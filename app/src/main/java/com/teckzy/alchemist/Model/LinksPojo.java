package com.teckzy.alchemist.Model;

import com.google.gson.annotations.SerializedName;

public class LinksPojo
{
    @SerializedName("link_id")
    int linkId;
    @SerializedName("description")
    String description;
    @SerializedName("link")
    String link;

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
