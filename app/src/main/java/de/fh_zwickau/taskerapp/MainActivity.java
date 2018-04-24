package de.fh_zwickau.taskerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import de.fh_zwickau.taskerapp.questionnaire.ui.QuestionnaireListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView textView = findViewById(R.id.my_text_view);
//
//
//        Intent intent = new Intent(this, QuestionnaireListActivity.class);
//        startActivity(intent);
        initialiseMainPageGUI();
    }

    public void initialiseMainPageGUI(){
        Button viewAllTasksButton=(Button)findViewById(R.id.viewAllTasksButton);
        viewAllTasksButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity", "viewAllTasksButton has just been pressed");
            }
        });
        viewAllTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ViewAllTasksActivity.class);
//                getIntent().putExtra(PropertyApp.EXTRA_TO_RENT, "false");
                startActivity(i);
            }
        });

        Button addNewTaskButton=(Button)findViewById(R.id.addNewTaskButton);
        addNewTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity", "addNewTaskButton has just been pressed");
            }
        });
        addNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddTaskActivity.class);
//                getIntent().putExtra(PropertyApp.EXTRA_TO_RENT, "false");
                startActivity(i);
            }
        });
        Button passTheTestButton=(Button)findViewById(R.id.passTheTestButton);
        passTheTestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity", "passTheTest has just been pressed");
            }
        });
        passTheTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, QuestionnaireListActivity.class);
//                getIntent().putExtra(PropertyApp.EXTRA_TO_RENT, "false");
                startActivity(i);
            }
        });

        Button aboutTimeManagement=(Button)findViewById(R.id.aboutTimeManagemnetButton);
        aboutTimeManagement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity", "aboutTimeManagement has just been pressed");
            }
        });
        aboutTimeManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AbouttmActivity.class);
//                getIntent().putExtra(PropertyApp.EXTRA_TO_RENT, "false");
                startActivity(i);
            }
        });

    }
}
