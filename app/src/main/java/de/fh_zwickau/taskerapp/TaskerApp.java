package de.fh_zwickau.taskerapp;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import de.fh_zwickau.taskerapp.questionnaire.dao.AppDatabase;
import de.fh_zwickau.taskerapp.questionnaire.model.Questionnaire;
import de.fh_zwickau.taskerapp.questionnaire.service.QuestionnaireService;
import de.fh_zwickau.taskerapp.questionnaire.service.impl.QuestionnaireServiceImpl;

public class TaskerApp extends Application {
public static final String FIRST_TIME_RUN = "FIRST_TIME_RUN";

    private SharedPreferences prefs;
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
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        initDatabase();


        if(firstTimeRun()){
            initMigration();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(FIRST_TIME_RUN, false);
            editor.commit();
        }

    }

    private boolean firstTimeRun() {
        return prefs.getBoolean(FIRST_TIME_RUN, true);
    }
}
