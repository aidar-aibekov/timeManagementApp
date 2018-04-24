package de.fh_zwickau.taskerapp.todoapp.db;

import android.database.Cursor;

import static de.fh_zwickau.taskerapp.todoapp.db.TaskContract.getColumnInteger;
import static de.fh_zwickau.taskerapp.todoapp.db.TaskContract.getColumnLong;
import static de.fh_zwickau.taskerapp.todoapp.db.TaskContract.getColumnString;


public class Task {
    private static final long NO_DEADLINE = 0;
    private static final long NO_ID = -1;

    private long id;
    private String description;
    private boolean isComplete;
    private boolean isPriority;
    private long deadline;

    public Task(String description, boolean isComplete, boolean isPriority, long deadline) {
        this.description = description;
        this.isComplete = isComplete;
        this.isPriority = isPriority;
        this.deadline = deadline;
    }

    public Task(String description, boolean isComplete, boolean isPriority) {
        this.description = description;
        this.isComplete = isComplete;
        this.isPriority = isPriority;
    }

    public Task(Cursor cursor) {
        this.id = getColumnLong(cursor, TaskContract.TaskColumns._ID);
        this.description = getColumnString(cursor, TaskContract.TaskColumns.DESCRIPTION);
        this.isComplete = getColumnInteger(cursor, TaskContract.TaskColumns.IS_COMPLETE) == 1;
        this.isPriority = getColumnInteger(cursor, TaskContract.TaskColumns.IS_PRIORITY) == 1;
        this.deadline = getColumnLong(cursor, TaskContract.TaskColumns.DEADLINE);
    }

    public boolean hasDeadline() {
        return this.deadline != 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }
}
