package com.teamgolf.golfdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Login extends AppCompatActivity {

    SQLiteDatabase db =null;
    DatabaseHelper dbHelper = null;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initializations
        context = this.getApplicationContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getDB();
        Log.e("DB","DB Loaded!");



    }
}
