package com.teckzy.alchemist.Model;

import com.google.gson.annotations.SerializedName;

public class SearchListPojo
{
    @SerializedName("lesson_id")
    int lessonId;
    @SerializedName("statement")
    String statements;
    @SerializedName("positive_statement")
    String positiveStatements;
    @SerializedName("translation")
    String translation;

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getStatements() {
        return statements;
    }

    public void setStatements(String statements) {
        this.statements = statements;
    }

    public String getPositiveStatements() {
        return positiveStatements;
    }

    public void setPositiveStatements(String positiveStatements) {
        this.positiveStatements = positiveStatements;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
