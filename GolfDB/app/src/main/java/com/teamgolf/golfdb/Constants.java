package com.teamgolf.golfdb;

import android.content.Context;

/**
 * Created by fred on 10/10/16.
 */

public class Constants {

    public static DatabaseHandler dbHandler;
    public static Context context;
    public static String user = null;
    public static String[][] holeLoaded;

    public Constants(Context context){
        this.context = context;
        dbHandler = new DatabaseHandler(context);
    }
}
