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

    }

    //player table
    //______________________________________________________________________________________________________________________________________________________

    /**
     * Method used to input new users
     * @param userid
     * @param password
     * @return True if user is suceesfully loaded to db
     */
    public boolean insertUser(String userid, String password){


        SQLiteDatabase db = dbHelper.connectDB();
        //check if user is already in system
        Cursor c = db.rawQuery("Select * from player where userid = '"+userid+"';",null);
        int pass = c.getCount();
        Log.d("INSERT_USER:USER_EXIST?",Integer.toString(pass));

        
        //TODO
        int userLength = 0;
        for (char ch: userid.toCharArray()) {
            userLength+=1;
        }
        if (userLength > 31){
            // TODO: 10/5/2016  Need to print error message to user
            db.close();
        }

        int passLength = 0;
        for (char ch: password.toCharArray()) {
            passLength+=1;
        }
        if (passLength > 31){
            // TODO: 10/5/2016  Need to print error message to user
            db.close();
        }

        String encrypt = passwordEncryption(password);

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

    /**
     * Method used to check users password
     * @param user
     * @param password
     * @return True if input is the correct password
     */
    public boolean checkPassword(String user, String password){

        SQLiteDatabase db = dbHelper.connectDB();
        //perform querey
        Cursor c = db.query(true,"player",new String[]{"password"}, "userid = ? ",new String[]{user},null,null,null,"1");
        c.moveToFirst();

        String result;
        if(c.getCount()!=0) {
            result = c.getString(c.getColumnIndex("password"));

            c.close();
            db.close();

            Log.d("CHECK_PASSWORD", "Resulting query: " + result);

            //compare result
            return encryptCompare(password, result);
        }
        return false;
    }

    //

    //log table
    //__________________________________________________________________________________________________________________________________

    //course table
    //_________________________________________________________________________________________________________________________________

    //helper methods
    //______________________________________________________________________________________________________________________________________________________________

    private String passwordEncryption(String pass){
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
        Log.d("COMPARE_PASS",s1);
        Log.d("COMPARE_PASS",fetch);

        if(s1.equals(fetch))
            return true;

        return false;
    }
}
