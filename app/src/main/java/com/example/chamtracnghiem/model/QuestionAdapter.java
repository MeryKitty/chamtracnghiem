package com.example.chamtracnghiem.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chamtracnghiem.R;

import java.util.List;

public class QuestionAdapter extends ArrayAdapter<Question> {
    Context context;
    int layoutResourceId;
    List<Question> questions;

    public QuestionAdapter(Context context, int layoutResourceId, List<Question> questions) {
        super(context, layoutResourceId, questions);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.questions = questions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MatrixHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(this.layoutResourceId, parent, false);
            holder = new MatrixHolder();
            holder.id = (TextView) row.findViewById(R.id.questionId);
            holder.answers = (RadioGroup) row.findViewById(R.id.questionAnswers);
            holder.answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int pos = (int) group.getTag();
                    questions.get(pos).answer = group.getCheckedRadioButtonId();
                }
            });
            row.setTag(holder);
        } else {
            holder = (MatrixHolder) row.getTag();
        }

        Question question = this.questions.get(position);
        holder.id.setText(Integer.toString(question.id));
        holder.answers.setTag(position);
        if (question.answer != -1) {
            holder.answers.check(question.answer);
        }
        return row;
    }

    static class MatrixHolder {
        TextView id;
        RadioGroup answers;
        int position;
    }
}
