package com.teamgolf.golfdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Class describing type of stats to view page
 */
public class Stat_select extends AppCompatActivity {

    Button GenStats;
    Button AdvancedStats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_select);
        
        AdvancedStats = (Button) findViewById((R.id.Coursestats));
        AdvancedStats.setOnClickListener(new ButtonListener("advanced",Constants.dbHandler));
    }

    /**
     * Button listener for two buttons on page, advances program accordingly
     */
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

                case "advanced":
                    //TODO
                    intent = new Intent(v.getContext(), Select_Course_Stats.class);
                    startActivity(intent);
                    break;
            }

        }
    }//end of button listener
}
