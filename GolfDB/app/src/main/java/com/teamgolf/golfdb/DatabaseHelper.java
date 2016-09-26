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
    }

    public SQLiteDatabase connectDB(){
        //create file
        if(! new File(path).exists()){
            try{
                FileOutputStream out = new FileOutputStream(path);
                InputStream in =context.getAssets().open(dbName);
                byte [] buffer = new byte[1024];
                int readBytes = 0;

                while((readBytes = in.read(buffer))!= -1){
                    out.write(buffer,0,readBytes);
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //clos db if already connected
        if(db != null && db.isOpen())
            db.close();



        try{
            this.db = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        }catch(SQLiteException e){
            throw new Error("Improper Path");
        }

        Cursor c = db.rawQuery("select * from courses",null);
        Log.d("TEST","Running Query");
        int tmp = c.getColumnCount();
        Log.d("TEST",Integer.toString(tmp));







        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
