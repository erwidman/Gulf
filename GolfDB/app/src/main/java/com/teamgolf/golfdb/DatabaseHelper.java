package com.teamgolf.golfdb;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Eric on 9/23/2016.
 * Class used to load exterior assets as well as connect to db
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String dbPath ="data/data/com.teamgolf.golfdb/";
    public static final String dbName ="golf.db";
    public static final int dbVersion = 1;
    private Context context = null;
    private SQLiteDatabase db;
    private String path = dbPath+ dbName;

    public DatabaseHelper(Context context){
        super(context,dbName,null,dbVersion);
        this.context = context;
        load_DB_From_APK();
    }

    /**
     *
     * @return A Database object for golf.db
     */
    public SQLiteDatabase connectDB(){

        //close db if already connected
        if(db != null && db.isOpen())
            db.close();


        try{
            this.db = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        }catch(SQLiteException e){
            throw new Error("Improper Path");
        }

        db.execSQL("pragma foreign_keys= on;");

        return db;
    }



    /**
     * Loads Assets from apk into a file in working directory
     */
    private void load_DB_From_APK(){
        //create file
           if(!new File(path).exists()) {
               try {
                   FileOutputStream out = new FileOutputStream(path);
                   InputStream in = context.getAssets().open(dbName);
                   byte[] buffer = new byte[1024];
                   int readBytes = 0;

                   while ((readBytes = in.read(buffer)) != -1) {
                       out.write(buffer, 0, readBytes);
                   }
                   in.close();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
