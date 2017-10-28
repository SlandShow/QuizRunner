package com.test_apps.slandshow.quizrunner;

import android.support.v7.app.AppCompatActivity;

import com.test_apps.slandshow.quizrunner.QuizController.QuizController;

/**
 * Created by Admin on 14.07.2017.
 */

public abstract class QuizActivity extends AppCompatActivity {
    protected int currentTaskIndex = 0;
    protected QuizController controller;

    public void setCurrentTaskIndex(int tmp) {
        currentTaskIndex = tmp;
    }

    public int getCurrentTaskIndex() {
        return currentTaskIndex;
    }

    public QuizController getController() {
        return controller;
    }

    public void setController(QuizController controller) {
        this.controller = controller;
    }
}
