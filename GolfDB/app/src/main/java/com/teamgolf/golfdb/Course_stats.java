package com.teamgolf.golfdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Class describing page displaying stats
 */
public class Course_stats extends AppCompatActivity {

    static int [] [] roundInfo;
    static String [] [] courseInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_stats);
        String [] stats={"Average Round Score ", "Times Played ", "% Birdie ", "% Par ", "% Bogie "};
        int[][] intInfo=new int[courseInfo.length][courseInfo[0].length];
        for(int i=0;i<courseInfo.length;i++)
        {
            for(int j=0;i<courseInfo[i].length;j++)
            {
                intInfo[i][j]=Integer.parseInt(courseInfo[i][j]);
            }
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, stats);

        ListView listView = (ListView) findViewById(R.id.stats_list);
        listView.setAdapter(adapter);

    }
}
