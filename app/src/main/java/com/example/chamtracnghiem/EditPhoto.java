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

import java.io.File;

public class EditPhoto extends AppCompatActivity {
    Button backButton;
    Button cropButton;
    Button sendButton;
    ImageView previewPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);
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

        cropButton = findViewById(R.id.cropButton);
    }

    public void openPhotoScreen(){
        Intent intent = new Intent(this,OpenCamera.class);
        startActivity(intent);
    }
}
