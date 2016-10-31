package com.teamgolf.golfdb;

import android.content.Context;

/**
 * Created by fred on 10/10/16.
 */

public class Constants {

    public static DatabaseHandler dbHandler;
    public static Context context;
    public static String user = null;
<<<<<<< HEAD
    public static int  numPlayers;
    public static boolean isAdvanced;
    public static String p2=null;
    public static String p3=null;
    public static String p4 = null;
=======
    public static String[][] holeLoaded;
>>>>>>> ba7ce83d19adb9e83450e4390d0fa8666e23fac4

    public Constants(Context context){
        this.context = context;
        dbHandler = new DatabaseHandler(context);
    }
}
