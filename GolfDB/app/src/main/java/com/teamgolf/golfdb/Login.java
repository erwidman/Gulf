package com.teamgolf.golfdb;

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


public class Login extends AppCompatActivity {

    DatabaseHandler dbHandler = null;
    Context context = null;
    Button loginButton, newUserButton;

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
            findViewById(R.id.invalid_login).setVisibility(View.INVISIBLE);
            DatabaseHandler dbHandler = new DatabaseHandler(context);
            firstStart = false;

            //init
            loginButton = (Button)findViewById(R.id.loginButton);
            loginButton.setOnClickListener(new ButtonListener("login",dbHandler));

            //newUserButton = (Button)findViewById(R.id.newUserButton);
            //newUserButton.setOnClickListener(new ButtonListener("newUser",dbHandler));

            //setContentView(R.layout.activity_create_new_acc);
            //check_available = (Button)findViewById(R.id.check_available);
            //check_available.setOnClickListener(new ButtonListener("check_available",dbHandler));

        }

    }
public void setNewUserButton(View v)
{
        //Transition to new view blank
    Intent intent = new Intent(v.getContext(), create_new_acc.class);
    startActivity(intent);
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

                        findViewById(R.id.invalid_login).setVisibility(View.VISIBLE);


                    }
                    break;
//NEW USER MOVED TO METHOD LISTENER

               /* case "check_available":
                    EditText d_uname = (EditText)findViewById(R.id.d_uname);
                    if (dbHandler.isUser(d_uname.toString()))
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
                    }

                    break;*/
            }
        }
    }//end of button listener

}//end of class
