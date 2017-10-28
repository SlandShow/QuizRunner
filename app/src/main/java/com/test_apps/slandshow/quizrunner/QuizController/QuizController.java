package com.test_apps.slandshow.quizrunner.QuizController;

import com.test_apps.slandshow.quizrunner.TasksViewer;

/**
 * Created by Admin on 14.07.2017.
 */

public class QuizController {
    public static String[] defaultQuizTasks;
    public static int index;
    public static int timeLimit;

    public static enum QUIZ_PROCESS {
        RUNNING, STOPED
    }

    public QuizController(String[] defaultQuizTasks) {
        this.defaultQuizTasks = defaultQuizTasks;
        init();
    }

    private void init() {
        index = 0;
    }

    public static void change(int i) {
        index = i;
    }
    
    public static void clear() {
        index = 0;
        defaultQuizTasks = null;
    }

}
