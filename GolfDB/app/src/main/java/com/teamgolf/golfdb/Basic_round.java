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

//todo check for courses less 3 holes.

public class Basic_round extends AppCompatActivity
{
    private String curHole;
    private int firstRun=1;
    int playedHole;
    int hatejava =1;
    public int [][] score;
    public TextView hole1Text;
    public TextView hole2Text;
    public TextView hole3Text;
    public TextView hole4Text;
    public int numPlayers;
    public int gender;  //1 = man 2 = woman 3 = child
    public String [][] courseInfo;
    int numHoles;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_round);

        if (firstRun==1) {
            gender=1;

            numPlayers=1;
            score=new int [numPlayers][numHoles];
            curHole="1";
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
            curHole="1";
            grabStats(Integer.parseInt(curHole));
            Log.d("Currhole",curHole);

        }
    }
    public void grabStats(int pntr)
    {
        if (firstRun==1)
        {
            courseInfo=Constants.holeLoaded;
            this.numHoles=courseInfo.length+1;
            score=new int [numPlayers][numHoles];
            firstRun=512;
        }
        //showScore();
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


        //row 0 = par
        //row 1 = men
        //row 2 = women
        //row 3 = child


       // hole1Par.setText(courseInfo[0][0]);
       // hole2Par.setText(courseInfo[0][1]);
       // hole3Par.setText(courseInfo[0][2]);
       // hole4Par.setText(courseInfo[0][3]);

        hole1Par.setText(courseInfo[0][pntr-1]);
        hole2Par.setText(courseInfo[0][pntr-0]);
        hole3Par.setText(courseInfo[0][pntr+1]);
        hole4Par.setText(courseInfo[0][pntr+2]);

        hole1YDs.setText(courseInfo[gender][pntr-1]);
        hole2YDs.setText(courseInfo[gender][pntr-0]);
        hole3YDs.setText(courseInfo[gender][pntr+1]);
        hole4YDs.setText(courseInfo[gender][pntr+2]);





        DatabaseHandler dbhandler = Constants.dbHandler;

        //dbhandler.


    }
    public void Prev(View v)
    {

        if (Integer.parseInt(curHole)<5)
        {
            return;
        }
        drawAll();
        TextView hole1Text=(TextView) findViewById(R.id.br_h1);
        TextView hole2Text=(TextView) findViewById(R.id.br_h2);
        TextView hole3Text=(TextView) findViewById(R.id.br_h3);
        TextView hole4Text=(TextView) findViewById(R.id.br_h4);
        curHole= Integer.toString(Integer.parseInt(curHole) -4);
        int toSetBack = Integer.parseInt(curHole);


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

        grabStats(Integer.parseInt(curHole));

    }


    public void next(View v)
    {
        //todo check for out of bounds
        if (numHoles-(Integer.parseInt(curHole)+4)==4)
        {
            return;
        }
        else if (numHoles-(Integer.parseInt(curHole)+4)<4)
        {
            //we only want to draw certain fields
            Log.i("TAG001","I am calling smart draw");
            smartDraw(Integer.parseInt(curHole),numHoles);
            return;
        }
        curHole= Integer.toString(Integer.parseInt(curHole) + 4);
        TextView hole1Text=(TextView) findViewById(R.id.br_h1);
        TextView hole2Text=(TextView) findViewById(R.id.br_h2);
        TextView hole3Text=(TextView) findViewById(R.id.br_h3);
        TextView hole4Text=(TextView) findViewById(R.id.br_h4);
        grabStats(Integer.parseInt(curHole));

    }

    public void smartDraw(int cHole, int nHoles)
    {
        TextView hole1Hole=(TextView)findViewById(R.id.br_h1);
        TextView hole2Hole=(TextView)findViewById(R.id.br_h2);
        TextView hole3Hole=(TextView)findViewById(R.id.br_h3);
        TextView hole4Hole=(TextView)findViewById(R.id.br_h4);

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
     int pntr;
        switch (nHoles-(cHole +4)) {

            case 0:
                findViewById(R.id.br_h2).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_par2).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_par3).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_par4).setVisibility(View.INVISIBLE);

                findViewById(R.id.br_h3).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_yd2).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_yd3).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_yd4).setVisibility(View.INVISIBLE);

                findViewById(R.id.br_h4).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_sc2).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_sc3).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_sc4).setVisibility(View.INVISIBLE);
                curHole= Integer.toString(Integer.parseInt(curHole) + 4);
                pntr=Integer.parseInt(curHole);
               //only hole 1 should be drawn
                hole1Hole.setText(Integer.toString(pntr).toCharArray(),0,hatejava);
                hole1Par.setText(courseInfo[0][pntr-1]);
                hole1YDs.setText(courseInfo[gender][pntr-1]);

                break;
            case 1:
                findViewById(R.id.br_h3).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_h4).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_par3).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_par4).setVisibility(View.INVISIBLE);



                findViewById(R.id.br_yd3).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_yd4).setVisibility(View.INVISIBLE);



                findViewById(R.id.br_sc3).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_sc4).setVisibility(View.INVISIBLE);

                curHole= Integer.toString(Integer.parseInt(curHole) + 4);
                pntr=Integer.parseInt(curHole);
                hole1Hole.setText(Integer.toString(pntr).toCharArray(),0,hatejava);
                hole1Par.setText(courseInfo[0][pntr-1]);
                hole1YDs.setText(courseInfo[gender][pntr-1]);

                pntr++;
                hole2Hole.setText(Integer.toString(pntr).toCharArray(),0,hatejava);
                hole2Par.setText(courseInfo[0][pntr]);
                hole2YDs.setText(courseInfo[gender][pntr]);


                //only hole 2 should be drawn
                break;
            case 2:

                findViewById(R.id.br_h4).setVisibility(View.INVISIBLE);
                findViewById(R.id.br_par4).setVisibility(View.INVISIBLE);




                findViewById(R.id.br_yd4).setVisibility(View.INVISIBLE);




                findViewById(R.id.br_sc4).setVisibility(View.INVISIBLE);
                curHole= Integer.toString(Integer.parseInt(curHole) + 4);
                pntr=Integer.parseInt(curHole);

                hole1Hole.setText(Integer.toString(pntr).toCharArray(),0,hatejava);
                hole1Par.setText(courseInfo[0][pntr-1]);
                hole1YDs.setText(courseInfo[gender][pntr-1]);

                pntr++;
                hole2Hole.setText(Integer.toString(pntr).toCharArray(),0,hatejava);
                hole2Par.setText(courseInfo[0][pntr]);
                hole2YDs.setText(courseInfo[gender][pntr]);

                pntr++;
                hole3Hole.setText(Integer.toString(pntr).toCharArray(),0,hatejava);
                hole3Par.setText(courseInfo[0][pntr]);
                hole3YDs.setText(courseInfo[gender][pntr]);

                //only hole3 should be drawn
                break;

            default:  //todo delete when done testing
                hole1Hole.setText(Integer.toString(nHoles).toCharArray(),0,1);
                break;

        }
    }
    public void drawAll()
    {
         findViewById(R.id.br_par1).setVisibility(View.VISIBLE);
         findViewById(R.id.br_par2).setVisibility(View.VISIBLE);
        findViewById(R.id.br_par3).setVisibility(View.VISIBLE);
         findViewById(R.id.br_par4).setVisibility(View.VISIBLE);

         findViewById(R.id.br_yd1).setVisibility(View.VISIBLE);
         findViewById(R.id.br_yd2).setVisibility(View.VISIBLE);
         findViewById(R.id.br_yd3).setVisibility(View.VISIBLE);
        findViewById(R.id.br_yd4).setVisibility(View.VISIBLE);

         findViewById(R.id.br_sc1).setVisibility(View.VISIBLE);
         findViewById(R.id.br_sc2).setVisibility(View.VISIBLE);
         findViewById(R.id.br_sc3).setVisibility(View.VISIBLE);
         findViewById(R.id.br_sc4).setVisibility(View.VISIBLE);

        findViewById(R.id.br_h1).setVisibility(View.VISIBLE);
        findViewById(R.id.br_h2).setVisibility(View.VISIBLE);
        findViewById(R.id.br_h3).setVisibility(View.VISIBLE);
        findViewById(R.id.br_h4).setVisibility(View.VISIBLE);

    }

    public void Enter(View v)
    {
        //todo implement multiple players
        score[0][playedHole-1]=Integer.parseInt(((EditText)findViewById(R.id.br_enteredScore)).getText().toString());
        playedHole++;
        showScore();
    }

    public void showScore()
    {
        TextView hole1Score=(TextView) findViewById(R.id.br_sc1);
        TextView hole2Score=(TextView) findViewById(R.id.br_sc2);
        TextView hole3Score=(TextView) findViewById(R.id.br_sc3);
        TextView hole4Score=(TextView) findViewById(R.id.br_sc4);

        //todo add support for 1+ players
            if (playedHole-Integer.parseInt(curHole)>=1)
            {
                hole1Score.setText(score[0][Integer.parseInt(curHole)]);
            }
        else
            {
                hole1Score.setVisibility(View.INVISIBLE);
                hole2Score.setVisibility(View.INVISIBLE);
                hole3Score.setVisibility(View.INVISIBLE);
                hole4Score.setVisibility(View.INVISIBLE);
                return;
            }

        if (playedHole-Integer.parseInt(curHole)>=2)
        {
            hole2Score.setText(score[0][Integer.parseInt(curHole)+1]);
        }
        else
        {
            hole2Score.setVisibility(View.INVISIBLE);
            hole3Score.setVisibility(View.INVISIBLE);
            hole4Score.setVisibility(View.INVISIBLE);
        }

        if (playedHole-Integer.parseInt(curHole)>=3)
        {
            hole3Score.setText(score[0][Integer.parseInt(curHole)+2]);
        }
        else
        {
            hole3Score.setVisibility(View.INVISIBLE);
            hole4Score.setVisibility(View.INVISIBLE);
        }

        if (playedHole-Integer.parseInt(curHole)>=4)
        {
            hole4Score.setText(score[0][Integer.parseInt(curHole)]+3);
        }
        else
        {
            hole4Score.setVisibility(View.INVISIBLE);
        }

    }

}
