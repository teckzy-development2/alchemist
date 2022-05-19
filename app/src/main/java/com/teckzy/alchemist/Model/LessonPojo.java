package com.teckzy.alchemist.Model;

import com.google.gson.annotations.SerializedName;

public class LessonPojo
{
    @SerializedName("lesson_id")
    int lessonId;
    @SerializedName("lesson")
    String lesson;

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }
}
