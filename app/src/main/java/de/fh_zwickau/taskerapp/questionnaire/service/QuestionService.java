package de.fh_zwickau.taskerapp.questionnaire.service;

import java.util.List;

import de.fh_zwickau.taskerapp.questionnaire.model.Entity;
import de.fh_zwickau.taskerapp.questionnaire.model.Question;

public interface QuestionService extends PersistenceService<Question> {

    List<Question> getAllByQuestionnareId(Integer questionnaireId);
}
