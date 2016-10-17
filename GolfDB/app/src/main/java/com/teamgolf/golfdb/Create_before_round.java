package com.teamgolf.golfdb;

/**
 * Created by fred on 10/13/16.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class Create_before_round extends AppCompatActivity {
private int currentHole;
    private int holesOnCourse;
    private int goTo;
    protected void onCreate(Bundle savedInstanceState)
    {
        findViewById(R.id.cbr_1_dumass).setVisibility(View.INVISIBLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_before_round);
        hideStage2();
        currentHole=1;
        this.goTo=1;
        char a [] = {' ', ' '};
        a[0]=String.valueOf(currentHole).charAt(0);
        ((TextView)findViewById(R.id.cbr_hole)).setText(a,0,1);
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
        findViewById(R.id.cbr_1_dumass).setVisibility(View.INVISIBLE);
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

    }
    public void test(View v)
    {
        Intent intent = new Intent(v.getContext(), MainScreen.class);
        startActivity(intent);
    }
    public void courseCreate(View v)
    {
        String courseName = ((EditText)findViewById(R.id.cbr_1_course_name)).getText().toString().toLowerCase().trim();
        String state = ((EditText)findViewById(R.id.cbr_1_course_state)).getText().toString().toLowerCase().trim();
        String city = ((EditText)findViewById(R.id.cbr_1_course_location)).getText().toString().toLowerCase().trim();
        String numHoles = ((EditText)findViewById(R.id.cbr_1_numholes)).getText().toString().toLowerCase().trim();
        if (courseName.length()==0||state.length()==0||city.length()==0||numHoles.length()==0)
        {
            findViewById(R.id.cbr_1_dumass).setVisibility(View.VISIBLE);
        }
        //this is where we check if they gave us course+location that we like
        if(Constants.dbHandler.insertCourse(courseName,state,city,"0",numHoles);
        {
            this.holesOnCourse=Integer.parseInt(((EditText)findViewById(R.id.cbr_1_numholes)).getText().toString());
            hideStage1();
            showStage2();
        }
        else
        {
            //todo course already exist
        }

    }

    public void addedHole(View v)
    {
        if (currentHole==holesOnCourse)
        {
            //// TODO: 10/16/16 transition to new page to play the round
            Intent intent = new Intent(Create_before_round.this, Select_course.class);
            startActivity(intent);

        }
        if (currentHole==9){this.goTo=2;}
        this.currentHole++;
        char a [] = {' ', ' '};
        a[0]=String.valueOf(currentHole).charAt(0);
        if (goTo==2)
        {
            a[1]=String.valueOf(currentHole).charAt(1);
        }
        ((TextView)findViewById(R.id.cbr_hole)).setText(a,0,goTo);
        // TODO: 10/16/16 add the information entered in boxes to the database
    }
}