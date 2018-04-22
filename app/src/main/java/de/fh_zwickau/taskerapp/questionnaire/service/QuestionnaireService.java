package de.fh_zwickau.taskerapp.questionnaire.service;

import java.util.List;

import de.fh_zwickau.taskerapp.questionnaire.model.Question;
import de.fh_zwickau.taskerapp.questionnaire.model.Questionnaire;

public interface QuestionnaireService extends PersistenceService<Questionnaire> {
    List<Question> findAllQuestionByQuestionnareId(Integer questionnaireId);
}
