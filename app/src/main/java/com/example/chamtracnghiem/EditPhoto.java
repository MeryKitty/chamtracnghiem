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
import org.apache.commons.io.FileUtils;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chamtracnghiem.model.AnswerKey;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

public class EditPhoto extends AppCompatActivity {
    Button backButton;
    Button cropButton;
    Button sendButton;
    TextView textView;
    ImageView previewPhoto;
    String photoPath;
    String photoToString;  //to be sent to server
    int imageWidth;
    int imageHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_photo);
        photoPath = getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/temp_pic.jpg";

        textView = findViewById(R.id.score);
        previewPhoto = findViewById(R.id.preview_photo);
        Bitmap tempPhoto = BitmapFactory.decodeFile(photoPath);
        imageHeight = tempPhoto.getHeight();
        imageWidth = tempPhoto.getWidth();
        previewPhoto.setImageBitmap(tempPhoto);
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

    public void sendAnswerSheet() {
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(photoPath));
            photoToString = Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e){
            textView.setText("No file to send");
            return;
        }
        String url = "http://localhost:1999";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest
                (Request.Method.PUT, url, new Response.Listener<String>() {
                    //TODO: find jsonRequest input (now null)
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
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
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = AnswerKey.getKey();
                params.put("image",photoToString);
                params.put("image_width",Integer.toString(imageWidth));
                params.put("image_height",Integer.toString(imageHeight));
                return params;
            }
        };

        queue.add(stringRequest);

    }
}
