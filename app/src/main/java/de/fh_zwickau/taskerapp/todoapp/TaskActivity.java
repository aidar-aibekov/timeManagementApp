package de.fh_zwickau.taskerapp.todoapp;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import de.fh_zwickau.taskerapp.R;
import de.fh_zwickau.taskerapp.todoapp.db.TaskContract;
import de.fh_zwickau.taskerapp.todoapp.db.TaskUpdateService;
import de.fh_zwickau.taskerapp.todoapp.view.DatePickerFragment;

public class TaskActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener,
        View.OnClickListener {

    private long deadline = Long.MAX_VALUE;
    private TextInputEditText descriptionView;
    private SwitchCompat prioritySelect;
    private TextView deadlineView;
    private String date;
    private static final String KEY_DATE = "date_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);

        descriptionView = findViewById(R.id.text_input_description);
        prioritySelect = findViewById(R.id.switch_priority);
        deadlineView = findViewById(R.id.text_date);
        View mSelectDate = findViewById(R.id.select_date);

        mSelectDate.setOnClickListener(this);
        updateDateDisplay();

        if (savedInstanceState != null) {
            String savedDate = savedInstanceState.getString(KEY_DATE);
            deadlineView.setText(savedDate);
            date = savedDate;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(KEY_DATE, deadlineView.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_new_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            saveItem();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setDateSelection(long selectedTimestamp) {
        deadline = selectedTimestamp;
        updateDateDisplay();
    }

    public long getDateSelection() {
        return deadline;
    }

    @Override
    public void onClick(View v) {
        DatePickerFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
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

    private void updateDateDisplay() {
        if (getDateSelection() == Long.MAX_VALUE) {
            deadlineView.setText(R.string.date_empty);
        } else {
            CharSequence formatted = DateUtils.getRelativeTimeSpanString(this, deadline);
            deadlineView.setText(formatted);
        }
    }

    private void saveItem() {
        ContentValues values = new ContentValues(4);
        values.put(TaskContract.TaskColumns.DESCRIPTION, descriptionView.getText().toString());
        values.put(TaskContract.TaskColumns.IS_PRIORITY, prioritySelect.isChecked() ? 1 : 0);
        values.put(TaskContract.TaskColumns.IS_COMPLETE, 0);
        values.put(TaskContract.TaskColumns.DEADLINE, getDateSelection());

        TaskUpdateService.insertNewTask(this, values);
        finish();
    }
}
