package de.fh_zwickau.taskerapp.todoapp.db;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import de.fh_zwickau.taskerapp.R;
import de.fh_zwickau.taskerapp.todoapp.view.TaskTitle;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
        void onItemToggled(boolean active, int position);
    }

    public class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TaskTitle nameView;
        public TextView dateView;
        public ImageView priorityView;
        public CheckBox checkBox;

        public TaskHolder(View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.text_description);
            dateView = itemView.findViewById(R.id.text_date);
            priorityView = itemView.findViewById(R.id.priority);
            checkBox = itemView.findViewById(R.id.checkbox);

            itemView.setOnClickListener(this);
            checkBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == checkBox)
                completionToggled(this);
            postItemClick(this);
        }
    }

    private Cursor mCursor;
    private OnItemClickListener mOnItemClickListener;
    private Context mContext;
    TaskTitle taskTitle;

    public TaskAdapter(Cursor cursor, Context context) {
        mCursor = cursor;
        this.mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    private void completionToggled(TaskHolder holder) {
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemToggled(holder.checkBox.isChecked(), holder.getAdapterPosition());
    }

    private void postItemClick(TaskHolder holder) {
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition());
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.activity_task_list, parent, false);

        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        final long NO_DATE = Long.MAX_VALUE;
        mCursor.moveToPosition(position);

        String mDescription = mCursor.getString(mCursor.getColumnIndex(TaskContract.TaskColumns.DESCRIPTION));
        long mDueDate = mCursor.getLong(mCursor.getColumnIndex(TaskContract.TaskColumns.DEADLINE));
        int mIsComplete = mCursor.getInt(mCursor.getColumnIndex(TaskContract.TaskColumns.IS_COMPLETE));
        int mIsPriority = mCursor.getInt(mCursor.getColumnIndex(TaskContract.TaskColumns.IS_PRIORITY));

        long time= System.currentTimeMillis();

        if (mDueDate != NO_DATE && time > mDueDate){
            holder.nameView.setState(2);
            holder.nameView.getState();
            holder.nameView.setText(mDescription);
        }else if (mIsComplete != 0 ){
            holder.nameView.setState(1);
            holder.nameView.getState();
            holder.nameView.setText(mDescription);
        }else{
            holder.nameView.setState(0);
            holder.nameView.getState();
            holder.nameView.setText(mDescription);
        }

        if (mIsPriority == 0)
            holder.priorityView.setImageResource(R.drawable.ic_not_priority);
        else
            holder.priorityView.setImageResource(R.drawable.ic_priority);

        Task task = new Task(mDescription, true, true, mDueDate);

        if (task.hasDeadline()){
            CharSequence formatted = DateUtils.getRelativeTimeSpanString(mContext, mDueDate);
            holder.dateView.setText(formatted);
        }
        else
            holder.dateView.setText("");
    }

    @Override
    public int getItemCount() {
        return (mCursor != null) ? mCursor.getCount() : 0;
    }

    public Task getItem(int position) {
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("Invalid item position requested");
        }

        return new Task(mCursor);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    public void swapCursor(Cursor cursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = cursor;
        notifyDataSetChanged();
    }
}
