package de.fh_zwickau.taskerapp.questionnaire.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Questionnaire implements de.fh_zwickau.taskerapp.questionnaire.model.Entity {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String name;

    public Questionnaire() {
    }

    public Questionnaire(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Questionnaire(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Class getClassName() {
        return Questionnaire.class;
    }
}
