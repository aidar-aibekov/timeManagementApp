package de.fh_zwickau.taskerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import de.fh_zwickau.taskerapp.questionnaire.ui.QuestionnaireListActivity;
import de.fh_zwickau.taskerapp.todoapp.TodoEntryActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseMainPageGUI();
    }

    public void initialiseMainPageGUI(){
        Button viewAllTasksButton= findViewById(R.id.viewAllTasksButton);
        viewAllTasksButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TodoEntryActivity.class);
            startActivity(intent);
        });

        Button addNewTaskButton= findViewById(R.id.addNewTaskButton);
        addNewTaskButton.setOnClickListener(v -> Log.d("MainActivity", "addNewTaskButton has just been pressed"));
        addNewTaskButton.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(i);
        });
        Button passTheTestButton= findViewById(R.id.passTheTestButton);
        passTheTestButton.setOnClickListener(v -> Log.d("MainActivity", "passTheTest has just been pressed"));
        passTheTestButton.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, QuestionnaireListActivity.class);
            startActivity(i);
        });

        Button aboutTimeManagement= findViewById(R.id.aboutTimeManagemnetButton);
        aboutTimeManagement.setOnClickListener(v -> Log.d("MainActivity", "aboutTimeManagement has just been pressed"));
        aboutTimeManagement.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, AbouttmActivity.class);
            startActivity(i);
        });

    }
}
