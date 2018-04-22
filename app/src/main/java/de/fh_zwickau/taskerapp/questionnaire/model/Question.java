package de.fh_zwickau.taskerapp.questionnaire.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Questionnaire.class, parentColumns = "id",
                childColumns = "questionnaire_id")
})
public class Question implements de.fh_zwickau.taskerapp.questionnaire.model.Entity {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "questionnaire_id")
    private Integer questionnaireId;
    private String question;
    private String answer;

    public Integer getId() {
        return id;
    }

    @Override
    public Class getClassName() {
        return Question.class;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
