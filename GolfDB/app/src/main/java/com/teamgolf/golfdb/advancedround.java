package com.teamgolf.golfdb;

/**
 * Created by ben on 14/11/16.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
public class advancedround extends AppCompatActivity {

    TextView h1;
    TextView h2 ;
    TextView h3 ;
    TextView h4 ;
    TextView h5 ;
    TextView h6 ;
    TextView h7 ;
    TextView h8 ;
    TextView h9 ;
    int [] holeTotals;
    int totalTotal;
    int gender;
    String [][] score;
    String [][]courseInfo;
    int currentLine;
    int curHoleStroke;
    int curFirst;
    int numHoles;
    Spinner holeSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        h1 = (TextView)findViewById(R.id.ar_h1);
        h2 = (TextView)findViewById(R.id.ar_h2);
        h3 = (TextView)findViewById(R.id.ar_h3);
        h4 = (TextView)findViewById(R.id.ar_h4);
        h5 = (TextView)findViewById(R.id.ar_h5);
        h6 = (TextView)findViewById(R.id.ar_h6);
        h7 = (TextView)findViewById(R.id.ar_h7);
        h8 = (TextView)findViewById(R.id.ar_h8);
        h9 = (TextView)findViewById(R.id.ar_h9);

        numHoles=Constants.holeLoaded[0].length;
//        Spinner dropdown = (Spinner)findViewById(R.id.ar_spinner);
//        String[] items = new String[]{"Putter", "2 iron", "3 iron", "4 iron", "5 iron", "6 iron","7 iron", "8 iron", "9 iron", "wedge", "Sand Wedge", "Fairway wood", "Driver"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, items);
//        dropdown.setAdapter(adapter);
        holeTotals=new int[numHoles];
        score = new String [numHoles][10];
        currentLine=1;
        curFirst=1;
        gender=1;
        courseInfo=Constants.holeLoaded;
        Log.d("CousreInfo:", Integer.toString(Constants.holeLoaded[0].length));

        drawScore();
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public void finishHole(View v)
    {

        //this is the onclick for when you finish a hole


        curHoleStroke=0;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public void addStroke(View v)
    {
        //this is for when you add a stroke to the scorecard
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public void prevHole(View v)
    {
        //this is when you want to go back a hole
        if (currentLine==curFirst && curFirst!=1)
        {
            curFirst=curFirst-9;
            currentLine--;
            drawScore();
        }
        else if (currentLine==curFirst && curFirst==1)
        {
            return;
        }
        else
        {
            currentLine--;
            drawScore();
        }
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public void nextHole(View v)
    {
        if (currentLine-curFirst==8 &&currentLine!=numHoles)
        {
            curFirst+=9;
            currentLine++;
            drawScore();

        }
        else if (currentLine-curFirst==8 && currentLine==numHoles)
        {
            return;
        }
        else
        {
            currentLine++;
            drawScore();
        }
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public void backspace(View v)
    {
        //for when you hit backspace
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public void drawScore()
    {
        //row 0 = par
        //row 1 = men
        //row 2 = women
        //row 3 = child
        colorMeShit();
        switch (numHoles-curFirst)
        {
            //todo add totals to this :(

            //if >=9 then default case
            case 1:
            {
                h2.setVisibility(View.INVISIBLE); h3.setVisibility(View.INVISIBLE); h4.setVisibility(View.INVISIBLE); h5.setVisibility(View.INVISIBLE);h6.setVisibility(View.INVISIBLE); h7.setVisibility(View.INVISIBLE); h8.setVisibility(View.INVISIBLE); h9.setVisibility(View.INVISIBLE);
                h1.setText(Integer.toString(curFirst)+"| "+courseInfo[gender][curFirst-1]+"| "+courseInfo[0][curFirst-1]+"||| "+score[curFirst-1].toString());
            }
            case 2:
            {
                h3.setVisibility(View.INVISIBLE); h4.setVisibility(View.INVISIBLE); h5.setVisibility(View.INVISIBLE);h6.setVisibility(View.INVISIBLE); h7.setVisibility(View.INVISIBLE); h8.setVisibility(View.INVISIBLE); h9.setVisibility(View.INVISIBLE);
                h1.setText(Integer.toString(curFirst)+"| "+courseInfo[gender][curFirst-1]+"| "+courseInfo[0][curFirst-1]+"||| "+score[curFirst-1].toString());
                h2.setText(Integer.toString(curFirst+1)+"| "+courseInfo[gender][curFirst]+"| "+courseInfo[0][curFirst]+"||| "+score[curFirst].toString());
            }
            case 3:
            {
                h4.setVisibility(View.INVISIBLE); h5.setVisibility(View.INVISIBLE);h6.setVisibility(View.INVISIBLE); h7.setVisibility(View.INVISIBLE); h8.setVisibility(View.INVISIBLE); h9.setVisibility(View.INVISIBLE);
                h1.setText(Integer.toString(curFirst)+"| "+courseInfo[gender][curFirst-1]+"| "+courseInfo[0][curFirst-1]+"||| "+score[curFirst-1].toString());
                h2.setText(Integer.toString(curFirst+1)+"| "+courseInfo[gender][curFirst]+"| "+courseInfo[0][curFirst]+"||| "+score[curFirst].toString());
                h3.setText(Integer.toString(curFirst+2)+"| "+courseInfo[gender][curFirst+1]+"| "+courseInfo[0][curFirst+1]+"||| "+score[curFirst+1].toString());
            }
            case 4:
            {
                h5.setVisibility(View.INVISIBLE);h6.setVisibility(View.INVISIBLE); h7.setVisibility(View.INVISIBLE); h8.setVisibility(View.INVISIBLE); h9.setVisibility(View.INVISIBLE);
                h1.setText(Integer.toString(curFirst)+"| "+courseInfo[gender][curFirst-1]+"| "+courseInfo[0][curFirst-1]+"||| "+score[curFirst-1].toString());
                h2.setText(Integer.toString(curFirst+1)+"| "+courseInfo[gender][curFirst]+"| "+courseInfo[0][curFirst]+"||| "+score[curFirst].toString());
                h3.setText(Integer.toString(curFirst+2)+"| "+courseInfo[gender][curFirst+1]+"| "+courseInfo[0][curFirst+1]+"||| "+score[curFirst+1].toString());
                h4.setText(Integer.toString(curFirst+3)+"| "+courseInfo[gender][curFirst+2]+"| "+courseInfo[0][curFirst+2]+"||| "+score[curFirst+2].toString());
            }
            case 5:
            {
                h6.setVisibility(View.INVISIBLE); h7.setVisibility(View.INVISIBLE); h8.setVisibility(View.INVISIBLE); h9.setVisibility(View.INVISIBLE);
                h1.setText(Integer.toString(curFirst)+"| "+courseInfo[gender][curFirst-1]+"| "+courseInfo[0][curFirst-1]+"||| "+score[curFirst-1].toString());
                h2.setText(Integer.toString(curFirst+1)+"| "+courseInfo[gender][curFirst]+"| "+courseInfo[0][curFirst]+"||| "+score[curFirst].toString());
                h3.setText(Integer.toString(curFirst+2)+"| "+courseInfo[gender][curFirst+1]+"| "+courseInfo[0][curFirst+1]+"||| "+score[curFirst+1].toString());
                h4.setText(Integer.toString(curFirst+3)+"| "+courseInfo[gender][curFirst+2]+"| "+courseInfo[0][curFirst+2]+"||| "+score[curFirst+2].toString());
                h5.setText(Integer.toString(curFirst+4)+"| "+courseInfo[gender][curFirst+3]+"| "+courseInfo[0][curFirst+3]+"||| "+score[curFirst+3].toString());
            }
            case 6:
            {
                h7.setVisibility(View.INVISIBLE); h8.setVisibility(View.INVISIBLE); h9.setVisibility(View.INVISIBLE);
                h1.setText(Integer.toString(curFirst)+"| "+courseInfo[gender][curFirst-1]+"| "+courseInfo[0][curFirst-1]+"||| "+score[curFirst-1].toString());
                h2.setText(Integer.toString(curFirst+1)+"| "+courseInfo[gender][curFirst]+"| "+courseInfo[0][curFirst]+"||| "+score[curFirst].toString());
                h3.setText(Integer.toString(curFirst+2)+"| "+courseInfo[gender][curFirst+1]+"| "+courseInfo[0][curFirst+1]+"||| "+score[curFirst+1].toString());
                h4.setText(Integer.toString(curFirst+3)+"| "+courseInfo[gender][curFirst+2]+"| "+courseInfo[0][curFirst+2]+"||| "+score[curFirst+2].toString());
                h5.setText(Integer.toString(curFirst+4)+"| "+courseInfo[gender][curFirst+3]+"| "+courseInfo[0][curFirst+3]+"||| "+score[curFirst+3].toString());
                h6.setText(Integer.toString(curFirst+5)+"| "+courseInfo[gender][curFirst+4]+"| "+courseInfo[0][curFirst+4]+"||| "+score[curFirst+4].toString());

            }
            case 7:
            {
                h8.setVisibility(View.INVISIBLE); h9.setVisibility(View.INVISIBLE);
                h1.setText(Integer.toString(curFirst)+"| "+courseInfo[gender][curFirst-1]+"| "+courseInfo[0][curFirst-1]+"||| "+score[curFirst-1].toString());
                h2.setText(Integer.toString(curFirst+1)+"| "+courseInfo[gender][curFirst]+"| "+courseInfo[0][curFirst]+"||| "+score[curFirst].toString());
                h3.setText(Integer.toString(curFirst+2)+"| "+courseInfo[gender][curFirst+1]+"| "+courseInfo[0][curFirst+1]+"||| "+score[curFirst+1].toString());
                h4.setText(Integer.toString(curFirst+3)+"| "+courseInfo[gender][curFirst+2]+"| "+courseInfo[0][curFirst+2]+"||| "+score[curFirst+2].toString());
                h5.setText(Integer.toString(curFirst+4)+"| "+courseInfo[gender][curFirst+3]+"| "+courseInfo[0][curFirst+3]+"||| "+score[curFirst+3].toString());
                h6.setText(Integer.toString(curFirst+5)+"| "+courseInfo[gender][curFirst+4]+"| "+courseInfo[0][curFirst+4]+"||| "+score[curFirst+4].toString());
                h7.setText(Integer.toString(curFirst+6)+"| "+courseInfo[gender][curFirst+5]+"| "+courseInfo[0][curFirst+5]+"||| "+score[curFirst+5].toString());
            }
            case 8:
            {
                h9.setVisibility(View.INVISIBLE);
                h1.setText(Integer.toString(curFirst)+"| "+courseInfo[gender][curFirst-1]+"| "+courseInfo[0][curFirst-1]+"||| "+score[curFirst-1].toString());
                h2.setText(Integer.toString(curFirst+1)+"| "+courseInfo[gender][curFirst]+"| "+courseInfo[0][curFirst]+"||| "+score[curFirst].toString());
                h3.setText(Integer.toString(curFirst+2)+"| "+courseInfo[gender][curFirst+1]+"| "+courseInfo[0][curFirst+1]+"||| "+score[curFirst+1].toString());
                h4.setText(Integer.toString(curFirst+3)+"| "+courseInfo[gender][curFirst+2]+"| "+courseInfo[0][curFirst+2]+"||| "+score[curFirst+2].toString());
                h5.setText(Integer.toString(curFirst+4)+"| "+courseInfo[gender][curFirst+3]+"| "+courseInfo[0][curFirst+3]+"||| "+score[curFirst+3].toString());
                h6.setText(Integer.toString(curFirst+5)+"| "+courseInfo[gender][curFirst+4]+"| "+courseInfo[0][curFirst+4]+"||| "+score[curFirst+4].toString());
                h7.setText(Integer.toString(curFirst+6)+"| "+courseInfo[gender][curFirst+5]+"| "+courseInfo[0][curFirst+5]+"||| "+score[curFirst+5].toString());
                h8.setText(Integer.toString(curFirst+7)+"| "+courseInfo[gender][curFirst+6]+"| "+courseInfo[0][curFirst+6]+"||| "+score[curFirst+6].toString());
            }
            default:
            {
                h1.setText(Integer.toString(curFirst)+"| "+courseInfo[gender][curFirst-1]+"| "+courseInfo[0][curFirst-1]+"||| "+score[curFirst-1].toString());
                h2.setText(Integer.toString(curFirst+1)+"| "+courseInfo[gender][curFirst]+"| "+courseInfo[0][curFirst]+"||| "+score[curFirst].toString());
                h3.setText(Integer.toString(curFirst+2)+"| "+courseInfo[gender][curFirst+1]+"| "+courseInfo[0][curFirst+1]+"||| "+score[curFirst+1].toString());
                h4.setText(Integer.toString(curFirst+3)+"| "+courseInfo[gender][curFirst+2]+"| "+courseInfo[0][curFirst+2]+"||| "+score[curFirst+2].toString());
                h5.setText(Integer.toString(curFirst+4)+"| "+courseInfo[gender][curFirst+3]+"| "+courseInfo[0][curFirst+3]+"||| "+score[curFirst+3].toString());
                h6.setText(Integer.toString(curFirst+5)+"| "+courseInfo[gender][curFirst+4]+"| "+courseInfo[0][curFirst+4]+"||| "+score[curFirst+4].toString());
                h7.setText(Integer.toString(curFirst+6)+"| "+courseInfo[gender][curFirst+5]+"| "+courseInfo[0][curFirst+5]+"||| "+score[curFirst+5].toString());
                h8.setText(Integer.toString(curFirst+7)+"| "+courseInfo[gender][curFirst+6]+"| "+courseInfo[0][curFirst+6]+"||| "+score[curFirst+6].toString());
                h9.setText(Integer.toString(curFirst+8)+"| "+courseInfo[gender][curFirst+7]+"| "+courseInfo[0][curFirst+7]+"||| "+score[curFirst+7].toString());
            }

        }

    }
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public void colorMeShit()
    {
        //todo highlight current row
        switch (curFirst%9) {
            case 1:
            {

            }
            case 2:
            {

            }
            case 3:
            {

            }
            case 4:
            {

            }
            case 5:
            {

            }
            case 6:
            {

            }
            case 7:
            {

            }
            case 8:
            {

            }
            default:
            {

            }
        }
    }
}

