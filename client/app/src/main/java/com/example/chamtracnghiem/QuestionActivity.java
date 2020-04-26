package com.example.chamtracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_question);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainMenu();
            }
        });

        List<Question> questionData = new ArrayList<>();
        for (int i = 1; i <= Question.nbQuestion; i++) {

            questionData.add(new Question(i));
        }
        QuestionAdapter adapter = new QuestionAdapter(this, R.layout.question, questionData);
        questionListView = (ListView)findViewById(R.id.questionActContent);
        questionListView.setAdapter(adapter);
    }

    private void returnToMainMenu(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
