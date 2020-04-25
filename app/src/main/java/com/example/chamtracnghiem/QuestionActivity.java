package com.example.chamtracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.chamtracnghiem.model.Question;
import com.example.chamtracnghiem.model.QuestionAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    private ListView questionListView;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        submit = (Button) findViewById(R.id.submit);
        List<Question> questionData = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            questionData.add(new Question(i));
        }
        QuestionAdapter adapter = new QuestionAdapter(this, android.R.layout.question, questionData);
        questionListView = (ListView)findViewById(R.id.questionActContent);
        questionListView.setAdapter(adapter);
    }
}
