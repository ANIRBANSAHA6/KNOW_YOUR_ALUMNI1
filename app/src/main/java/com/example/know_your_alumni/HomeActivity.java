package com.example.know_your_alumni;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.know_your_alumni.PostAdapter;
import com.example.know_your_alumni.R;
import com.example.know_your_alumni.post;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<post> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Your home layout

        recyclerView = findViewById(R.id.recycler_view); // Assume you have a RecyclerView in home.xml
        //Button follow = findViewById(R.id.follow_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list for posts
        postList = new ArrayList<>();

        // Receive data from PostActivity
        Intent intent = getIntent();
        if (intent != null) {
            String postCaption = intent.getStringExtra("keyname"); // Get the caption passed from PostActivity
            if (postCaption != null) {
                // Add the post to the list
                postList.add(new post("UserName", postCaption)); // Replace "UserName" with the actual user name if needed
            }
        }

        // Load more dummy posts (or real posts if applicable)
        loadPosts();

        // Set up the adapter with the updated post list
        postAdapter = new PostAdapter(this, postList);
        recyclerView.setAdapter(postAdapter);


    }

    private void loadPosts() {
        // Dummy posts to populate RecyclerView along with user post from PostActivity
        postList.add(new post("John Doe", "This is a sample post caption."));
        postList.add(new post("Jane Smith", "Hello, world! This is my post."));
        postList.add(new post("User123", "Loving the new app interface!"));
        // Posts from PostActivity are added in the onCreate method
    }
}
