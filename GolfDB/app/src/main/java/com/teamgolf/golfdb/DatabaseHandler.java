package com.teamgolf.golfdb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Eric on 9/26/2016.
 */

public class DatabaseHandler {

    DatabaseHelper dbHelper = null;
    Context context;

    public DatabaseHandler(Context context){
        //init dbHelper load file
        this.context = context;
        dbHelper = new DatabaseHelper(context);

    }

    public boolean insertUser(String userid, String password){

        SQLiteDatabase db = dbHelper.connectDB();

        //check if user is already in system
        Cursor c = db.query(true,"player",new String[] {userid},"userid = ?",new String[] {userid},null,null,null,null);
        int pass = c.getCount();

        if(pass>0) {
            //attempt insert
            db.beginTransaction();
            db.execSQL("insert into table values (? , ? );", new String[]{userid, password});
            db.endTransaction();
            return true;
        }
        return false;
    }


    public boolean checkPassword(String user, String password){

        //perform querey
        SQLiteDatabase db = dbHelper.connectDB();
        db.beginTransaction();
        Cursor c = db.query(true,"player",new String[]{"password"}, "userid =? ",new String[]{user},null,null,null,"1");
        db.endTransaction();
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
