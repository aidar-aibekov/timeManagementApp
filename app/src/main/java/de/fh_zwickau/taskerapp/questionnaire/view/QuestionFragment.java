package de.fh_zwickau.taskerapp.questionnaire.view;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.fh_zwickau.taskerapp.R;
import de.fh_zwickau.taskerapp.questionnaire.model.Answer;
import de.fh_zwickau.taskerapp.questionnaire.model.Question;
import de.fh_zwickau.taskerapp.questionnaire.service.QuestionService;
import de.fh_zwickau.taskerapp.questionnaire.service.impl.QuestionServiceImpl;


public class QuestionFragment extends Fragment {

    private static final String QUESTION_ID = "QUESTION_ID";
    private Question question  = new Question(1, "");

    public QuestionFragment() {

    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_question, container, false);

        TextView textView = inflate.findViewById(R.id.question_text_view);
        textView.setText(question.getQuestion());

        RadioGroup radioGroup = inflate.findViewById(R.id.answer_radio_group);


        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = radioGroup.findViewById(checkedId);
            question.setAnswer(String.valueOf(radioButton.getText()));
        });
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
