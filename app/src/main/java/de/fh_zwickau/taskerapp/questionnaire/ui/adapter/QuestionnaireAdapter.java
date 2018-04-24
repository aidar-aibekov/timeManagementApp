package de.fh_zwickau.taskerapp.questionnaire.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import de.fh_zwickau.taskerapp.R;
import de.fh_zwickau.taskerapp.questionnaire.model.Question;
import de.fh_zwickau.taskerapp.questionnaire.model.Questionnaire;
import de.fh_zwickau.taskerapp.questionnaire.ui.QuestionLayout;

public class QuestionnaireAdapter extends RecyclerView.Adapter {

    public static final String EXTRA_QUESTIONNAIRE_ID = "QUESTOINNAIRE_ID";

    private List<Questionnaire> questionnaires;
    private Context context;

    public QuestionnaireAdapter(final List<Questionnaire> questionnaires, Context context) {
        this.questionnaires = questionnaires;
        this.context = context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button button;
        public ViewHolder(View v) {
            super(v);
            button = v.findViewById(R.id.questionnaire_name_button);
        }

        public void bindData(final Questionnaire questionnaire) {
            button.setText(questionnaire.getId() + " " + questionnaire.getName());
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
        Questionnaire questionnaire = questionnaires.get(position);
        ((ViewHolder)holder).bindData(questionnaire);

        ((ViewHolder)holder).button.setOnClickListener(view -> {
            Intent intent = new Intent(context, QuestionLayout.class);
            intent.putExtra(EXTRA_QUESTIONNAIRE_ID, questionnaire.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return questionnaires.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_questionnaire_itemview;
    }
}
