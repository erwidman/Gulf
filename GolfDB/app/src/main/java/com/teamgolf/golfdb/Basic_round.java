package com.teamgolf.golfdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by fred on 10/23/16.
 */

public class Basic_round extends AppCompatActivity
{
    private String curHole;
    private int firstRun=1;
    int hatejava =1;
    public int [][] score;
    public TextView hole1Text;
    public TextView hole2Text;
    public TextView hole3Text;
    public TextView hole4Text;
    public int numPlayers;
    int numHoles;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_round);

        if (firstRun==1) {
            curHole="1";

            numPlayers=1;
            numHoles=18;
            score=new int [numPlayers][numHoles];
            TextView hole1Text = (TextView) findViewById(R.id.br_h1);
            TextView hole2Text = (TextView) findViewById(R.id.br_h2);
            TextView hole3Text = (TextView) findViewById(R.id.br_h3);
            TextView hole4Text = (TextView) findViewById(R.id.br_h4);
            hole1Text.setText(curHole.toCharArray(),0,1);
            curHole= Integer.toString(Integer.parseInt(curHole) + 1);
            hole2Text.setText(curHole.toCharArray(),0,1);
            curHole= Integer.toString(Integer.parseInt(curHole) + 1);
            hole3Text.setText(curHole.toCharArray(),0,1);
            curHole= Integer.toString(Integer.parseInt(curHole) + 1);
            hole4Text.setText(curHole.toCharArray(),0,1);

        }
    }
    public void grabStats(int position)
    {
        TextView hole1Par=(TextView) findViewById(R.id.br_par1);
        TextView hole2Par=(TextView) findViewById(R.id.br_par2);
        TextView hole3Par=(TextView) findViewById(R.id.br_par3);
        TextView hole4Par=(TextView) findViewById(R.id.br_par4);

        TextView hole1YDs=(TextView) findViewById(R.id.br_yd1);
        TextView hole2YDs=(TextView) findViewById(R.id.br_yd2);
        TextView hole3YDs=(TextView) findViewById(R.id.br_yd3);
        TextView hole4YDs=(TextView) findViewById(R.id.br_yd4);

        TextView hole1Score=(TextView) findViewById(R.id.br_sc1);
        TextView hole2Score=(TextView) findViewById(R.id.br_sc2);
        TextView hole3Score=(TextView) findViewById(R.id.br_sc3);
        TextView hole4Score=(TextView) findViewById(R.id.br_sc4);

        DatabaseHandler dbhandler = Constants.dbHandler;

        //dbhandler.


    }
    public void prev(View v)
    {
        if (Integer.parseInt(curHole)<4)
        {
            return;
        }

        TextView hole1Text=(TextView) findViewById(R.id.br_h1);
        TextView hole2Text=(TextView) findViewById(R.id.br_h2);
        TextView hole3Text=(TextView) findViewById(R.id.br_h3);
        TextView hole4Text=(TextView) findViewById(R.id.br_h4);
        curHole=(hole1Text.getText().toString());
        curHole= Integer.toString(Integer.parseInt(curHole) -4);

        hole1Text.setText(curHole.toCharArray(),0,hatejava);
        curHole= Integer.toString(Integer.parseInt(curHole) + 1);
        if (Integer.parseInt(curHole)>9){hatejava=2; } else if (Integer.parseInt(curHole)<9){hatejava=1;}
        hole2Text.setText(curHole.toCharArray(),0,hatejava);
        curHole= Integer.toString(Integer.parseInt(curHole) + 1);
        if (Integer.parseInt(curHole)>9){hatejava=2; } else if (Integer.parseInt(curHole)<9){hatejava=1;}
        hole3Text.setText(curHole.toCharArray(),0,hatejava);
        curHole= Integer.toString(Integer.parseInt(curHole) + 1);
        if (Integer.parseInt(curHole)>9){hatejava=2; } else if (Integer.parseInt(curHole)<9){hatejava=1;}
        hole4Text.setText(curHole.toCharArray(),0,hatejava);
        curHole= Integer.toString(Integer.parseInt(curHole) -3);
        if (Integer.parseInt(curHole)>9){hatejava=2; } else if (Integer.parseInt(curHole)<9){hatejava=1;}


    }


    public void next(View v)
    {

        TextView hole1Text=(TextView) findViewById(R.id.br_h1);
        TextView hole2Text=(TextView) findViewById(R.id.br_h2);
        TextView hole3Text=(TextView) findViewById(R.id.br_h3);
        TextView hole4Text=(TextView) findViewById(R.id.br_h4);
        curHole=(hole1Text.getText().toString());
        curHole= Integer.toString(Integer.parseInt(curHole) + 3);

        hole1Text.setText(curHole.toCharArray(),0,hatejava);
        curHole= Integer.toString(Integer.parseInt(curHole) + 1);
        if (Integer.parseInt(curHole)>9){hatejava=2; } else if (Integer.parseInt(curHole)<9){hatejava=1;}
        hole2Text.setText(curHole.toCharArray(),0,hatejava);
        curHole= Integer.toString(Integer.parseInt(curHole) + 1);
        if (Integer.parseInt(curHole)>9){hatejava=2; } else if (Integer.parseInt(curHole)<9){hatejava=1;}
        hole3Text.setText(curHole.toCharArray(),0,hatejava);
        curHole= Integer.toString(Integer.parseInt(curHole) + 1);
        if (Integer.parseInt(curHole)>9){hatejava=2; } else if (Integer.parseInt(curHole)<9){hatejava=1;}
        hole4Text.setText(curHole.toCharArray(),0,hatejava);
        curHole= Integer.toString(Integer.parseInt(curHole) -3);
        if (Integer.parseInt(curHole)>9){hatejava=2; } else if (Integer.parseInt(curHole)<9){hatejava=1;}
    }

}
