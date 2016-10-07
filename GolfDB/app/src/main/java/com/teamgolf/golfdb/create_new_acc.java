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
    DatabaseHandler dbHandler= Login.dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_acc);
    }


    public void Check_Availiability(View v)
    {
        dbHandler=new DatabaseHandler(dbHandler.context);
        EditText d_uname = (EditText)findViewById(R.id.d_uname);

     //chk passwd then chk to make sure insert wins
        String pass = ((EditText)findViewById(R.id.d_pw)).toString().trim();
        String pass2 = ((EditText)findViewById(R.id.d_pw2)).toString().trim();
        String userName = ((EditText)findViewById(R.id.d_uname)).toString().trim();

        //// TODO: 10/7/16 Fred: add gui that signifys that passwords are not the same
        if (!pass.equals(pass2))
            Log.d("COMPARE_PASSWORDS","Passwords do not match!");

        else{
            if(dbHandler.insertUser(userName,pass))
            {
                Log.d("INSERT_USER", "User inserted!");
                //// TODO: 10/7/16 transition
            }
            else
            {
                //user already exists print out gui error message
                Log.d("Check Username", "User already exists in the database");
            }

        }

    }
}
