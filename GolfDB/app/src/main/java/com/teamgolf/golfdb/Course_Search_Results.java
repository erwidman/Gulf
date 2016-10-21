package com.teamgolf.golfdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Course_Search_Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course__search__results);

        //String[] result = getIntent().getStringArrayExtra(Select_course.EXTRA_MESSAGE);

//        ListView listview =  (ListView) findViewById(R.id.c_Course_Search_Results);
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, result);
//        listview.setAdapter(adapter);
//        return;
    }
}
