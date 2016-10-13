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



public class create_before_round extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_before_round);
        hideStage2();
    }
    public void hideStage1()
    {
        findViewById(R.id.cbr_1_course_location).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_1_course_location_text).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_1_course_name).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_1_course_name_text).setVisibility(View.INVISIBLE);
        findViewById(R.id.cbr_1_enter);
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
        findViewById(R.id.cbr_testbox).setVisibility(View.INVISIBLE);

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
        findViewById(R.id.cbr_testbox).setVisibility(View.VISIBLE);

    }
    public void test(View v)
    {
        Intent intent = new Intent(v.getContext(), MainScreen.class);
        startActivity(intent);
    }
    public void courseCreate(View v)
    {
        //this is where we check if they gave us course+location that we like

        hideStage1();
        showStage2();
    }
}