package com.test_apps.slandshow.quizrunner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.test_apps.slandshow.quizrunner.QuizController.QuizController;
import com.test_apps.slandshow.quizrunner.Timing.QuizTimer;
import com.test_apps.slandshow.quizrunner.Timing.TimerTask;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.InternalStyleSheet;
import br.tiagohm.markdownview.css.StyleSheet;
import br.tiagohm.markdownview.css.styles.*;


public class TasksViewer extends AppCompatActivity {

    private InternalStyleSheet mStyle = new Github();
    private MarkdownView mMarkdownView;
    private String currentTaskFile;
    private QuizActivity quizActivity;
    private Button nextTask, prewiousTask;
    private TextView taskNumber;
    private static TimerTask timerTask;
    public static QuizTimer timer;
    public static TextView timeInfo;
    public static QuizController.QUIZ_PROCESS currentContext;
    private static TasksViewer self;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_viewer);

        currentContext = QuizController.QUIZ_PROCESS.RUNNING;
        self = this;

        mMarkdownView = (MarkdownView) findViewById(R.id.markdown_view);
        mStyle = new Github();
        mMarkdownView.addStyleSheet(mStyle);

        nextTask = (Button) findViewById(R.id.nextTaskBtn);
        prewiousTask = (Button) findViewById(R.id.previousTaskBtn);
        mMarkdownView.loadMarkdownFromAsset(QuizController.defaultQuizTasks[0]); // Default quiz test
        taskNumber = (TextView) findViewById(R.id.taskNumberTxt);
        taskNumber.setText("1"); // Default

        timer = new QuizTimer();
        timeInfo = (TextView) findViewById(R.id.timerInfoTxt);
        // timerTask = new TimerTask();
        // timerTask.execute();
        changeUI();

        nextTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizController.index < QuizController.defaultQuizTasks.length - 1) {
                    QuizController.change(QuizController.index + 1);

                    // Update new task
                    setCodeQuestionContent(QuizController.defaultQuizTasks[QuizController.index]);
                    update();

                    // Update UI
                    updateUI(QuizController.index + 1); // Indexing starts with zero, because we need to add + 1
                } else {
                    createDialogFrame();
                }
            }
        });

        prewiousTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizController.index > 0) {
                    QuizController.change(QuizController.index - 1);

                    // Update new task
                    setCodeQuestionContent(QuizController.defaultQuizTasks[QuizController.index]);
                    update();

                    // Update UI
                    updateUI(QuizController.index + 1); // Indexing starts with zero, because we need to add + 1
                }
            }
        });
    }

    public void setCodeQuestionContent(String questionName) {
        currentTaskFile = questionName;
    }

    public int update() {
        try {
            mMarkdownView.loadMarkdownFromAsset(currentTaskFile);
        } catch (Exception exe) {
            return -1;
        }
        return 0;
    }

    private void updateUI(int taskNumber) {
        this.taskNumber.setText("" + taskNumber);
    }

    private void createDialogFrame() {
        AlertDialog.Builder ad;
        final Context context;

        context = TasksViewer.this;
        String title = "Важное сообщение";
        String message = "Вы хотите закончить попытку?\n" + "Потраченное время: " + timer.calculateTotalTime();
        String button1String = "Yes";
        String button2String = "No";

        ad = new AlertDialog.Builder(context);
        ad.setTitle(title);  // заголовок
        ad.setMessage(message); // сообщение
        ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                Toast.makeText(context, "Тест завершён",
                        Toast.LENGTH_LONG).show();

                // TODO: End of test
            }
        });
        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                Toast.makeText(context, "Продолжайте попытку...", Toast.LENGTH_LONG)
                        .show();
            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(context, "Вы ничего не выбрали",
                        Toast.LENGTH_LONG).show();
            }
        });

        ad.show();
    }

    public static void changeUI() {
        final Handler myHandler = new Handler(); // автоматически привязывается к текущему потоку.
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                timeInfo.setText(timer.calculateTotalTime());
            }
        };

        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (TasksViewer.currentContext == QuizController.QUIZ_PROCESS.RUNNING)
                    myHandler.post(runnable);
            }
        });

        myThread.start();

    }
}
