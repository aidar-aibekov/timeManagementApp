package de.fh_zwickau.taskerapp.questionnaire.service.impl;

import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
        try {
            return new AsyncTask<Void, Void, List<Question>>() {

                @Override
                protected List<Question> doInBackground(Void... voids) {
                    return ((QuestionDao)dao).findAllWhereQuestionnareId(questionnaireId);
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


}
