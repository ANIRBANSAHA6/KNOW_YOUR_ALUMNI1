package com.example.know_your_alumni;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name,@Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name,factory,version);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table users(college_id text, password text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void register(String college_Id, String password){
        ContentValues cv1 = new ContentValues();
        cv1.put("college_id",college_Id);
        cv1.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv1);
        db.close();


    }
}