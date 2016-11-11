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
    int currGame;
    String currName;
    String currLocation;


    public DatabaseHandler(Context context) {
        //init dbHelper load file
        this.context = context;
        dbHelper = new DatabaseHelper(context);

        //!!!!!!!!!!!!!!for testing
        //dbHelper.connectDB().execSQL("delete from player where 1 =1;");
      //  dbHelper.connectDB().execSQL("delete from courses where 1=1;");
     

        Cursor c = dbHelper.connectDB().rawQuery("Select * from hole;",null);

    }

    //hole table
    //__________________________________________________________________________________________________________________________________________________________
    public void insertHoles(String cName, String cLocation, String [] par, String menDis[], String womenDis[], String[] childDis){
        SQLiteDatabase db= dbHelper.connectDB();
        Log.d("TestingHoleInsert", "Start");
        db.beginTransaction();
        for(int i=0; i < par.length;i++){
            Log.d("TestingHoleInsert", par[i]);
            db.execSQL("insert into hole values(?,?,?,?,?,?,?,?)", new String [] {cName,cLocation,Integer.toString(i+1),par[i],childDis[i],womenDis[i],menDis[i],"0"});
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    //player table
    //____________________________________________________________________________________________________________________________________________________


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

        String encrypt = passwordEncryption(password);

        if(pass==0) {
            Log.d("BEFORE_INSERT",Integer.toString(db.rawQuery("Select * from player;",null).getCount()));
            //attempt insert
            db.beginTransaction();
            db.execSQL("insert into player values(?,?);",new String [] {userid,encrypt});
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
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
    public void insertScore(int [] [] score){
        SQLiteDatabase db = dbHelper.connectDB();

        Log.d("CurrName,CurLoc:",currName+ ","+currLocation);
        Log.d("Course in db: ",Integer.toString(db.rawQuery("Select * from courses where name = ? and location = ?;",new String[] {currName,currLocation}).getCount()));
        for(int i =0 ; i<score[0].length;i+=1){
            db.beginTransaction();
            db.execSQL("insert into log values(?, ?, ?, ?, ?, ?);", new String[] {Constants.user,Integer.toString(i+1),currName,currLocation,Integer.toString(score[0][i]),Integer.toString(this.currGame)});
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        db.close();
    }

    public void insertAdvancedScore(int [] advancedScore){
        SQLiteDatabase db = dbHelper.connectDB();
        Log.d("CurrName,CurLoc:",currName+ ","+currLocation);
        Log.d("Course in db: ",Integer.toString(db.rawQuery("Select * from courses where name = ? and location = ?;",new String[] {currName,currLocation}).getCount()));
        for(int i =0 ; i<advancedScore.length;i+=1){
            db.beginTransaction();
            db.execSQL("insert into advancedLog values(?, ?, ?, ?, ?, ?);", new String[] {Constants.user,Integer.toString(i+1),currName,currLocation,Integer.toString(advancedScore[i]),Integer.toString(this.currGame)});
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        db.close();
    }


    public int [] [] getScore(boolean advanced, String courseName,String courseLocation){
        Cursor c;
        SQLiteDatabase db = dbHelper.connectDB();

        if(!advanced)
            c = db.rawQuery("Select number, score, play_id from log where userid = ? and name = ? and location = ? order by number,play_id asc;",new String[] {Constants.user,courseName,courseLocation});
        else
            c = db.rawQuery("Select number, clubUsed ,play_id from advancedLog where userid = ? and name = ? and location ? order by number, play_id asc;", new String [] {Constants.user, courseName, courseLocation});
        int [] [] res = new int [c.getCount()][3];
        int row = 0;
        while(c.moveToNext()){
            res[row][0] = c.getInt(0);
            res[row][1] = c.getInt(1);
            res[row][2] = c.getInt(2);
            row++;
        }
        db.close();
        return res;
    }
    public int [] [] getScore(boolean advanced){
        Cursor c;
        SQLiteDatabase db = dbHelper.connectDB();

        if(!advanced)
            c = db.rawQuery("Select number, score, play_id from log where userid = ? order by number, play_id asc;",new String[] {Constants.user});
        else
            c = db.rawQuery("Select number,clubUsed,play_id from advancedLog where userid = ? order by number, play_id asc;",new String[] {Constants.user});
        int [] [] res = new int [c.getCount()][3];
        int row = 0;
        while(c.moveToNext()){
            res[row][0] = c.getInt(0);
            res[row][1] = c.getInt(1);
            res[row][2] = c.getInt(2);
            row++;
        }
        db.close();
        return res;
    }



    //course table
    //_________________________________________________________________________________________________________________________________


    public String[] getCourses(String input, Boolean isLocationSearch, Boolean isNameSearch){
        SQLiteDatabase db = dbHelper.connectDB();
        //perform query
        Cursor c;
        input = "%" + input + "%";

        if(isLocationSearch&&!isNameSearch)
            c = db.rawQuery("Select name,location from courses where location like ?;", new String[]{input});
        else if(isNameSearch&&!isLocationSearch)
            c = db.rawQuery("Select name,location from courses where name like ?;", new String[]{input});
        else
            c = db.rawQuery("Select name,location from courses where name like ? union select name, location from courses where location like ?;", new String[]{input,input});




        //if querey is succesful populate results
        int numResults = c.getCount();
        if(numResults>0)
        {
            String[] results = new String[numResults];
            String resString;
            int i = 0;
            while(c.moveToNext()){
                resString= c.getString(0)+":"+c.getString(1);
                results[i]=resString;
                i++;
            }
            db.close();
            c.close();
            return results;
        }

        db.close();
        c.close();
        return null;
    }

    public boolean insertCourse(String name, String state, String city, String difficulty, String numOfHoles){

        Log.d("Test",name.toLowerCase());
        String loc = state + ":" + city ;

        name = name.toLowerCase();
        loc = loc.toLowerCase();

        SQLiteDatabase db = dbHelper.connectDB();

        Cursor c = db.query("courses",new String[] {"name","courseDifficulty","numOfHoles"},"location = ? and name = ?",new String[] {loc,name},null,null,null,null);

        if(c.getCount() > 0)
            return false;

        db.beginTransaction();
        db.execSQL("insert into courses values(?,?, ?,?);",new String [] {name ,loc,difficulty,numOfHoles});
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        c.close();

        return true;
    }

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

        return s1.equals(fetch);

    }

    //________________________________________________________________________________________________________________----
    public boolean startGame(String courseName, String courseLoc) {

        SQLiteDatabase db = dbHelper.connectDB();
        //load curr game
        Cursor c = db.rawQuery("Select * from increment;",null);
        c.moveToFirst();
        this.currGame = c.getInt(0)+1;
        this.currLocation= courseLoc;
        this.currName = courseName;
        //increment in db
        db.beginTransaction();
        db.execSQL("Update increment set play = play + 1;");
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        c.close();
        return currGame!=0;

    }


    /**
     * Returns a 2-d array of info on holes for param golf course, array is 4 rows and # of
     * golf course cols (col 0 = hole 1). Row 0 is par, Row 1 is menDis, Row 2 is womenDis
     * row 3 = childDis
     *
     * @param name CourseName
     * @param location CourseLocation
     * @return Retur
     */
    public String[][] holeInfo(String name, String location){
        //// TODO: 24/10/16
        SQLiteDatabase db = dbHelper.connectDB();
        Cursor c = db.rawQuery("select par, menDis,womenDis,childDis from hole where name = ? and location = ? order by number asc;",new String[] {name,location});

        //iterate through and populate result array
        String [][] res = new String[4][c.getCount()];
        int i = 0;
        while(c.moveToNext()){
            Log.d("holeInfo:", c.getString(0));
            res[0][i]= c.getString(0); //par
            res[1][i]= c.getString(1); //mendis
            res[2][i]= c.getString(2); //womendis
            res[3][i]= c.getString(3); //childis
            i++;
        }
        db.close();
        c.close();
        startGame(name,location);
        return res;
    }
}
