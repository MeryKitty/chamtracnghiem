package com.example.chamtracnghiem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.File;

public class EditPhoto extends AppCompatActivity {
    Button backButton;
    Button cropButton;
    Button sendButton;
    TextView textView;
    ImageView previewPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);
        textView = findViewById(R.id.score);
        previewPhoto = findViewById(R.id.preview_photo);
        previewPhoto.setImageBitmap(BitmapFactory.decodeFile(getExternalFilesDir( Environment.DIRECTORY_PICTURES)+"/temp_pic.jpg"));
        //previewPhoto.invalidate();

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoScreen();
            }
        });

        sendButton = findViewById(R.id.sendButton);
        //TODO: send photo to server
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnswerSheet();
            }
        });

        cropButton = findViewById(R.id.cropButton);
    }

    public void openPhotoScreen(){
        Intent intent = new Intent(this,OpenCamera.class);
        startActivity(intent);
    }

    public void sendAnswerSheet(){
        String url = "localhost:1999";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url, null, new Response.Listener<JSONObject>() {

                    //TODO: find jsonRequest input (now null)
                    @Override
                    public void onResponse(JSONObject response) {
                        textView.setText("Something something");
                        Toast toast = Toast.makeText(EditPhoto.this, "Graded!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        textView.setText("Error getting to server");
                        Toast toast = Toast.makeText(EditPhoto.this, "Error getting to server", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

        queue.add(jsonObjectRequest);

    }
}
