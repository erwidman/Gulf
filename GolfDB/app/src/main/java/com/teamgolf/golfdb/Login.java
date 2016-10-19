package com.teamgolf.golfdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Login extends AppCompatActivity {

    Button loginButton, newUserButton;


    //boolean noting if app iNs on first run of code at startup
    static Boolean firstStart = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Run Once at start (initalizations)
        if(firstStart) {
            Log.d("Run once","...");
            new Constants(this.getApplicationContext());
            firstStart = false;
        }


        //create login view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new ButtonListener("login"));
        newUserButton = (Button)findViewById(R.id.newUserButton);
        newUserButton.setOnClickListener(new ButtonListener("newUser"));
        findViewById(R.id.invalid_login).setVisibility(View.INVISIBLE);



    }

    protected void onDestroy(){
        super.onDestroy();
        if(isFinishing()){
            firstStart = false;
        }
    }


    public class ButtonListener implements Button.OnClickListener {
        String id;
        DatabaseHandler dbHandler = Constants.dbHandler;

        public ButtonListener(String id){
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            switch (id) {

                case "login":
                    EditText tmp = (EditText)findViewById(R.id.uLogin);
                    String user = tmp.getText().toString().trim();
                    tmp = (EditText)findViewById(R.id.uPassword);
                    String password = tmp.getText().toString().trim();
                    Log.d("user",user);
                    Log.d("password",password);
                    //Log.d("TESTING_LOGIN",Boolean.toString(dbHandler.checkPassword(user,password)));
                    if(dbHandler.checkPassword(user,password)){
                        //transition to blank activity
                        Intent intent = new Intent(Login.this, MainScreen.class);
                        startActivity(intent);

                    }
                    else{
                        //prompt user of invalid login
                        //create new user for testing purposes
                        Log.d("TESTING_LOGIN",Boolean.toString(dbHandler.checkPassword(user,password)));
                        findViewById(R.id.invalid_login).setVisibility(View.VISIBLE);
                    }
                    break;

                case "newUser":
                    Intent intent = new Intent(v.getContext(), Create_new_acc.class);
                    startActivity(intent);
                    break;
            }
        }
    }//end of button listener
}//end of class
