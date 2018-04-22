package de.fh_zwickau.taskerapp.questionnaire.service;

import java.util.List;

import de.fh_zwickau.taskerapp.questionnaire.model.Entity;

public interface PersistenceService<T extends Entity> {

    List<T> getAll();

    void save(T ... objects);

    T getById(Integer id);

    void remove (T ... objects);
}
