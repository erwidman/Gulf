package com.teamgolf.golfdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    @Override
    public void onBackPressed() {
    }
    Button playGameButton, optionButton, statisticsButton, closeButton;
    static Boolean firstStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        if(firstStart) {
            //init button
            playGameButton = (Button) findViewById(R.id.playGameButton);
            playGameButton.setOnClickListener(new ButtonListener("playGame", Constants.dbHandler));
            optionButton = (Button) findViewById(R.id.optionsButton);
            optionButton.setOnClickListener(new ButtonListener("options", Constants.dbHandler));
            statisticsButton = (Button) findViewById(R.id.statisticsButton);
            statisticsButton.setOnClickListener(new ButtonListener("stats", Constants.dbHandler));
            closeButton = (Button) findViewById(R.id.closeButton);
            closeButton.setOnClickListener(new ButtonListener("close", Constants.dbHandler));
            firstStart = false;
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
            Intent intent;
            switch(id){
                case "playGame":
                    //todo Transition to next page

                    intent = new Intent(v.getContext(), Select_course.class);
                    startActivity(intent);
                    break;
                case "stats":
                    //todo Transition
                    break;
                case "options":
                    //todo Transition
                    break;
                case "close":
                    intent = new Intent(MainScreen.this,Login.class);
                    startActivity(intent);
                    break;
            }

        }
    }//end of button listener

}
