package de.fh_zwickau.taskerapp.questionnaire.service.impl;

import java.util.List;

import de.fh_zwickau.taskerapp.questionnaire.dao.QuestionnaireDao;
import de.fh_zwickau.taskerapp.questionnaire.model.Question;
import de.fh_zwickau.taskerapp.questionnaire.model.Questionnaire;
import de.fh_zwickau.taskerapp.questionnaire.service.QuestionnaireService;

public class QuestionnaireServiceImpl extends AbstractPersistenceService<Questionnaire> implements QuestionnaireService {
    @Override
    protected Class<Questionnaire> getClassName() {
        return Questionnaire.class;
    }

    @Override
    public List<Question> findAllQuestionByQuestionnareId(Integer questionnaireId) {
        return ((QuestionnaireDao)dao).findAllQuestionByQuestionnareId(questionnaireId);
    }
}
