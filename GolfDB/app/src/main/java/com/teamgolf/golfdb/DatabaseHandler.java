package com.teamgolf.golfdb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.security.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Eric on 9/26/2016.
 */

/**
 * Class used to interface with db throughout the program
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

    }

    /**
     * Method used to get information on course recently played by currently signed in user
     * @return String array of 2 cols cotaining name and location of course user has played at
     */
    public String [] getRecentCourses(){
        Cursor c;
        SQLiteDatabase db = dbHelper.connectDB();
        c = db.rawQuery("Select distinct name, location from log where userid = ?;",new String [] {Constants.user});
        String [] res = new String [c.getCount()];
        int i = 0;
        while(c.moveToNext()){
            String input = c.getString(0)+":"+c.getString(1);
            res[i]= input;
            i++;
        }
        c.close();
        db.close();
        return res;
    }

    /**
     * Returns the rounds a user has played for a course select in the stats section of the program
     * @return String array of two cols conaing a play_id and date
     */
    public String [] getRounds(){
        Cursor c;
        SQLiteDatabase db = dbHelper.connectDB();
        c = db.rawQuery("Select  play_id, date from (Select distinct play_id from log where name = ? and location = ?) natural join date;", new String [] {Constants.courseNamePickedForStats,Constants.courseLocationPickedForStats});
        String [] res = new String [c.getCount()+1];
        res[0] = "Combined Stats";
        int i = 1;
        while(c.moveToNext()){
            String input = c.getString(0)+"-     Date: "+c.getString(1);
            res[i]= input;
            i++;
        }
        c.close();
        db.close();
        return res;
    }


    /**
     * Inserts a set of holes into db
     * @param cName Course Name
     * @param cLocation Location
     * @param par array of pars
     * @param menDis array of mens yardage
     * @param womenDis array of womens yardage
     * @param childDis array of childs yardage
     */
    public void insertHoles(String cName, String cLocation, String [] par, String menDis[], String womenDis[], String[] childDis){
        SQLiteDatabase db= dbHelper.connectDB();
        db.beginTransaction();
        for(int i=0; i < par.length;i++){
            db.execSQL("insert into hole values(?,?,?,?,?,?,?,?)", new String [] {cName,cLocation,Integer.toString(i+1),par[i],childDis[i],womenDis[i],menDis[i],"0"});
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

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
            //attempt insert
            db.beginTransaction();
            db.execSQL("insert into player values(?,?);",new String [] {userid,encrypt});
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
            return true;
        }
        c.close();
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

            //compare result
            return encryptCompare(password, result);
        }
        return false;
    }

    /**
     * Method used to insert scores recorded by player after game into db (upon hitting submit)
     * @param score 2d array of scores
     */
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

    /**
     * insert scores of the advanced type (club strokes) into db upon submission
     * @param advancedScore int [] of advanced scores
     */
    public void insertAdvancedScore(int [] advancedScore){
        SQLiteDatabase db = dbHelper.connectDB();
        Log.d("CurrName,CurLoc:",currName+ ","+currLocation);
        Log.d("Course in db: ",Integer.toString(db.rawQuery("Select * from courses where name = ? and location = ?;",new String[] {currName,currLocation}).getCount()));
        int currHole = 1;
        for(int i =0 ; i<advancedScore.length;i+=1) {
            if (advancedScore[i] == -1){
                currHole++;
                continue;
            }
            db.beginTransaction();
            db.execSQL("insert into advancedLog values(?, ?, ?, ?, ?, ?);", new String[] {Constants.user,Integer.toString(currHole),currName,currLocation,Integer.toString(advancedScore[i]),Integer.toString(this.currGame)});
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        db.close();
    }

    /**
     * Master method to extract information from log, and advancedlog tables in db
     * @param courseName name of course, null if not searching by name
     * @param courseLocation name of location of course, null if not searching by name
     * @param playID round number if searching by round
     * @param advanced boolean indicating a search in advancedlog table
     * @param allUserRecord boolean indicating to pull all stats from all courses for current user
     * @return
     */
    public int [] [] getScore(String courseName,String courseLocation, String playID,boolean advanced,boolean allUserRecord){
        Cursor c;
        SQLiteDatabase db = dbHelper.connectDB();

        if(allUserRecord)
            c= db.rawQuery("Select number,score,play_id from log where userid = ?;", new String [] {Constants.user});

        else if(playID!=null)
            c = db.rawQuery("Select number,score,play_id from log where play_id = ? order by number asc;", new String[]{playID});

        else if(courseName!=null){
            if(!advanced)
                c = db.rawQuery("Select number, score, play_id from log where userid = ? order by play_id asc;",new String[] {Constants.user});
            else
                c = db.rawQuery("Select number, clubUsed ,play_id from advancedLog where userid = ? order by play_id asc;", new String [] {Constants.user});
        }
        else {
            if (!advanced)
                c = db.rawQuery("Select number, score, play_id from log where userid = ? and name = ? and location = ? order by play_id asc;", new String[]{Constants.user, courseName, courseLocation});
            else
                c = db.rawQuery("Select number, clubUsed ,play_id from advancedLog where userid = ? and name = ? and location ? order by play_id asc;", new String[]{Constants.user, courseName, courseLocation});
        }

        int [] [] res = new int [c.getCount()][3];
        int row = 0;
        while(c.moveToNext()){
            res[row][0] = c.getInt(0);
            res[row][1] = c.getInt(1);
            res[row][2] = c.getInt(2);
            row++;
        }
        c.close();

        Cursor c2;

        if(courseName!=null)
            c2 = db.rawQuery("Select distinct play_id from log where userid = ? and name = ? and location =  ?;",new String[] {Constants.user,courseName,courseLocation});
        else
            c2 = db.rawQuery("Select distinct play_id from log where userid = ?;",new String[] {Constants.user});


        int currZ = 0;

        int rows = c2.getCount();

        //max rows
        if(playID!=null)
            rows = 1;

        //max cols
        int cols = 0;
        if(Constants.holeLoaded!=null)
            cols = Constants.holeLoaded[0].length;
        if(allUserRecord)
            cols = 27;

        //filter data such that rows display scores and column index represents hole number
        int [][] tmp = new int [rows] [cols];
        for(int i = 0 ; i<tmp.length;i++){
            int plyID = res[currZ][2];
            for(int j = 0; j<tmp[0].length;j++){
                if(currZ<res.length&&res[currZ][2]==plyID) {
                    tmp[i][j] = res[currZ][1];
                }
                else
                    break;
                currZ++;
            }
            if(currZ>res.length-1)
                break;
            plyID = res[currZ] [2];
        }

        return tmp;
    }


    /**
     * Method used to serach db for a course
     * @param input users input
     * @param isLocationSearch are they searching by location?
     * @param isNameSearch are they searching by name?
     * @return String array of resulting querey
     */
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

    /**
     * Method user to insert new courses into db
     * @param name Name of course
     * @param state state course is in
     * @param city city course is in
     * @param difficulty difficulty of course
     * @param numOfHoles number of holes on course
     * @return boolean indicating if insert was succesful
     */
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


    /**
     * Method used to encrpy password
     * @param pass input password
     * @return encrypted password
     */
    private String passwordEncryption(String pass){
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(pass.getBytes());
            byte [] digest = md.digest();
            result = new String(digest);


        }catch(NoSuchAlgorithmException e){

        }

        return result;
    }

    /**
     * method used to compare encrypted text
     * @param input
     * @param fetch
     * @return
     */
    private boolean encryptCompare(String input, String fetch){

        String s1 = passwordEncryption(input);
        Log.d("COMPARE_PASS",s1);
        Log.d("COMPARE_PASS",fetch);

        return s1.equals(fetch);

    }


    /**
     * Method used to setup conditions necesarry to begin recording and eventually submitting a game
     * @param courseName name of course
     * @param courseLoc location of course
     * @return boolean indicating if the game was started
     */
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

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
        db = dbHelper.connectDB();
        db.beginTransaction();
        db.execSQL("Insert into date values(?,?);", new String [] {Integer.toString(currGame),timeStamp});
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
        Cursor c;

        c = db.rawQuery("select par, menDis,womenDis,childDis from hole where name = ? and location = ? order by number asc;",new String[] {name,location});

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
        Constants.holeLoaded= res;
        return res;
    }
}
