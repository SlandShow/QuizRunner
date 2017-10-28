package com.test_apps.slandshow.quizrunner.user_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.test_apps.slandshow.quizrunner.QuizActivity;
import com.test_apps.slandshow.quizrunner.QuizController.QuizController;
import com.test_apps.slandshow.quizrunner.R;
import com.test_apps.slandshow.quizrunner.TasksViewer;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.InternalStyleSheet;
import br.tiagohm.markdownview.css.styles.Github;

public class DefaultQuizActivity extends QuizActivity {

    private InternalStyleSheet mStyle = new Github();
    private MarkdownView mMarkdownView;
    private Button startQuiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_quiz);

        mMarkdownView = (MarkdownView) findViewById(R.id.defaultQuizMarkdownView);
        mStyle = new Github();
        mMarkdownView.addStyleSheet(mStyle);
        mMarkdownView.loadMarkdownFromAsset("user_manifest.md");

        String[] array = {
                "task1.md",
                "task2.md",
                "task3.md",
                "task4.md",
                "task5.md",
                "task6.md",
                "task7.md",
                "task8.md",
                "task9.md",
                "task10.md",
                "task11.md"
        };

        controller = new QuizController(array);

        startQuiz = (Button) findViewById(R.id.startDefaultQuizBtn);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TasksViewer.class);
                startActivity(intent);
            }
        });
    }

    public QuizController getController() {
        return controller;
    }
}
