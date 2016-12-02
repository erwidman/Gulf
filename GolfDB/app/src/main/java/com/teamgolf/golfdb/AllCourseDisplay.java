package com.teamgolf.golfdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AllCourseDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_course_display);
        populateList();
    }

    private void populateList(){
        final String [] info = produceInfo();
        ListView list = (ListView) findViewById(R.id.c_all_course_display_listview);
        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_list_item_1,info);
        list.setAdapter(ad);
    }

    private String [] produceInfo(){
        int [] [] score = Constants.scoreForStats;
        int [] []course =Constants.courseInfoForStats;

        String [] display = new String [6];

        display[0] = "Course Average: " + Stat_methods.averageRoundScore(score);
        display[1] = "Course Handicap: " + Stat_methods.Handicap(score,course);
        display[2]= "%Eagles: "   + (float) Stat_methods.Percentages(score,course)[0];
        display[3]= "%Birdie:  " + (float) Stat_methods.Percentages(score,course)[1];
        display[4]= "%Par: "  + (float) Stat_methods.Percentages(score,course)[2];
        display[5]= "%Boogie: "  + (float) Stat_methods.Percentages(score,course)[3];
        return display;
    }
}
