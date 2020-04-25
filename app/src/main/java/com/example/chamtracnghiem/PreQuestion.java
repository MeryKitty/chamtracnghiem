package com.example.chamtracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.chamtracnghiem.model.Question;

public class PreQuestion extends AppCompatActivity {
    Spinner chooseNbQuestions;
    Button next;
    boolean buttonClickable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pre_question);

        next = findViewById(R.id.next);
        chooseNbQuestions = findViewById(R.id.chooseNbQuestion);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nbQuestions,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        chooseNbQuestions.setAdapter(adapter);
        chooseNbQuestions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                buttonClickable = true;
                Question.nbQuestion = Integer.parseInt(parent.getItemAtPosition(position).toString());
                next.setBackgroundColor(getResources().getColor(R.color.colorPrimary,null));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                buttonClickable = false;
                next.setBackgroundColor(Color.GRAY);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonClickable){
                    Intent intent = new Intent(PreQuestion.this, QuestionActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
