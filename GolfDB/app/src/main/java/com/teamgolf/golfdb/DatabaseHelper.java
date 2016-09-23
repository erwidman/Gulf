package com.teamgolf.golfdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eric on 9/23/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String dbPath ="/data/data/com.teamgolf.golfdb/assets/databases/";
    public static final String dbName ="golf.db";
    public static final int dbVersion = 1;
    private Context context = null;
    private SQLiteDatabase db;

    public DatabaseHelper(Context context){
        super(context,dbName,null,dbVersion);
        this.context = context;
    }

    public SQLiteDatabase getDB(){
        try{
            String path = dbPath + dbName;
            this.db = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        }catch(SQLiteException e){
            throw new Error("Improper Path");
        }
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
