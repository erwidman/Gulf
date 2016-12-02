package com.teamgolf.golfdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RoundDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_display);
        populateList();
    }

    private void populateList(){
        final String [] info = produceInfo();
        ListView list = (ListView) findViewById(R.id.c_round_display_listview);
        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_list_item_1,info);
        list.setAdapter(ad);
    }

    private String [] produceInfo(){
        int [] [] score = Constants.scoreForStats;
        int [] []course =Constants.courseInfoForStats;

        String [] display = new String [4 + score[0].length];

        display[0] = "Par: " + Stat_methods.coursePar(course);
        display[1] = "Score: " + Stat_methods.overallScore(score);
        display[2] = "Score(+-): " + Stat_methods.aboveBelowPar(score,course);
        display[3] = "Holes-";

        int index = 4;
        for(int i =0 ;i <score[0].length;i++){
            display[index] = "Hole " +(i+1) +" : " + score[0][i];
            index++;
        }
        Log.d("Tmp",Integer.toString(display.length));
        return display;
    }

}
