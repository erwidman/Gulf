package com.teamgolf.golfdb;

/**
 * Created by fred on 10/13/16.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class Create_before_round extends AppCompatActivity {

    @Override
    public void onBackPressed() {
    }
    //inits
    private int currentHole;
    private int holesOnCourse;
    private int goTo;

    private String currCourseName;
    private String courseLocation;


    //paraelle arrays
    private String holeDistanceChild [];
    private String holeDistanceWomen [];
    private String holeDistanceMen [];
    private String par[];




    protected void onCreate(Bundle savedInstanceState)
    {
        //FIX
        //findViewById(R.id.cbr_1_dumass).setVisibility(View.INVISIBLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_before_round);
        hideStage2();
        currentHole=1;
        this.goTo=1;
        char a [] = {' ', ' '};
        a[0]=String.valueOf(currentHole).charAt(0);
        ((TextView)findViewById(R.id.cbr_hole)).setText(a,0,1);
        findViewById(R.id.cbr_1_error).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_error2).setVisibility(View.INVISIBLE);
    }
    public void hideStage1()
    {
        findViewById(R.id.cbr_1_course_location).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_1_course_location_text).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_1_course_name).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_1_course_name_text).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_1_course_state).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_1_course_state_text).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_1_numholes).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_1_numholes_text).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_1_error).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_1_enter).setVisibility(View.INVISIBLE);

    }
    public void hideStage2()
    {
        findViewById(R.id.cbr_next_hole).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_yd).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_yd2).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_yd3).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_woman_yard).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_man_yard).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_child_yard).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_man_tee).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_woman_tee).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_child_tee).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_hole).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_hole_number).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_par).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_par_text).setVisibility(View.INVISIBLE);

    }
    public void showStage2()
    {
        findViewById(R.id.cbr_next_hole).setVisibility(View.VISIBLE);
        findViewById(R.id.cbr_yd).setVisibility(View.VISIBLE);
        findViewById(R.id.cbr_yd2).setVisibility(View.VISIBLE);
        findViewById(R.id.cbr_yd3).setVisibility(View.VISIBLE);
        findViewById(R.id.cbr_woman_yard).setVisibility(View.VISIBLE);
        findViewById(R.id.cbr_man_yard).setVisibility(View.VISIBLE);
        findViewById(R.id.cbr_child_yard).setVisibility(View.VISIBLE);
        findViewById(R.id.cbr_man_tee).setVisibility(View.VISIBLE);
        findViewById(R.id.cbr_woman_tee).setVisibility(View.VISIBLE);
        findViewById(R.id.cbr_child_tee).setVisibility(View.VISIBLE);
        findViewById(R.id.cbr_hole).setVisibility(View.VISIBLE);
        findViewById(R.id.cbr_hole_number).setVisibility(View.VISIBLE);
        findViewById(R.id.cbr_par).setVisibility(View.VISIBLE);
        findViewById(R.id.cbr_par_text).setVisibility(View.VISIBLE);

    }

    //______________________________________________________________________________________________________________


    public void courseCreate(View v)
    {
        Log.d("courseCreate","RUNNING METHOD");
        //init textfields
        String courseName = ((EditText)findViewById(R.id.cbr_1_course_name)).getText().toString().toLowerCase().trim();
        String state = ((EditText)findViewById(R.id.cbr_1_course_state)).getText().toString().toLowerCase().trim();
        String city = ((EditText)findViewById(R.id.cbr_1_course_location)).getText().toString().toLowerCase().trim();
        String numHoles = ((EditText)findViewById(R.id.cbr_1_numholes)).getText().toString().toLowerCase().trim();


        //if an input is missing
        if (courseName.length()==0||state.length()==0||city.length()==0||numHoles.length()==0)
        {
            findViewById(R.id.cbr_1_error).setVisibility(View.VISIBLE);
            return;
        }

        //if course is succesfully inserted
        if(Constants.dbHandler.insertCourse(courseName,state,city,"0",numHoles))
        {
            this.holesOnCourse=Integer.parseInt(((EditText)findViewById(R.id.cbr_1_numholes)).getText().toString());
            hideStage1();
            showStage2();

            //used for inserting holes later
            this.courseLocation = state + ":" + city;
            this.currCourseName = courseName;

            //load array sizes
            int size = Integer.parseInt(numHoles);
            this.holeDistanceChild = new String[size];
            this.holeDistanceMen = new String[size];
            this.holeDistanceWomen = new String[size];
            this.par = new String[size];
        }
        else
        {
            //todo course already exist print error
        }

    }

    public void addedHole(View v)
    {
        //if forums completed
        if(((Button)(findViewById(R.id.cbr_next_hole))).getText().equals("Finish")){

        }


        if (currentHole==holesOnCourse)
        {
            //// TODO: 10/16/16 transition to new page to play the round
            Intent intent = new Intent(Create_before_round.this, Select_course.class);
            startActivity(intent);

        }


        //store values in array
        String childDis = ((EditText)findViewById(R.id.cbr_child_yard)).getText().toString().trim();
        String menDis = ((EditText)findViewById(R.id.cbr_man_yard)).getText().toString().trim();
        String womenDis = ((EditText)findViewById(R.id.cbr_woman_yard)).getText().toString().trim();
        String par = ((EditText)findViewById(R.id.cbr_par)).getText().toString().trim();


        //if any of the strings are null print error
        Log.d("Compare",childDis);
        if (childDis.isEmpty() || menDis.isEmpty()||womenDis.isEmpty()||par.isEmpty())
        {
            findViewById(R.id.cbr_error2).setVisibility(View.VISIBLE);
            return;
        }

        //save data to arrays to be written to db.
        this.holeDistanceChild[currentHole-1] = childDis;
        this.holeDistanceWomen[currentHole-1] = womenDis;
        this.holeDistanceMen[currentHole-1] = menDis;
        this.par[currentHole-1] = par;
        emptyText();


        if (currentHole==9){this.goTo=2;}
        this.currentHole++;
        char a [] = {' ', ' '};
        a[0]=String.valueOf(currentHole).charAt(0);
        if (goTo==2)
        {
            a[1]=String.valueOf(currentHole).charAt(1);
        }
        ((TextView)findViewById(R.id.cbr_hole)).setText(a,0,goTo);
        if (currentHole==(holesOnCourse))
        {
            String t = "Finish";
            char tt []= t.toCharArray();
            ((Button)(findViewById(R.id.cbr_next_hole))).setText(tt,0,6);
        }
        // TODO: 10/16/16 add the information entered in boxes to the database



    }

    public void emptyText()
    {
        ((EditText)findViewById(R.id.cbr_child_yard)).setText("");
        ((EditText)findViewById(R.id.cbr_man_yard)).setText("");
        ((EditText)findViewById(R.id.cbr_woman_yard)).setText("");
        ((EditText)findViewById(R.id.cbr_par)).setText("");
    }
}