package com.teamgolf.golfdb;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


/**
 * Created by fred on 10/13/16.
 */

public class Select_course extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);
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
        Log.d("COURSE_SEARCH",locationSearch.toString());
            //Todo search the dba for toSearch
        Cursor c = Constants.dbHandler.getCourses(toSearch,locationSearch);

        //if the result of search is nothing do nothing
        if(c == null)
            return;

        //iterate through search
        c.moveToFirst();
        while(c.moveToNext()){
                //TODO create buttons for resulting courses
            }



    }
}
