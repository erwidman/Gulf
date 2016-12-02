package com.teamgolf.golfdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Class describing screen in which game type is selected
 */
public class GameType extends AppCompatActivity {

    //buttons on screen
    Button advancedButton, basicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_type);
        advancedButton = (Button)findViewById(R.id.advancedButton);
        advancedButton.setOnClickListener(new ButtonListener("advanced"));

        basicButton = (Button) findViewById(R.id.basicButton);
        basicButton.setOnClickListener(new ButtonListener("basic"));
    }


    /**
     * Listeners for noted buttons, transitions program to proper state
     */
    public class ButtonListener implements Button.OnClickListener {
        String id;

        public ButtonListener(String id){
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            Intent intent;
            switch(id){
                case "advanced":
                    //todo Transition to next page
                    Constants.numPlayers=1;
                    Constants.isAdvanced=true;
                    intent = new Intent(GameType.this, AdvancedRound.class);
                    startActivity(intent);
                    break;
                case "basic":
                    //todo Trasition to next page

                    Constants.numPlayers=1;
                    Constants.isAdvanced=false;

                    //transition to basic scorecard
                    intent = new Intent(GameType.this, Basic_round.class);
                    startActivity(intent);

                    break;
            }

        }
    }//end of button listener
}
