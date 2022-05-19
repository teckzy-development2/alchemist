package com.teckzy.alchemist.Model;

import com.google.gson.annotations.SerializedName;

public class WordPojo
{
    @SerializedName("word_id")
    int wordId;
    @SerializedName("words")
    String words;

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
