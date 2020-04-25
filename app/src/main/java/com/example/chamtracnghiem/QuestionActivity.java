package com.example.chamtracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.chamtracnghiem.model.Question;
import com.example.chamtracnghiem.model.QuestionAdapter;

import org.json.JSONObject;

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
                sendAnswerKey();
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

    public void sendAnswerKey(){
        String url = "localhost:1999";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url, null, new Response.Listener<JSONObject>() {

                    //TODO: find jsonRequest input (now null)
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast toast = Toast.makeText(QuestionActivity.this, "Something something", Toast.LENGTH_SHORT);
                        toast.show();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast toast = Toast.makeText(QuestionActivity.this, "Error getting to server", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

        queue.add(jsonObjectRequest);

    }
}
