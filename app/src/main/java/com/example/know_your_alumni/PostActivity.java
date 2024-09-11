package com.example.know_your_alumni;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;


public class PostActivity extends AppCompatActivity {

    private EditText captionEditText; // The EditText where the user writes the post
    private Button uploadButton; // The button to upload the post

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post); // Set the layout to the one you provided

        // Initialize the UI components
        captionEditText = findViewById(R.id.Caption_edittext); // Get the caption EditText
        uploadButton = findViewById(R.id.upload_btn); // Get the upload button

        // Set an OnClickListener for the upload button
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text the user entered in the caption EditText
                String postCaption = captionEditText.getText().toString().trim();

                if (!postCaption.isEmpty()) {
                    // Create an intent to start HomeActivity
                    Intent intent = new Intent(PostActivity.this,HomeActivity.class);

                    // Put the caption in the intent to pass it to HomeActivity
                    intent.putExtra("keyname", postCaption);

                    // Start HomeActivity
                    startActivity(intent);
                } else {
                    // If the caption is empty, show a message to the user
                    captionEditText.setError("Please enter a caption");
                }
            }
        });
    }
}

