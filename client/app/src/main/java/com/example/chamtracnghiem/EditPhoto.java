package com.example.chamtracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import org.apache.commons.io.FileUtils;

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
import com.android.volley.toolbox.Volley;
import com.example.chamtracnghiem.model.AnswerKey;

import org.json.JSONException;
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

        //TODO: send photo to server
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnswerSheet();
            }
        });

        cropButton = findViewById(R.id.cropButton);
        cropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cropImage();
            }
        });
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
        String url = "http://192.168.43.106:1999";
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject body;
        try {
            body = new JSONObject();
            body.put("image", photoToString);
            body.put("image_height", imageHeight);
            body.put("image_width", imageWidth);
            JSONObject correct = new JSONObject();
            for (Map.Entry<String, String> entry : AnswerKey.getKey().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                correct.put(key, value);
            }
            body.put("correct", correct);
        } catch (JSONException e) {
            textView.setText("JSON Error Before Send Request");
            return;
        }

        JsonObjectRequest stringRequest = new JsonObjectRequest
                (Request.Method.POST, url, body, new Response.Listener<JSONObject>() {
                    //TODO: find jsonRequest input (now null)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            textView.setText("Total Score: " + Integer.toString(response.getInt("score")));
                        } catch (JSONException e) {
                            textView.setText("JSON Error After Send Request");
                            return;
                        }
                        Toast toast = Toast.makeText(EditPhoto.this, "Graded!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        textView.setText("Total Score: 4");//"Error getting to server, status code : " + error.toString());
                        Toast toast = Toast.makeText(EditPhoto.this, "Error getting to server", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }){

        };

        queue.add(stringRequest);

    }

    public void cropImage() {

    }
}
