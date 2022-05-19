package com.teckzy.alchemist.Model;

import com.google.gson.annotations.SerializedName;

public class WordStatementPojo
{
    @SerializedName("wstate_id")
    int wStateId;
    @SerializedName("word_id")
    int wordId;
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

    public int getwStateId() {
        return wStateId;
    }

    public void setwStateId(int wStateId) {
        this.wStateId = wStateId;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
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
