package com.example.know_your_alumni;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "college_details.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1 = "create table users(college_id text, password text)";
        sqLiteDatabase.execSQL(query1);

        String query2 = "create table posts(caption_text text, image_url text, poll_options text, tags_text text, link_text text)";
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Handle database upgrade
    }

    public void register(String college_Id, String password) {
        ContentValues cv1 = new ContentValues();
        cv1.put("college_id", college_Id);
        cv1.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv1);
        db.close();
    }

    public long post(String postText, String imageUrl, String pollOptions, String tagsText, String linkText) {
        ContentValues cv = new ContentValues();
        cv.put("post_text", postText != null ? postText : ""); // Ensure postText is not null
        cv.put("image_url", imageUrl != null ? imageUrl : ""); // Handle null imageUrl
        cv.put("poll_options", pollOptions != null ? pollOptions : ""); // Handle null pollOptions
        cv.put("tags_text", tagsText != null ? tagsText : ""); // Handle null tagsText
        cv.put("link_text", linkText != null ? linkText : ""); // Handle null linkText

        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert("posts", null, cv);
        db.close();

        // Optionally check for success
        if (result == -1) {
            Log.e("DatabaseHelper", "Failed to insert post");
        } else {
            Log.d("DatabaseHelper", "Post inserted successfully with ID: " + result);
        }
        return result;
    }




    public Cursor getAllPosts() {
        SQLiteDatabase db2 = this.getReadableDatabase();
        return db2.rawQuery("SELECT * FROM posts", null);
    }
}
