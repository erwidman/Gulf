package com.teamgolf.golfdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by fred on 10/31/16.
 */

public class Subnames extends AppCompatActivity {
    static boolean firstrun = true;

    protected void onCreate(Bundle savedInstanceState) {

        //Run Once at start (initalizations)
        if (firstrun) {
            Log.d("Run once", "...");
            new Constants(this.getApplicationContext());
            firstrun = false;
            if (Constants.numPlayers == 2) {
                findViewById(R.id.s_player3).setVisibility(View.INVISIBLE);
                findViewById(R.id.s_player3Name).setVisibility(View.INVISIBLE);
                findViewById(R.id.s_player4).setVisibility(View.INVISIBLE);
                findViewById(R.id.s_player4Name).setVisibility(View.INVISIBLE);
            } else if (Constants.numPlayers == 3) {
                findViewById(R.id.s_player4).setVisibility(View.INVISIBLE);
                findViewById(R.id.s_player4Name).setVisibility(View.INVISIBLE);
            }

        }

    }

    public void Submit(View v)
    {
        //TODO check if name will fit
       // String player2 =

        int availableChar =32-(Constants.user.length()+1);
        if (Constants.numPlayers >= 2) {

        } else if (Constants.numPlayers > 3) {

        }
        else
        {

        }






    }
}