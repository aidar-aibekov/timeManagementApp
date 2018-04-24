package de.fh_zwickau.taskerapp.todoapp;

import android.app.DatePickerDialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;

import de.fh_zwickau.taskerapp.R;
import de.fh_zwickau.taskerapp.todoapp.db.TaskContract;
import de.fh_zwickau.taskerapp.todoapp.view.DatePickerFragment;

public class TaskDetailActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, LoaderManager.LoaderCallbacks<Cursor> {

    private TextView taskDescr;
    private TextView taskDeadline;
    private String taskDescription;
    private int taskPriority;
    private long deadline = Long.MAX_VALUE;
    private ImageView mPriority;
    private Uri taskUri;
    private static final int TASK_DETAIL_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        taskUri = getIntent().getData();

        taskDescr = findViewById(R.id.text_description);
        taskDeadline = findViewById(R.id.due_date);
        mPriority = findViewById(R.id.priority);

        if (taskUri != null) {
            getLoaderManager().initLoader(TASK_DETAIL_LOADER, null, this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reminder:
                DatePickerFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "datePicker");
                return true;
            case R.id.action_delete:
                deleteTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteTask() {
        if (taskUri != null) {
            int rowsDeleted = getContentResolver().delete(taskUri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, getString(R.string.deleting_task_failed), Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, getString(R.string.deleting_task_successful), Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 12);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        setDateSelection(c.getTimeInMillis());
    }

    public void setDateSelection(long selectedTimestamp) {
        deadline = selectedTimestamp;
        long time = System.currentTimeMillis();
        if (deadline < time) {
            Toast.makeText(this, "Task alarm cannot be set to a past date", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(this, "Task alarm set ", Toast.LENGTH_SHORT).show();
        }
    }

    public long getDateSelection() {
        return deadline;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] mProjection = {
                    TaskContract.TaskColumns._ID,
                    TaskContract.TaskColumns.DESCRIPTION,
                    TaskContract.TaskColumns.IS_COMPLETE,
                    TaskContract.TaskColumns.IS_PRIORITY,
                    TaskContract.TaskColumns.DEADLINE
                };
        String mSelectionClause = null;
        String[] mSelectionArgs = null;
        String mSortOrder = null;

        return new CursorLoader(this,
                taskUri,
                mProjection,
                mSelectionClause,
                mSelectionArgs,
                mSortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        if (cursor.moveToFirst()) {
            int description = cursor.getColumnIndex(TaskContract.TaskColumns.DESCRIPTION);
            int deadline = cursor.getColumnIndex(TaskContract.TaskColumns.DEADLINE);
            int priority = cursor.getColumnIndex(TaskContract.TaskColumns.IS_PRIORITY);

            taskDescription = cursor.getString(description);
            this.deadline = cursor.getLong(deadline);
            taskPriority = cursor.getInt(priority);

            taskDescr.setText(taskDescription);
            if (taskPriority == 0) {
                mPriority.setImageResource(R.drawable.ic_not_priority);
            } else {
                mPriority.setImageResource(R.drawable.ic_priority);
            }

            if (getDateSelection() == Long.MAX_VALUE) {
                taskDeadline.setText("");
            } else {
                CharSequence formatted = DateUtils.getRelativeTimeSpanString(this, this.deadline);
                taskDeadline.setText("Deadline: " + formatted);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {}
}

