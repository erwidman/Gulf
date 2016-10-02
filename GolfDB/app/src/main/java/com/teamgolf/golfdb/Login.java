package com.teamgolf.golfdb;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

import static android.R.attr.id;


public class Login extends AppCompatActivity {

    DatabaseHandler dbHandler = null;
    Context context = null;

    //boolean noting if app is on first run of code at startup
    static Boolean firstStart = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //create login view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Run Once at start (initalizations)
        if(firstStart) {
            context = this.getApplicationContext();
            DatabaseHandler dbHandler = new DatabaseHandler(context);
            firstStart = false;
        }

    }

    protected void login(){
        EditText tmp = (EditText)findViewById(R.id.uLogin);
        String user = tmp.getText().toString().trim();

        tmp = (EditText)findViewById(R.id.uPassword);
        String password = tmp.getText().toString().trim();

        Log.d("TESTING_LOGIN",Boolean.toString(dbHandler.checkPassword(user,password)));
    }

    protected void onDestroy(){
        super.onDestroy();
        if(isFinishing()){
            firstStart = false;
        }
    }



}
