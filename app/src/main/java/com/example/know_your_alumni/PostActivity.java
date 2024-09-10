package com.example.know_your_alumni;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_LINK_REQUEST = 2; // For adding a link
    private static final int PICK_TAG_REQUEST = 3;  // For tagging people

    private EditText et_Text, et_poll_question;
    private ImageView imagePreview, img_btn, tag_btn, poll_btn, link_btn;
    private Button upload_btn;
    private Uri imageURI;
    private ArrayList<String> tagsList = new ArrayList<>();
    private String pollQuestion = "";
    private String attachedLink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post);

        et_Text = findViewById(R.id.Caption_edittext);
        //et_poll_question = findViewById(R.id.Poll_edittext); // Assume an EditText for poll
        imagePreview = findViewById(R.id.img_preveiew);
        img_btn = findViewById(R.id.img_video_btn);
        tag_btn = findViewById(R.id.tag_btn);
        poll_btn = findViewById(R.id.polling_btn);
        link_btn = findViewById(R.id.link_btn);
        upload_btn = findViewById(R.id.upload_btn);

        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        poll_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPoll();
            }
        });

        tag_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tagPeople();
            }
        });

        link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLink();
            }
        });

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPost();
            }
        });

    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void createPoll() {
        pollQuestion = et_poll_question.getText().toString();
        if (TextUtils.isEmpty(pollQuestion)) {
            Toast.makeText(this, "Please enter a poll question", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Poll added: " + pollQuestion, Toast.LENGTH_SHORT).show();
            // Optionally you can add more logic here for creating poll options
        }
    }

    private void tagPeople() {
        Intent tagIntent = new Intent(this, TagActivity.class); // Create an activity to select tags
        startActivityForResult(tagIntent, PICK_TAG_REQUEST);
    }

    private void addLink() {
        Intent linkIntent = new Intent(this, LinkActivity.class); // Create an activity to input a link
        startActivityForResult(linkIntent, PICK_LINK_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageURI = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageURI);
                imagePreview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_TAG_REQUEST && resultCode == RESULT_OK && data != null) {
            tagsList = data.getStringArrayListExtra("tagsList");
            if (tagsList != null && !tagsList.isEmpty()) {
                Toast.makeText(this, "Tags added: " + tagsList, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No tags added", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PICK_LINK_REQUEST && resultCode == RESULT_OK && data != null) {
            attachedLink = data.getStringExtra("link");
            if (!TextUtils.isEmpty(attachedLink)) {
                Toast.makeText(this, "Link added: " + attachedLink, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No link added", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Method to upload the post (caption + image + poll + tags + link)
    private void uploadPost() {
        String caption = et_Text.getText().toString();

        if (caption.isEmpty()) {
            Toast.makeText(this, "Please write something in the caption", Toast.LENGTH_SHORT).show();
            return;
        }


        // Here is where you can implement the logic to upload the post
        // Example: Use Firebase or another backend system to store image, caption, poll, tags, etc.

        Toast.makeText(this, "Post uploaded successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PostActivity.this, HomeActivity.class);
        startActivity(intent);

        // Reset fields after upload
        et_Text.setText("");
        et_poll_question.setText("");
        imagePreview.setImageResource(R.drawable.applogo); // Replace with a placeholder image
        tagsList.clear();
        attachedLink = "";
        pollQuestion = "";
    }
}
