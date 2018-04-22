package de.fh_zwickau.taskerapp.questionnaire.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

import de.fh_zwickau.taskerapp.questionnaire.model.Entity;

public interface Dao<T extends Entity> {
    @Insert
    void insert(T... ts);

    @Update
    void update(T t);

    @Delete
    void delete(T... ts);

    List<T> findAll();

    T findById(Integer id);
}