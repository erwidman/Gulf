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
    DatabaseHandler dbHandler= Constants.dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_acc);
        findViewById(R.id.bad_pw).setVisibility(View.INVISIBLE);
       findViewById(R.id.bad_uname).setVisibility(View.INVISIBLE);
    }


    public void create_Account(View v)
    {
        EditText d_uname = (EditText)findViewById(R.id.d_uname);

     //chk passwd then chk to make sure insert wins
        String pass = ((EditText)findViewById(R.id.d_pw)).getText().toString().trim();
        String pass2 = ((EditText)findViewById(R.id.d_pw2)).getText().toString().trim();
        String userName = ((EditText)findViewById(R.id.d_uname)).getText().toString().trim();

        //// TODO: 10/7/16 Fred: add gui that signifys that passwords are not the same
        if (pass.compareTo(pass2)!=0) {
            Log.d("COMPARE_PASSWORDS", "Passwords do not match!");
            Log.d("COMPARE_PASSWORDS",pass);
            Log.d("COMPARE_PASSWORDS",pass2);
            findViewById(R.id.bad_pw).setVisibility(View.VISIBLE);
        }
        else
            {
                findViewById(R.id.bad_pw).setVisibility(View.INVISIBLE);
            if(dbHandler.insertUser(userName,pass))
                {
                   Log.d("INSERT_USER","User inserted!");
                    findViewById(R.id.bad_pw).setVisibility(View.INVISIBLE);
                    findViewById(R.id.bad_uname).setVisibility(View.INVISIBLE);
                  //// TODO: 10/7/16 transition
                    Intent intent = new Intent(create_new_acc.this, MainScreen.class);
                    startActivity(intent);
                }
            else
            {
               findViewById(R.id.bad_uname).setVisibility(View.VISIBLE);
                //user already exists print out gui error message
                Log.d("Check Username", "User already exists in the database");
            }

        }

    }
}
