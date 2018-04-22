package de.fh_zwickau.taskerapp.questionnaire.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import de.fh_zwickau.taskerapp.R;
import de.fh_zwickau.taskerapp.questionnaire.model.Questionnaire;
import de.fh_zwickau.taskerapp.questionnaire.ui.QuestionnaireListActivity;

public class QuestionnaireAdapter extends RecyclerView.Adapter {

    private List<Questionnaire> questionnaires;

    public QuestionnaireAdapter(final List<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button button;
        public ViewHolder(View v) {
            super(v);
            button = v.findViewById(R.id.simple_text);
        }

        public void bindData(final Questionnaire questionnaire) {
            button.setText(questionnaire.getId() + " " + questionnaire.getName());

            button.setOnClickListener( e -> {
                Log.v("my tag", questionnaire.getId().toString());
            });
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        return new QuestionnaireAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bindData(questionnaires.get(position));
    }

    @Override
    public int getItemCount() {
        return questionnaires.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_simple_itemview;
    }
}
