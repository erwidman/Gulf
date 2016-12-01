package com.teamgolf.golfdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Class describing screen that displays rounds for selection
 */
public class RoundList extends AppCompatActivity {

    //gui objects
    ListView list;
    ArrayAdapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_list);
        populateList();
    }

    /**
     * Used to populate the listview (scrollpane) based on user inputs, makes call to dbhandler to pull this information
     */
    private void populateList(){
        final String [] res = Constants.dbHandler.getRounds();
        list = (ListView) findViewById(R.id.c_Select_course_stats_listView);
        ad = new ArrayAdapter(this,android.R.layout.simple_list_item_1,res);
        list.setAdapter(ad);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //removes date
                String clean = "";
                String input = res[position];

                int score [] [];
                if(position == 0){
                   score = Constants.dbHandler.getScore(Constants.courseNamePickedForStats,Constants.courseLocationPickedForStats,null,false,false);
                }
                else{
                    int index = 0;
                    while(input.charAt(index)!= '-'){
                        clean = clean + Character.toString(input.charAt(index));
                        index++;
                    }
                   score = Constants.dbHandler.getScore(null,null,clean,false,false);
                }
                //TODO transition to stats page
            }
        });
    }
}
