package de.fh_zwickau.taskerapp.questionnaire.service.impl;

import java.util.List;

import de.fh_zwickau.taskerapp.questionnaire.dao.QuestionDao;
import de.fh_zwickau.taskerapp.questionnaire.model.Question;
import de.fh_zwickau.taskerapp.questionnaire.service.QuestionService;

public class QuestionServiceImpl extends AbstractPersistenceService<Question> implements QuestionService {
    @Override
    protected Class<Question> getClassName() {
        return Question.class;
    }

    @Override
    public List<Question> getAllByQuestionnareId(Integer questionnaireId) {
        return ((QuestionDao)dao).findAllWhereQuestionnareId(questionnaireId);
    }


}
