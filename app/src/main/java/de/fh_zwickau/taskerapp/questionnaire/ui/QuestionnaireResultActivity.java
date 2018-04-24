package de.fh_zwickau.taskerapp.questionnaire.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.fh_zwickau.taskerapp.MainActivity;
import de.fh_zwickau.taskerapp.R;
import de.fh_zwickau.taskerapp.questionnaire.model.Answer;
import de.fh_zwickau.taskerapp.questionnaire.model.Question;
import de.fh_zwickau.taskerapp.questionnaire.model.Questionnaire;
import de.fh_zwickau.taskerapp.questionnaire.service.QuestionnaireService;
import de.fh_zwickau.taskerapp.questionnaire.service.impl.QuestionnaireServiceImpl;
import de.fh_zwickau.taskerapp.questionnaire.ui.adapter.QuestionnaireAdapter;

public class QuestionnaireResultActivity extends AppCompatActivity {
    private Integer questionnaireId;
    private QuestionnaireService questionnaireService = new QuestionnaireServiceImpl();
    private List<Question> questionnaireList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_result);

        Intent intent = getIntent();
        questionnaireId = intent.getIntExtra(QuestionnaireAdapter.EXTRA_QUESTIONNAIRE_ID, 1);
        questionnaireList = questionnaireService.findAllQuestionByQuestionnareId(questionnaireId);

        Integer totalPoints = calcPoints();
        Float avgPoint = calcAveragePoints();
        Integer numberOfQuestions = questionnaireList.size();

        TextView totalPointsTextView = findViewById(R.id.total_points_textview);
        TextView avgPointsTextView = findViewById(R.id.avg_points_textview);
        TextView numberOfQuestionsTextView = findViewById(R.id.number_of_questions_textview);

        totalPointsTextView.setText(totalPointsTextView.getText() + " : " + String.valueOf(totalPoints));
        avgPointsTextView.setText(avgPointsTextView.getText() + " " + String.valueOf(avgPoint));
        numberOfQuestionsTextView.setText(numberOfQuestionsTextView.getText() + " : " + String.valueOf(numberOfQuestions));

        Button okButton = findViewById(R.id.go_to_main_button);
        okButton.setOnClickListener(v ->
            startActivity(new Intent(this, MainActivity.class)
        ));
    }

    private Integer calcPoints() {
        Integer points = 0;
        for(Question question : questionnaireList) {
            points += Answer.getPoints(question.getAnswer());
        }
        return points;
    }

    private Float calcAveragePoints() {
        return (float)calcPoints() / (float) questionnaireList.size();
    }
}
