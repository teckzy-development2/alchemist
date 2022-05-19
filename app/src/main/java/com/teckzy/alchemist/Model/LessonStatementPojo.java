package com.teckzy.alchemist.Model;

import com.google.gson.annotations.SerializedName;

public class LessonStatementPojo
{
    @SerializedName("lstate_id")
    int lStateId;
    @SerializedName("lesson_id")
    int lessonId;
    @SerializedName("statement")
    String statement;
    @SerializedName("translation")
    String translation;
    @SerializedName("status")
    String favourite;

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public int getlStateId() {
        return lStateId;
    }

    public void setlStateId(int lStateId) {
        this.lStateId = lStateId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
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
