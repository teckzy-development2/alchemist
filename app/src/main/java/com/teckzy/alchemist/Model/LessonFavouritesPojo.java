package com.teckzy.alchemist.Model;

import com.google.gson.annotations.SerializedName;

public class LessonFavouritesPojo
{
    @SerializedName("state_id")
    int stateId;
    @SerializedName("statement")
    String statement;
    @SerializedName("translation")
    String translation;


    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
