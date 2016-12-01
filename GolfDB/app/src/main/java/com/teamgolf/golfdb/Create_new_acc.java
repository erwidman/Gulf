package com.teamgolf.golfdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Class describing page for account creation
 */
public class Create_new_acc extends AppCompatActivity {

    DatabaseHandler dbHandler= Constants.dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_acc);
        findViewById(R.id.bad_pw).setVisibility(View.INVISIBLE);
       findViewById(R.id.bad_uname).setVisibility(View.INVISIBLE);
        findViewById(R.id.short_uname).setVisibility(View.INVISIBLE);
    }
    @Override
    public void onBackPressed() {
    }

    /**
     * Button listiener for create account, makes proper cheks on user input (password consistency, matching passwords ect.) and triggers following action accordingly
     * @param v
     */
    public void create_Account(View v)
    {
        EditText d_uname = (EditText)findViewById(R.id.d_uname);

     //chk passwd then chk to make sure insert wins
        String pass = ((EditText)findViewById(R.id.d_pw)).getText().toString().trim();
        String pass2 = ((EditText)findViewById(R.id.d_pw2)).getText().toString().trim();
        String userName = ((EditText)findViewById(R.id.d_uname)).getText().toString().trim();

        if (pass.compareTo(pass2)!=0)
            findViewById(R.id.bad_pw).setVisibility(View.VISIBLE);

        else {
                findViewById(R.id.bad_pw).setVisibility(View.INVISIBLE);
            if(dbHandler.insertUser(userName,pass)) {
                    findViewById(R.id.bad_uname).setVisibility(View.INVISIBLE);
                    findViewById(R.id.bad_pw).setVisibility(View.INVISIBLE);
                    findViewById(R.id.bad_uname).setVisibility(View.INVISIBLE);
                    Constants.user = userName;
                    Intent intent = new Intent(Create_new_acc.this, MainScreen.class);
                    startActivity(intent);
                }
            else {
                //user already exist, show error
               findViewById(R.id.bad_uname).setVisibility(View.VISIBLE);
            }
        }
    }
}
