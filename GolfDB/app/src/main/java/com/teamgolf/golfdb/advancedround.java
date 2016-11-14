package com.teamgolf.golfdb;

/**
 * Created by ben on 14/11/16.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
public class advancedround extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState)
    {
        Spinner dropdown = (Spinner)findViewById(R.id.ar_spinner);
        String[] items = new String[]{"1", "2", "three"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }
}

