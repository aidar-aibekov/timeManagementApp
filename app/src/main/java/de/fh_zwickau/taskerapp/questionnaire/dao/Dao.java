package de.fh_zwickau.taskerapp.questionnaire.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

import de.fh_zwickau.taskerapp.questionnaire.model.Entity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

public interface Dao<T extends Entity> {
    @Insert(onConflict = REPLACE)
    void insert(T... ts);

    @Update
    void update(T t);

    @Delete
    void delete(T... ts);

    List<T> findAll();

    T findById(Integer id);
}