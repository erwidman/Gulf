package com.teamgolf.golfdb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.security.*;

/**
 * Created by Eric on 9/26/2016.
 */

public class DatabaseHandler {

    DatabaseHelper dbHelper = null;
    Context context;


    public DatabaseHandler(Context context) {
        //init dbHelper load file
        this.context = context;
        dbHelper = new DatabaseHelper(context);



        /* Test CODE
        SQLiteDatabase db = dbHelper.connectDB();
        Log.d("TEST",Integer.toString(db.rawQuery("Select * from player;",null).getCount()));
        db.close();
        */


    }

    //player table
    //______________________________________________________________________________________________________________________________________________________
    public boolean insertUser(String userid, String password){


        SQLiteDatabase db = dbHelper.connectDB();
        //check if user is already in system
        Cursor c = db.rawQuery("Select * from player where userid = '"+userid+"';",null);
        int pass = c.getCount();
        Log.d("INSERT_USER:USER_EXIST?",Integer.toString(pass));

        //TODO
        String encrypt = password;

        if(pass==0) {
            Log.d("BEFORE_INSERT",Integer.toString(db.rawQuery("Select * from player;",null).getCount()));
            //attempt insert
            db.beginTransaction();
            db.execSQL("insert into player values(?,?);",new String [] {userid,encrypt});
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
            //test
            db = dbHelper.connectDB();
            Log.d("AFTER_INSERT",Integer.toString(db.rawQuery("Select * from player;",null).getCount()));
            return true;
        }

        db.close();
        return false;
    }


    public boolean checkPassword(String user, String password){

        SQLiteDatabase db = dbHelper.connectDB();
        //perform querey
        Cursor c = db.query(true,"player",new String[]{"password"}, "userid = ? ",new String[]{user},null,null,null,"1");
        c.moveToFirst();
        String result = c.getString(c.getColumnIndex("password"));
        c.close();

        Log.d("CHECK_PASSWORD","Resulting query: "+ result);

        //compare result
        if (encryptCompare(password,result))
            return true;

        return false;
    }


    //______________________________________________________________________________________________________________________________________________________________

    //TODO
    public String passwordEncryption(String pass){
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(pass.getBytes());
            byte [] digest = md.digest();
            result = new String(digest);


        }catch(NoSuchAlgorithmException e){
            Log.d("ENCRYPTION","MD5 is not a valid algorithm");
        }

        Log.d("ENCRYPTION",result);

        return result;
    }

    private boolean encryptCompare(String input, String fetch){

        String s1 = passwordEncryption(input);

        if(s1.equals(fetch))
            return true;

        return false;
    }
}
