package com.teamgolf.golfdb;

import android.content.Context;

/**
 * Created by fred on 10/10/16.
 */

/**
 * Class containing all static variables used
 */
public class Constants {

    public static DatabaseHandler dbHandler;
    public static Context context;
    public static String user = null;
    public static int  numPlayers;
    public static boolean isAdvanced;
    public static String p2=null;
    public static String p3=null;
    public static String p4 = null;
    public static String[][] holeLoaded;
    public static String courseNamePickedForStats;
    public static String courseLocationPickedForStats;
    public static int [][] scoreForStats;
    public static int [][] courseInfoForStats;

    public Constants(Context context){
        this.context = context;
        dbHandler = new DatabaseHandler(context);
    }
}
