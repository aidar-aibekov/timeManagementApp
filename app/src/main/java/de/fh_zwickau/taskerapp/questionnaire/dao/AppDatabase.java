package de.fh_zwickau.taskerapp.questionnaire.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import de.fh_zwickau.taskerapp.questionnaire.exception.DaoNotFoundException;
import de.fh_zwickau.taskerapp.questionnaire.model.Entity;
import de.fh_zwickau.taskerapp.questionnaire.model.Question;
import de.fh_zwickau.taskerapp.questionnaire.model.Questionnaire;

@Database(version = 1, entities = {Question.class, Questionnaire.class})
public abstract class AppDatabase extends RoomDatabase {
    static AppDatabase appDatabase;

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public static void setAppDatabase(AppDatabase appDatabase) {
        AppDatabase.appDatabase = appDatabase;
    }

    abstract public QuestionDao questionDao();

    abstract public QuestionnaireDao questionnaireDao();

    public Dao getDao(Class entityClass) throws DaoNotFoundException {
        if(Question.class == entityClass) {
            return questionDao();
        }
        if(Questionnaire.class == entityClass) {
            return questionnaireDao();
        }
        throw new DaoNotFoundException("there is no such entity class or table");
    }
}
