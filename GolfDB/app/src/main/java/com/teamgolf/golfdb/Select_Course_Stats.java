package com.teamgolf.golfdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Used to display page for seaching course the user has played
 */
public class Select_Course_Stats extends AppCompatActivity {

    //gui objects
    ListView listview;
    ArrayAdapter adapter;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course_stats);
        populateList();
    }

    /**
     * used to pre populate the listview with courses user has played on
     */
    private void populateList(){
        final String [] res = Constants.dbHandler.getRecentCourses();

        listview = (ListView)findViewById(R.id.c_Course_Search_Results2);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, res);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String course = res [position];
                courseSelected(course,view);

            }
        });
    }

    /**
     * Method used to serach for a course based on users inputs, utilizes dbhandler to get results
     * @param v
     */
    public void courseSearch(View v)
    {
        //inputed search
        String toSearch=((EditText)findViewById(R.id.search_course_entry)).getText().toString().toLowerCase().trim();


        final String[] results = Constants.dbHandler.getCourses(toSearch,false,false);

        if(results!=null) {

            listview = (ListView)findViewById(R.id.c_Course_Search_Results2);
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, results);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    String course = results [position];
                    courseSelected(course,view);

                }
            });
        }
    }

    /**
     * Listener for listview, when item is clicked advances program conditions accordingly
     * @param course
     * @param v
     */
    public void courseSelected(String course, View v){

        String courseName = "";
        String location = "";

        char tmp;
        boolean loc = false;
        for(int i =0; i<course.length();i+=1){
            tmp = course.charAt(i);
            if(tmp == ':'&&!loc) {
                loc = true;
                continue;
            }
            if(!loc)
                courseName = courseName + tmp;
            else
                location = location + tmp;
        }

        Constants.courseNamePickedForStats = courseName;
        Constants.courseLocationPickedForStats = location;
        Constants.dbHandler.holeInfo(courseName,location);
        int [][] roundInfo = Constants.dbHandler.getScore(courseName,location,null,false,false);
        if(roundInfo.length==0){
            //TODO print that user hasnt played course;
            return;
        }
        Course_stats.roundInfo=roundInfo;

        Intent intent = new Intent(v.getContext(), RoundList.class);
        startActivity(intent);
    }

}
