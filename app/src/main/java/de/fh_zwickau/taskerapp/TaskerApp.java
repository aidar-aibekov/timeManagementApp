package de.fh_zwickau.taskerapp;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import de.fh_zwickau.taskerapp.questionnaire.dao.AppDatabase;
import de.fh_zwickau.taskerapp.questionnaire.model.Question;
import de.fh_zwickau.taskerapp.questionnaire.model.Questionnaire;
import de.fh_zwickau.taskerapp.questionnaire.service.QuestionService;
import de.fh_zwickau.taskerapp.questionnaire.service.QuestionnaireService;
import de.fh_zwickau.taskerapp.questionnaire.service.impl.QuestionServiceImpl;
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

        QuestionnaireService questionnaireService = new QuestionnaireServiceImpl();
        questionnaireService.save(
                new Questionnaire(1, "Short-range planning test"),
                new Questionnaire(2, "Time attitudes"),
                new Questionnaire(3, "Long-Range Planning"));

        QuestionService questionService = new QuestionServiceImpl();
        questionService.save(
                new Question(1, "Do you make a list of the things you have to do each day?"),
                new Question(1, "Do you plan your day before you start it?"),
                new Question(1, "Do you make a schedule of the activities you have to do on work days?"),
                new Question(1, "Do you write a set of goals for yourself for each day?"),
                new Question(1, "Do you spend time each day planning?"),
                new Question(1, "Do you have a clear idea of what you want to accomplish during the next week?"),
                new Question(1, "Do you set and honor priorities?"),

                new Question(2, "Do you often find yourself doing things which interfere with your schoolwork simply because you hate to say \"No\" to people? *"),
                new Question(2, "Do you feel you are in charge of your own time, by and large?"),
                new Question(2, "On an average class day do you spend more time with personal grooming than doing schoolwork?*"),
                new Question(2, "Do you believe that there is room for improvement in the way you manage your time? *"),
                new Question(2, "Do you make constructive use of your time?"),
                new Question(2, " Do you continue unprofitable routines or activities?*"),

                new Question(3, "Do you usually keep your desk clear of everything other than what you are currently working on?"),
                new Question(3, " Do you have a set of goals for the entire quarter?"),
                new Question(3, "The night before a major assignment is due, are you usually still working on it? *"),
                new Question(3, "When you have several things to do, do you think it is best to do a little bit of work on each one?"),
                new Question(3, " Do you regularly review your class notes, even when a test is not imminent?")
        );

        Log.v("after migrate", "migration done");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        initDatabase();


        if (firstTimeRun()) {
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
