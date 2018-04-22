package de.fh_zwickau.taskerapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import de.fh_zwickau.taskerapp.questionnaire.dao.AppDatabase;

public class TaskerApp extends Application {


    public TaskerApp() {

    }

    private void initDatabase() {
        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasker-db")
                .build();
        AppDatabase.setAppDatabase(appDatabase);
    }


}
