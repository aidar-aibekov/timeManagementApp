package de.fh_zwickau.taskerapp.questionnaire.model;

public enum Answer {
    NEVER(1), INFREQUENTLY(2), SOMETIMES(3), FREQUENTLY(4), ALWAYS(5);

    final int points;

    Answer(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
