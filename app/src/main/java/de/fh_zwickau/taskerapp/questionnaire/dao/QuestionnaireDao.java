package de.fh_zwickau.taskerapp.questionnaire.dao;

import android.arch.persistence.room.Query;

import java.util.List;

import de.fh_zwickau.taskerapp.questionnaire.model.Question;
import de.fh_zwickau.taskerapp.questionnaire.model.Questionnaire;

@android.arch.persistence.room.Dao
public interface QuestionnaireDao extends Dao<Questionnaire> {
    @Query("SELECT * FROM questionnaire WHERE id = :questionnaireId")
    Questionnaire findById(Integer questionnaireId);

    @Query("SELECT * FROM questionnaire")
    List<Questionnaire> findAll();

    @Query("SELECT * FROM question WHERE questionnaire_id = :questionnaireId")
    List<Question> findAllQuestionByQuestionnareId(Integer questionnaireId);
}
