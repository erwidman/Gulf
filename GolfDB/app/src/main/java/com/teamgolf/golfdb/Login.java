package com.teamgolf.golfdb;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class Login extends AppCompatActivity {

    SQLiteDatabase db =null;
    DatabaseHelper dbHelper = null;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        //create login view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //initializations
        context = this.getApplicationContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        dbHelper.connectDB();
        Log.d("TEST_RUN","DB Loaded!");

    }



    public boolean checkPassword(String user, String Password, SQLiteDatabase db){

        return false;
    }


}
