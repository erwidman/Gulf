package com.teamgolf.golfdb;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;


/**
 * Created by fred on 10/13/16.
 */

public class Select_course extends AppCompatActivity
{
//    public final static String EXTRA_MESSAGE = "com.teamgolf.golfdb";

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
        Boolean nameSearch = ((CheckBox)findViewById(R.id.search_course)).isChecked();

        String[] results = Constants.dbHandler.getCourses(toSearch,locationSearch,nameSearch);

        if(results!=null) {
            int k = 0;
            for (String s : results) {
                int i = 0;
                for (char c : s.toCharArray()){

                    if(c== ':'){
                        Log.d("Character", "true");
                       s = s.substring(0,i) + ' ' + s.substring(i+1,s.length());
                    }
                    i++;
                }
                Log.d("SearchResult", s);
                results[k] = s;
                k++;
            }
           ListView listview =  (ListView) findViewById(R.id.c_Course_Search_Results);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, results);
            listview.setAdapter(adapter);





            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    //TODO
                }

            });



        }
//        Intent intent = new Intent(v.getContext(), Course_Search_Results.class);
//        intent.putExtra(EXTRA_MESSAGE, results);
//        startActivity(intent);

    }
    public void courseSelected(View v)
    {
        //on click listener for course selection
        ListView list = (ListView)findViewById(R.id.c_Course_Search_Results);
        //list.getAdapter().;
    }
    public void playCourse(String courseName, String courseLoc){

        //load newgame for user
        Constants.dbHandler.startGame(courseName,courseLoc);
        //todo transition to scorecard
    }
}
