package com.test_apps.slandshow.quizrunner.Timing;

import android.os.AsyncTask;

import com.test_apps.slandshow.quizrunner.QuizController.QuizController;
import com.test_apps.slandshow.quizrunner.TasksViewer;

/**
 * Created by Admin on 16.07.2017.
 */

public class TimerTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        while (TasksViewer.currentContext == QuizController.QUIZ_PROCESS.RUNNING) {
            TasksViewer.changeUI();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... aVoid) {
        super.onProgressUpdate(aVoid);
    }
}
