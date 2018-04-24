package de.fh_zwickau.taskerapp.questionnaire.service.impl;

import android.os.AsyncTask;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import de.fh_zwickau.taskerapp.questionnaire.dao.AppDatabase;
import de.fh_zwickau.taskerapp.questionnaire.dao.Dao;
import de.fh_zwickau.taskerapp.questionnaire.exception.DaoNotFoundException;
import de.fh_zwickau.taskerapp.questionnaire.model.Entity;
import de.fh_zwickau.taskerapp.questionnaire.service.PersistenceService;

public abstract class AbstractPersistenceService<T extends Entity> implements PersistenceService<T> {

    protected Dao<T> dao;

    public AbstractPersistenceService() {
        try {
            dao = AppDatabase.getAppDatabase().getDao(getClassName());
        } catch (DaoNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<T> getAll() {
        try {
            return new AsyncTask<Void, Void, List<T>>() {

                @Override
                protected List<T> doInBackground(Void... voids) {
                    return dao.findAll();
                }
            }.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void save(T... objects) {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] os) {
                dao.insert(objects);
                return null;
            }
        }.execute();
    }

    @Override
    public T getById(Integer id) {
        try {
            return new AsyncTask<Void, Void, T>() {

                @Override
                protected T doInBackground(Void... voids) {
                    return dao.findById(id);
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(T... objects) {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] os) {
                dao.delete(objects);
                return null;
            }
        }.execute();
    }

    protected abstract Class<T> getClassName();
}
