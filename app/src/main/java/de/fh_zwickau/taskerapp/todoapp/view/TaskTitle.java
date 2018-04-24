package de.fh_zwickau.taskerapp.todoapp.view;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import de.fh_zwickau.taskerapp.R;


public class TaskTitle extends android.support.v7.widget.AppCompatTextView {
    public static final int NORMAL = 0;
    public static final int DONE = 1;
    public static final int OVERDUE = 2;
    private int state;

    public TaskTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TaskTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TaskTitle(Context context) {
        super(context);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        switch (state) {
            case DONE:
                setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                break;
            case NORMAL:
                setPaintFlags(0);
                setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                break;
            case OVERDUE:
                setPaintFlags(0);
                setTextColor(ContextCompat.getColor(getContext(), R.color.red));
                break;
            default:
                return;
        }
        this.state = state;
    }
}
