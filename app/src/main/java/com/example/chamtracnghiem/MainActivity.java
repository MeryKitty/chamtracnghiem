package com.example.chamtracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button takePhotoButton;
    private Button makeAnswerkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        takePhotoButton = (Button) findViewById(R.id.takePhoto);
        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoScreen();
            }
        });

        makeAnswerkey = findViewById(R.id.taodapan);
        makeAnswerkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnswerKeyScreen();
            }
        });
    }

    public void openAnswerKeyScreen(){
        Intent intent = new Intent(this,PreQuestion.class);
        startActivity(intent);
    }

    public void openPhotoScreen(){
        Intent intent = new Intent(this,OpenCamera.class);
        startActivity(intent);
    }
}
