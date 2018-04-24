package de.fh_zwickau.taskerapp.questionnaire.service.impl;

import android.os.AsyncTask;
import android.support.annotation.WorkerThread;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
        try {
            return new AsyncTask<Void, Void, List<Question>>() {

                @Override
                protected List<Question> doInBackground(Void... voids) {
                    return ((QuestionnaireDao)dao).findAllQuestionByQuestionnareId(questionnaireId);
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
