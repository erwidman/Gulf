package com.teamgolf.golfdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class RoundList extends AppCompatActivity {

    ListView list;
    ArrayAdapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_list);
        populateList();
    }
    private void populateList(){
        final String [] res = Constants.dbHandler.getRounds();
        list = (ListView) findViewById(R.id.c_Select_course_stats_listView);
        ad = new ArrayAdapter(this,android.R.layout.simple_list_item_1,res);
        list.setAdapter(ad);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String course = res [position];
               // courseSelected(course,view);
            }
        });
    }
}
