package com.teamgolf.golfdb;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Login extends AppCompatActivity {

    DatabaseHandler dbHandler = null;
    Context context = null;
    Button loginButton;
    Button newUserButton;

    //boolean noting if app iNs on first run of code at startup
    static Boolean firstStart = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //create login view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Run Once at start (initalizations)
        if(firstStart) {
            context = this.getApplicationContext();
            DatabaseHandler dbHandler = new DatabaseHandler(context);
            firstStart = false;

            //init
            loginButton = (Button)findViewById(R.id.loginButton);
            loginButton.setOnClickListener(new ButtonListener("login",dbHandler));

            newUserButton = (Button)findViewById(R.id.newUserButton);
            newUserButton.setOnClickListener(new ButtonListener("newUser",dbHandler));

        }

    }



    protected void onDestroy(){
        super.onDestroy();
        if(isFinishing()){
            firstStart = false;
        }
    }


    public class ButtonListener implements Button.OnClickListener {
        String id;
        DatabaseHandler dbHandler;

        public ButtonListener(String id, DatabaseHandler dbHandler){
            this.id = id;
            this.dbHandler = dbHandler;
        }


        @Override
        public void onClick(View v) {
            switch (id) {
                case "login":
                    EditText tmp = (EditText)findViewById(R.id.uLogin);
                    String user = tmp.getText().toString().trim();
                    tmp = (EditText)findViewById(R.id.uPassword);
                    String password = tmp.getText().toString().trim();
                    Log.d("TESTING_LOGIN",Boolean.toString(dbHandler.checkPassword(user,password)));
                    if(dbHandler.checkPassword(user,password)){
                        //transition to blank activity
                        Intent intent = new Intent(Login.this, MainScreen.class);
                        startActivity(intent);

                    }
                    else{
                        //prompt user of invalid login

                        //create new user for testing purposes
                        dbHandler.insertUser(user,password);
                        Log.d("TESTING_LOGIN",Boolean.toString(dbHandler.checkPassword(user,password)));
                    }
                    break;
                case "newUser":
                    //todo (ben)
                    //Transition to new view blank
                    break;
            }
        }
    }//end of button listener

}//end of class
