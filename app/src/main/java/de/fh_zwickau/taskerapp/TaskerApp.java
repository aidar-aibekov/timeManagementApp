package de.fh_zwickau.taskerapp;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.util.Log;

import de.fh_zwickau.taskerapp.questionnaire.dao.AppDatabase;
import de.fh_zwickau.taskerapp.questionnaire.model.Questionnaire;
import de.fh_zwickau.taskerapp.questionnaire.service.QuestionnaireService;
import de.fh_zwickau.taskerapp.questionnaire.service.impl.QuestionnaireServiceImpl;

public class TaskerApp extends Application {


    public TaskerApp() {

    }

    private void initDatabase() {
        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasker-db")
                .build();
        AppDatabase.setAppDatabase(appDatabase);
    }

    private void initMigration() {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                QuestionnaireService questionnaireService = new QuestionnaireServiceImpl();
                questionnaireService.save(
                        new Questionnaire("Short-range planning test"),
                        new Questionnaire("Time attitudes"),
                        new Questionnaire("Long-Range Planning"));
                return null;
            }
        }.execute();
        Log.v("after migrate", "migration done");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDatabase();
        initMigration();
    }
}
