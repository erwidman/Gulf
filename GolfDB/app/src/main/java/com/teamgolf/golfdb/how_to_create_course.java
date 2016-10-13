package com.teamgolf.golfdb;

/**
 * Created by fred on 10/13/16.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;



public class how_to_create_course extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_create_course);
    }
    public void before_round(View v)
    {
        Intent intent = new Intent(v.getContext(), create_before_round.class);
        startActivity(intent);
    }
}
