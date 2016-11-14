package com.teamgolf.golfdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Stat_select extends AppCompatActivity {

    Button GenStats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_select);

        GenStats = (Button) findViewById(R.id.GenStats);
        GenStats.setOnClickListener(new ButtonListener("general", Constants.dbHandler));
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
                case "general":
                    //todo Transition to next page

                    intent = new Intent(v.getContext(), General_stats.class);
                    startActivity(intent);
                    break;
                case "advanced":
                    //TODO
                    //intent = new Intent(v.getContext(), Stat_select.class);
                    //startActivity(intent);
                    break;
            }

        }
    }//end of button listener
}
