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

public class Select_Course_Stats extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course_stats);
        findViewById(R.id.search_checkbox_error).setVisibility(View.INVISIBLE);
    }

    public void create_course(View v)
    {
        Intent intent = new Intent(v.getContext(), How_to_create_course.class);
        startActivity(intent);
    }


    public void courseSearch(View v)
    {
        //inputed search
        String toSearch=((EditText)findViewById(R.id.search_course_entry)).getText().toString().toLowerCase().trim();
        //loaction check box
        Boolean locationSearch = ((CheckBox)findViewById(R.id.search_city)).isChecked();
        Boolean nameSearch = ((CheckBox)findViewById(R.id.search_course)).isChecked();

        final String[] results = Constants.dbHandler.getCourses(toSearch,locationSearch,nameSearch);

        if(results!=null) {

            ListView listview = (ListView)findViewById(R.id.c_Course_Search_Results);

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, results);
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
//        Intent intent = new Intent(v.getContext(), Course_Search_Results.class);
//        intent.putExtra(EXTRA_MESSAGE, results);
//        startActivity(intent);

    }
    public void courseSelected(String course, View v){
        Log.d("TEST",course);
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
        Log.d("Name",courseName);
        Log.d("Location",location);

        String [][] holeInfo = Constants.dbHandler.holeInfo(courseName,location);
        int [][] roundInfo = Constants.dbHandler.getScore(false,false,courseName,location);
        if(roundInfo.length==0){
            //TODO print that user hasnt played course;
            return;
        }



        Course_stats.roundInfo=roundInfo;
        Course_stats.courseInfo=holeInfo;

        for(int i =0; i<holeInfo[0].length;i+=1){
            Log.d("Par",holeInfo[0][i]);
            Log.d("MenDis",holeInfo[1][i]);
            Log.d("WomenDis",holeInfo[2][i]);
            Log.d("ChildDis",holeInfo[3][i]);
        }
        Constants.holeLoaded= holeInfo;
/*
<<<<<<< HEAD
        Intent intent = new Intent(Select_course.this, GameType.class);
=======
*/
        Intent intent = new Intent(v.getContext(), Course_stats.class);
        startActivity(intent);
    }
    public void playCourse(String courseName, String courseLoc){

        //load newgame for user
        Constants.dbHandler.startGame(courseName,courseLoc);
        //todo transition to scorecard
    }
}
