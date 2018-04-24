package de.fh_zwickau.taskerapp.questionnaire.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import de.fh_zwickau.taskerapp.R;
import de.fh_zwickau.taskerapp.questionnaire.model.Questionnaire;
import de.fh_zwickau.taskerapp.questionnaire.service.QuestionnaireService;
import de.fh_zwickau.taskerapp.questionnaire.service.impl.QuestionnaireServiceImpl;
import de.fh_zwickau.taskerapp.questionnaire.ui.adapter.QuestionnaireAdapter;

public class QuestionnaireListActivity extends AppCompatActivity {

    private QuestionnaireService questionnaireService = new QuestionnaireServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_list);
        List<Questionnaire> questionnaires = questionnaireService.getAll();
        QuestionnaireAdapter adapter = new QuestionnaireAdapter(questionnaires, this);
        RecyclerView recyclerView = findViewById(R.id.questionnaire_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
