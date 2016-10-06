package com.teamgolf.golfdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class create_new_acc extends AppCompatActivity {
    //Context context =getApplicationContext();
    DatabaseHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.create_new_acc);
    }



    public void Check_Availiability(View v)
    {
        dbHandler=new DatabaseHandler(dbHandler.context);
        EditText d_uname = (EditText)findViewById(R.id.d_uname);
       /* if (dbHandler.isUser(d_uname.toString()))
        {
            //if returns true that means username already used
            TextView temp;
            temp =(TextView) findViewById(R.id.uname_pass);
            temp.setVisibility(View.INVISIBLE);
            temp = (TextView)findViewById(R.id.uname_fail);
            temp.setVisibility(View.VISIBLE);
            temp.setText(d_uname.toString()+"Is not Available. Try Again");
        }
        else
        {
            TextView temp2;
            temp2=(TextView)findViewById(R.id.uname_fail);
            temp2.setVisibility(View.INVISIBLE);
            temp2= (TextView)findViewById(R.id.uname_pass);
            temp2.setVisibility(View.VISIBLE);
            temp2.setText(d_uname.toString() + "Is Available!");
        }*/

    }
}
