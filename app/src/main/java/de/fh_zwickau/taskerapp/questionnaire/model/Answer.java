package de.fh_zwickau.taskerapp.questionnaire.model;

public enum Answer {
    NEVER(1), INFREQUENTLY(2), SOMETIMES(3), FREQUENTLY(4), ALWAYS(5), NOTHING(0);

    final int points;

    Answer(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public static int getPoints(String name) {
        if (name.equalsIgnoreCase(NEVER.name())) {
            return NEVER.points;
        }
        if (name.equalsIgnoreCase(INFREQUENTLY.name())) {
            return INFREQUENTLY.points;
        }
        if (name.equalsIgnoreCase(SOMETIMES.name())) {
            return SOMETIMES.points;
        }
        if (name.equalsIgnoreCase(FREQUENTLY.name())) {
            return FREQUENTLY.points;
        }
        if (name.equalsIgnoreCase(ALWAYS.name())) {
            return ALWAYS.points;
        }
        return NOTHING.points;
    }
}
