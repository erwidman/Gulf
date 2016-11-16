package com.teamgolf.golfdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameType extends AppCompatActivity {

    Button advancedButton, basicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_type);
        advancedButton = (Button)findViewById(R.id.advancedButton);
        advancedButton.setOnClickListener(new ButtonListener("advanced"));

        basicButton = (Button) findViewById(R.id.basicButton);
        basicButton.setOnClickListener(new ButtonListener("basic"));
        findViewById(R.id.gt_error).setVisibility(View.INVISIBLE);
    }


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
                    intent = new Intent(GameType.this, advancedround.class);
                    startActivity(intent);
                    break;
                case "basic":
                    //todo Trasition to next page
                    if (findViewById(R.id.gt_numPlayers).toString().length()==0)
                    {
                        findViewById(R.id.gt_error).setVisibility(View.VISIBLE);
                        return;
                    }
                    Constants.numPlayers=1;//Integer.parseInt(((TextView)findViewById(R.id.gt_numPlayers)).getText().toString());
                    Constants.isAdvanced=false;

                    if  (Constants.numPlayers ==1)
                    {
                        intent = new Intent(GameType.this, Basic_round.class);
                        startActivity(intent);
                    }

                    //transition to basic scorecard
                    //todo transition
                    break;
            }

        }
    }//end of button listener
}
