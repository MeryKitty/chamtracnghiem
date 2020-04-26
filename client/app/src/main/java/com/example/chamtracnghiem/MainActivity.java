package com.example.chamtracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button takePhotoButton;
    private Button makeAnswerkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        takePhotoButton = (Button) findViewById(R.id.takePhoto);
        takePhotoButton.setText(getResources().getText(R.string.takePhoto));
        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoScreen();
            }
        });

        makeAnswerkey = findViewById(R.id.taodapan);
        makeAnswerkey.setText(getResources().getText(R.string.make_answer_key));
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
