package de.fh_zwickau.taskerapp.questionnaire.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import de.fh_zwickau.taskerapp.R;
import de.fh_zwickau.taskerapp.questionnaire.model.Question;
import de.fh_zwickau.taskerapp.questionnaire.service.QuestionService;
import de.fh_zwickau.taskerapp.questionnaire.service.impl.QuestionServiceImpl;
import de.fh_zwickau.taskerapp.questionnaire.ui.adapter.QuestionnaireAdapter;
import de.fh_zwickau.taskerapp.questionnaire.view.QuestionFragment;

public class QuestionLayout extends AppCompatActivity {

    private List<Question> questions = new ArrayList<>();
    private QuestionService questionService = new QuestionServiceImpl();
    private Integer currentQuestionPosition = 0;
    private Integer questionnaireId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        questionnaireId = intent.getIntExtra(QuestionnaireAdapter.EXTRA_QUESTIONNAIRE_ID, 1);
        questions = questionService.getAllByQuestionnareId(questionnaireId);
        setContentView(R.layout.activity_question_layout);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        QuestionFragment fragment = new QuestionFragment();
        fragment.setQuestion(questions.get(currentQuestionPosition));

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.question_fragment, fragment);
        transaction.commit();

        Button nextButton = findViewById(R.id.next_button);

        nextButton.setOnClickListener( v -> {
            Question question = questions.get(currentQuestionPosition);
            questionService.save(question);
            if(questions.size() > currentQuestionPosition + 1) {
                nextFragment();
            } else {
                showResult();
            }
        });
    }

    private void nextFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        QuestionFragment nextFragment = new QuestionFragment();
        nextFragment.setQuestion(questions.get(++currentQuestionPosition));
        transaction.replace(R.id.question_fragment, nextFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    private void showResult() {
        Intent intent = new Intent(this, QuestionnaireResultActivity.class);
        intent.putExtra(QuestionnaireAdapter.EXTRA_QUESTIONNAIRE_ID, questionnaireId);
        startActivity(intent);
    }
}
