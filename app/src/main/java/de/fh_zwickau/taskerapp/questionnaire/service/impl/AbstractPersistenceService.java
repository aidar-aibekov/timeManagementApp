package de.fh_zwickau.taskerapp.questionnaire.service.impl;

import java.util.List;

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
        return dao.findAll();
    }

    @Override
    public void save(T... objects) {
        dao.insert(objects);
    }

    @Override
    public T getById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public void remove(T... objects) {
        dao.delete(objects);
    }

    protected abstract Class<T> getClassName();
}
