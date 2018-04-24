package de.fh_zwickau.taskerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.fh_zwickau.taskerapp.questionnaire.model.Questionnaire;
import de.fh_zwickau.taskerapp.questionnaire.service.impl.QuestionnaireServiceImpl;
import de.fh_zwickau.taskerapp.questionnaire.ui.QuestionnaireListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.my_text_view);


        Intent intent = new Intent(this, QuestionnaireListActivity.class);
        startActivity(intent);

    }


}
