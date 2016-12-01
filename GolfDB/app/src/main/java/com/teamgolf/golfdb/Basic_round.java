package com.teamgolf.golfdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Class Used to display basic round recording screen
 */
public class Basic_round extends AppCompatActivity
{
    //declaration of instances variables
    private int curHole;
    public int [][] score;
    public int numPlayers;
    public int gender;  //1 = man 2 = woman 3 = child
    public String [][] courseInfo;
    int numHoles;

    //Gui objects
    TextView[] par = new TextView[4];
    TextView[] yds = new TextView[4];
    TextView[] scoreOnCard = new TextView[4];
    TextView[] txt = new TextView[4];

    TextView totalScore;
    TextView totalPar;
    TextView totalYDS;

    EditText edit;


    protected void onCreate(Bundle savedInstanceState)
    {

        //initaliztion of instance variables
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_round);
        par[0]=(TextView) findViewById(R.id.br_par1);
        par[1]=(TextView) findViewById(R.id.br_par2);
        par[2]=(TextView) findViewById(R.id.br_par3);
        par[3]=(TextView) findViewById(R.id.br_par4);

        yds[0]=(TextView) findViewById(R.id.br_yd1);
        yds[1]=(TextView) findViewById(R.id.br_yd2);
        yds[2]=(TextView) findViewById(R.id.br_yd3);
        yds[3]=(TextView) findViewById(R.id.br_yd4);

        scoreOnCard[0]=(TextView) findViewById(R.id.br_sc1);
        scoreOnCard[1]=(TextView) findViewById(R.id.br_sc2);
        scoreOnCard[2]=(TextView) findViewById(R.id.br_sc3);
        scoreOnCard[3]=(TextView) findViewById(R.id.br_sc4);

        txt[0] = (TextView) findViewById(R.id.br_h1);
        txt[1] = (TextView) findViewById(R.id.br_h2);
        txt[2] = (TextView) findViewById(R.id.br_h3);
        txt[3] = (TextView) findViewById(R.id.br_h4);

        edit= (EditText)findViewById(R.id.br_enteredScore);

        totalScore = (TextView) findViewById(R.id.br_sc_total);
        totalPar = (TextView) findViewById((R.id.br_par_total));
        totalYDS = (TextView) findViewById(R.id.br_yd_total);

        gender=1;
        numPlayers=1;
        curHole = 1;
        courseInfo=Constants.holeLoaded;
        this.numHoles=courseInfo[0].length;
        score=new int [numPlayers][numHoles];
        int parTotal =0;
        int yardTotal = 0;
        for(int i =0; i<courseInfo[0].length;i+=1){
            parTotal += Integer.parseInt(courseInfo[0][i]);
            yardTotal+= Integer.parseInt(courseInfo[1][i]);
        }
        totalPar.setText(Integer.toString(parTotal));
        totalYDS.setText(Integer.toString(yardTotal));


        //begin display
        grabStats();



    }

    /**
     * Used to update text on screen for holes being displayed
     */
    public void grabStats()
    {
        //show all gui objects
        showAll();

        //row 0 = par
        //row 1 = men
        //row 2 = women
        //row 3 = child

        //iterate through the holes and set text to proper information about given hole
        for(int i = 0; i <par.length;i+=1){
            if(curHole+(i-1)<numHoles){
                txt[i].setText(Integer.toString(curHole+i));
                yds[i].setText(courseInfo[gender][curHole+(i-1)]);
                par[i].setText(courseInfo[0][curHole +(i-1)]);
                scoreOnCard[i].setText(Integer.toString(score[0][curHole+(i-1)]));
            }
            else{
                txt[i].setVisibility(View.INVISIBLE);
                yds[i].setVisibility(View.INVISIBLE);
                par[i].setVisibility(View.INVISIBLE);
                scoreOnCard[i].setVisibility(View.INVISIBLE);
            }
        }

        int scoreSum = sumScore();
        totalScore.setText(Integer.toString(scoreSum));




    }

    /**
     * Addition function used to sum total inputed score
     * @return totalScore
     */
    private int sumScore(){
        int sum = 0;
        for(int i=0; i< score[0].length;i+=1){
            sum +=score[0][i];
        }
        return sum;
    }

    /**
     * Makes all gui objects visible
     */
    private void showAll(){
        for(int i = 0; i <par.length;i+=1){
            txt[i].setVisibility(View.VISIBLE);
            yds[i].setVisibility(View.VISIBLE);
            par[i].setVisibility(View.VISIBLE);
            scoreOnCard[i].setVisibility(View.VISIBLE);
        }
    }

    /**
     * Button listener for previous button
     * @param v View
     */
    public void Prev(View v)
    {
        if((curHole-1)!=0)
        curHole--;
        grabStats();
    }


    public void next(View v)
    {
        if((curHole+1)<=numHoles)
        curHole++;
        grabStats();

    }

    /**
     * Enter button listener
     * @param v View
     */
    public void Enter(View v)
    {
        if(!edit.getText().toString().isEmpty())
            score[0][curHole-1] = Integer.parseInt(edit.getText().toString());
        else {
            grabStats();
            return;
        }
        edit.setText("");
        next(v);
    }

    /**
     * Submit buttonlistener, inserts info into db
     * @param v
     */
    public void Submit(View v){
        Constants.dbHandler.insertScore(score);
        Intent intent = new Intent(v.getContext(), MainScreen.class);
        startActivity(intent);
    }

}
