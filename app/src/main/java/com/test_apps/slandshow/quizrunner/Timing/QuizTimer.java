package com.test_apps.slandshow.quizrunner.Timing;

import java.util.Calendar;

/**
 * Created by Admin on 16.07.2017.
 */

public class QuizTimer {

    private long startTime;
    private long totalTime;
    private Calendar calendar;
    private int minutes, seconds;

    public QuizTimer() {
        startTime = System.currentTimeMillis();
        calendar = Calendar.getInstance();
    }

    public String calculateTotalTime() {
        totalTime = System.currentTimeMillis() - startTime;
        calendar.setTimeInMillis(totalTime);

        minutes = calendar.get(Calendar.MINUTE);
        seconds = calendar.get(Calendar.SECOND);

        return minutes + " : " + seconds;
    }

    public int getMinutes() {
        totalTime = System.currentTimeMillis() - startTime;
        calendar.setTimeInMillis(totalTime);

        return calendar.get(Calendar.MINUTE);
    }

    public int getSeconds() {
        totalTime = System.currentTimeMillis() - startTime;
        calendar.setTimeInMillis(totalTime);

        return calendar.get(Calendar.SECOND);
    }
}
