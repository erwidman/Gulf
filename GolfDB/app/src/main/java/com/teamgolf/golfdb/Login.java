package com.teamgolf.golfdb;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class Login extends AppCompatActivity {

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

    }



    public boolean checkPassword(String user, String password){

        //perform querey
        SQLiteDatabase db = dbHelper.connectDB();
        Cursor c = db.query(true,"player",new String[]{"password"}, "userid =? ",new String[]{user},null,null,null,"1");
        c.moveToFirst();
        String result = c.getString(c.getColumnIndex("password"));
        c.close();
        db.close();

        Log.d("CHECK_PASSWORD","Resulting query: "+ result);

        //compare result
        if (encryptCompare(password,result))
            return true;

        return false;
    }


    //// TODO: 9/26/2016
    private boolean encryptCompare(String input, String fetch){

        return false;
    }


}
