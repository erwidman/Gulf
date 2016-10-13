package com.teamgolf.golfdb;
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

public class select_course extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);
        findViewById(R.id.search_checkbox_error).setVisibility(View.INVISIBLE);
    }

    public void courseSearch(View v)
    {
        String toSearch=((EditText)findViewById(R.id.search_course_entry)).getText().toString().trim();
        if (((CheckBox)findViewById(R.id.search_city)).isChecked()&&((CheckBox)findViewById(R.id.search_course)).isChecked())
        {
            //they have more than 1 box checked. they are fucking dumb.....
            findViewById(R.id.search_checkbox_error).setVisibility(View.VISIBLE);
            Log.d("Choice","1");

        }
        else if (((CheckBox)findViewById(R.id.search_city)).isChecked()&&(!((CheckBox)findViewById(R.id.search_course)).isChecked()))
        {
            //we are searching with a city name if we get here
            Log.d("Choice","1");

            //Todo search the dba for toSearch
        }
        else
        {
            //we are searching for a golf course name
            //Todo search the dba for toSearch
            Log.d("Choice","1");
        }
    }
}
