package de.fh_zwickau.taskerapp.questionnaire.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;
import de.fh_zwickau.taskerapp.questionnaire.model.Question;

@Dao
public interface QuestionDao extends de.fh_zwickau.taskerapp.questionnaire.dao.Dao<Question> {
    @Query("SELECT * FROM question WHERE id = :id")
    Question findById(Integer id);

    @Query("SELECT * FROM question")
    List<Question> findAll();

    @Query("SELECT * FROM question WHERE questionnaire_id = :questionnaireId")
    List<Question> findAllWhereQuestionnareId(Integer questionnaireId);
}
