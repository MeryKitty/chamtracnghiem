package com.example.chamtracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.chamtracnghiem.model.AnswerKey;
import com.example.chamtracnghiem.model.Question;
import com.example.chamtracnghiem.model.QuestionAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewQuestionActivity extends AppCompatActivity {
    private LinearLayout questionsScrollView;
    Button submit;
    private Map<Integer,RadioGroup> RGList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_question);

        questionsScrollView = findViewById(R.id.questionScrollView);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainMenu();
            }
        });

        final Map<Integer,String> tempMap = new HashMap<>();
        tempMap.put(1,"A");
        tempMap.put(2,"B");
        tempMap.put(3,"C");
        tempMap.put(4,"D");
//        RGList = new HashMap<>();
//        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        for (int i = 1; i <= Question.nbQuestion; i++) {
//            RadioGroup tempRG = new RadioGroup(this);
//            RadioGroup.LayoutParams tempRGParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT,RadioGroup.LayoutParams.WRAP_CONTENT);
//            tempRG.setLayoutParams(tempRGParams);
//            tempRG.setOrientation(RadioGroup.HORIZONTAL);
//            TextView tempTV = new TextView(this);
//            ViewGroup.LayoutParams tempTVParams = new ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
//            tempTV.setLayoutParams(tempTVParams);
//            tempTV.setLayoutDirection();
//            tempTV.setText(Integer.toString(i));
//            tempRG.addView(tempTV);
//            RGList.put(i,tempRG);
//            for (int j = 1; j <= 4; j++){
//                RadioButton tempRB = new RadioButton(this);
//                LinearLayout.LayoutParams tempRBParams = new LinearLayout.LayoutParams(85,LinearLayout.LayoutParams.WRAP_CONTENT);
//                tempRB.setLayoutParams(tempRBParams);
//                tempRB.setText(tempMap.get(j));
//                tempRG.addView(tempRB);
//            }
//            questionsScrollView.addView(tempRG);
//            questionsScrollView.animate();
//        }
        /*QuestionAdapter adapter = new QuestionAdapter(this, R.layout.question, questionData);
        questionListView = (ListView)findViewById(R.id.questionActContent);
        questionListView.setAdapter(adapter);*/
        RadioGroup group1 = findViewById(R.id.question1);
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkRadio(1, checkedId, tempMap);
            }
        });

        RadioGroup group2 = findViewById(R.id.question2);
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkRadio(2, checkedId, tempMap);
            }
        });

        RadioGroup group3 = findViewById(R.id.question3);
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkRadio(3, checkedId, tempMap);
            }
        });

        RadioGroup group4 = findViewById(R.id.question4);
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkRadio(4, checkedId, tempMap);
            }
        });

        RadioGroup group5 = findViewById(R.id.question5);
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkRadio(5, checkedId, tempMap);
            }
        });
    }

    private void checkRadio(int i, int checkedId, Map<Integer, String> tempMap) {
        AnswerKey.getKey().put(Integer.toString(i), tempMap.get(checkedId));
    }

    private void returnToMainMenu(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
