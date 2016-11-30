package com.teamgolf.golfdb;

/**
 * Created by ben on 14/11/16.
 */

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

public class advancedround extends AppCompatActivity {

    TextView[] H = new TextView[9];
    int [] holeTotals;
    int totalTotal;
    int gender;
    Spinner cSpinner;
    String [][] score;
    int [] daLengths;
    int [][]intScore;
    String selected;
    TextView [] tView;
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
        setContentView(R.layout.activity_advancedround);
        numHoles=Constants.holeLoaded[0].length;
        Spinner dropdown = (Spinner)findViewById(R.id.ar_clubspinner);
        String[] items = new String[]{"Putter", "2 iron", "3 iron", "4 iron", "5 iron", "6 iron","7 iron", "8 iron", "9 iron", "wedge", "Sand Wedge", "Fairway wood", "Driver"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        cSpinner=dropdown;




        //Spinner gender_dropdown = (Spinner)findViewById(R.id.ar_spinner);
        //String[] gender_items = new String[]{"Putter", "2 iron", "3 iron", "4 iron", "5 iron", "6 iron","7 iron", "8 iron", "9 iron", "wedge", "Sand Wedge", "Fairway wood", "Driver"};
        //ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, gender_items);
        //dropdown.setAdapter(adapter2);


        holeTotals=new int[numHoles];
        score = new String [numHoles][14];
        intScore = new int [numHoles][14];
        for (int i =0; i<numHoles;i++)
        {
            for (int j=0;j<14;j++)
            {
                intScore[i][j]=-1;
            }
        }
        tView=new TextView[9];
        currentLine=1;
        curFirst=1;
        gender=1;
        courseInfo=Constants.holeLoaded;
        daLengths=new int[numHoles];
        for (int i =0;i<numHoles;i++)
        {
            daLengths[i]=0;
        }

        tView[0]=(TextView)findViewById(R.id.ar_h1t);
        tView[1]=(TextView)findViewById(R.id.ar_h2t);
        tView[2]=(TextView)findViewById(R.id.ar_h3t);
        tView[3]=(TextView)findViewById(R.id.ar_h4t);
        tView[4]=(TextView)findViewById(R.id.ar_h5t);
        tView[5]=(TextView)findViewById(R.id.ar_h6t);
        tView[6]=(TextView)findViewById(R.id.ar_h7t);
        tView[7]=(TextView)findViewById(R.id.ar_h8t);
        tView[8]=(TextView)findViewById(R.id.ar_h9t);



        Log.d("CousreInfo:", Integer.toString(Constants.holeLoaded[0].length));
        H[0] = (TextView)findViewById(R.id.ar_h1);
        H[1] = (TextView)findViewById(R.id.ar_h2);
        H[2] = (TextView)findViewById(R.id.ar_h3);
        H[3] = (TextView)findViewById(R.id.ar_h4);
        H[4] = (TextView)findViewById(R.id.ar_h5);
        H[5] = (TextView)findViewById(R.id.ar_h6);
        H[6] = (TextView)findViewById(R.id.ar_h7);
        H[7] = (TextView)findViewById(R.id.ar_h8);
        H[8] = (TextView)findViewById(R.id.ar_h9);


        cSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("da CLUB is :", (String) parent.getItemAtPosition(position));
                selected=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        for (int i =0; i <numHoles;i++)
        {
            for (int j=0;j<14;j++)
            {
                score[i][j]="";
            }
        }
        drawScore();
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public void addStroke(View v)
    {
        //this is for when you add a stroke to the scorecard
        String str = Integer.toString(cSpinner.getSelectedItemPosition());
        Log.d("Club is :", Integer.toString((numHoles-curFirst)+1));

        //"Putter", "2 iron", "3 iron", "4 iron", "5 iron", "6 iron","7 iron", "8 iron", "9 iron", "wedge", "Sand Wedge", "Fairway wood", "Driver"
        switch(selected)
        {
            //in int 0 = driver; 1= fw wood; 2-9 = respective iron; 10 = wedge; 11 = Sand Wedge; 12 = putter;
            // in string D = dr # = iron sw = sand wedge w = wedge p = putter fw = fairway wood
            case "Putter":
            {
                score[currentLine-1][curHoleStroke]="P";
                intScore[currentLine-1][curHoleStroke]=12;
                break;
            }
            case "2 iron":
            {
                score[currentLine-1][curHoleStroke]="2";
                intScore[currentLine-1][curHoleStroke]=2;
                break;
            }
            case "3 iron":
            {
                score[currentLine-1][curHoleStroke]="3";
                intScore[currentLine-1][curHoleStroke]=3;
                break;
            }
            case "4 iron":
            {
                score[currentLine-1][curHoleStroke]="4";
                intScore[currentLine-1][curHoleStroke]=4;
                break;
            }
            case "5 iron":
            {
                score[currentLine-1][curHoleStroke]="5";
                intScore[currentLine-1][curHoleStroke]=5;
                break;
            }
            case "6 iron":
            {
                score[currentLine-1][curHoleStroke]="6";
                intScore[currentLine-1][curHoleStroke]=6;
                break;
            }
            case "7 iron":
            {
                score[currentLine-1][curHoleStroke]="7";
                intScore[currentLine-1][curHoleStroke]=7;
                break;
            }
            case "8 iron":
            {
                score[currentLine-1][curHoleStroke]="8";
                intScore[currentLine-1][curHoleStroke]=8;
                break;
            }
            case "9 iron":
            {
                score[currentLine-1][curHoleStroke]="9";
                intScore[currentLine-1][curHoleStroke]=9;
                break;
            }
            case "wedge":
            {
                score[currentLine-1][curHoleStroke]="W";
                intScore[currentLine-1][curHoleStroke]=10;
                break;
            }
            case "Sand Wedge":
            {
                score[currentLine-1][curHoleStroke]="SW";
                intScore[currentLine-1][curHoleStroke]=11;
                break;
            }
            case "Fairway wood":
            {
                score[currentLine-1][curHoleStroke]="FW";
                intScore[currentLine-1][curHoleStroke]=1;
                break;
            }
            case "Driver":
            {
                score[currentLine-1][curHoleStroke]="D";
                intScore[currentLine-1][curHoleStroke]=0;
                break;
            }
        }
        curHoleStroke++;
        drawScore();
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
            curHoleStroke=daLengths[currentLine-1];
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
        curHoleStroke=daLengths[currentLine];
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public void nextHole(View v)
    {
        daLengths[currentLine-1]=curHoleStroke;
        if (currentLine-curFirst==8 &&currentLine!=numHoles)
        {
            curFirst+=9;
            currentLine++;
            drawScore();

        }
        else if (currentLine-curFirst==8 || currentLine==numHoles)
        {
            return;
        }
        else
        {
            currentLine++;
            drawScore();
        }
        curHoleStroke=daLengths[currentLine-1];
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public void backspace(View v)
    {
        //for when you hit backspace
    }
    public void submit(View V)
    {
        if(currentLine==numHoles)
        {
            Constants.dbHandler.insertAdvancedScore(ericLikesSmallArrays());
            Intent intent = new Intent(advancedround.this, MainScreen.class);
            startActivity(intent);
        }
    }

    public int [] ericLikesSmallArrays() {
        int giant1DArray[] = new int[numHoles * 14];
        int used = 0;
        for (int i = 0; i < numHoles; i++) {
            for (int j = 0; j < numHoles; j++) {
                if (intScore[i][j] == -1) {
                    giant1DArray[used] = -1;
                    used++;
                    break;
                } else {
                    giant1DArray[used] = intScore[i][j];
                    used++;
                }
            }

        }
        int toReturn[] = new int[used];
        for (int i = 0; i < used; i++)
        {
            toReturn[i] = giant1DArray[i];
            Log.d("Array to Eric's garbage",Integer.toString(toReturn[i]));
        }
        Log.d("Array to Eric's garbage",toReturn.toString());
        return toReturn;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public void drawScore()
    {
        //row 0 = par
        //row 1 = men
        //row 2 = women
        //row 3 = child
        colorMeShit();
        generateTotals();
        String l = courseInfo[0][0];
        Log.d("# Lines to Display:", Integer.toString((numHoles-curFirst)+1));
        for (int i =0;i<((numHoles-curFirst)+1);i++)
        {
            H[i].setVisibility(View.VISIBLE);
            H[i].setText(Integer.toString(curFirst+i)+"| "+courseInfo[gender][curFirst-1+i]+" yds  |  par "+courseInfo[0][curFirst-1+i]+" | Clubs: "+ Arrays.toString(score[curFirst-1+i]).replace(',',' '));
            tView[i].setVisibility(View.VISIBLE);
            int holeTotal=0;
            for (int j=0;j<100;j++)
            {
                if (intScore[i][j] <0||intScore[i][j]>12){break;}
                holeTotal+=1;
            }
            tView[i].setText(Integer.toString(holeTotal));
        }
        for (int i =(numHoles-curFirst)+1;i<9;i++) {
            H[i].setVisibility(View.INVISIBLE);
            tView[i].setVisibility(View.INVISIBLE);
        }


    }
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public void colorMeShit()
    {
        if (currentLine==numHoles)
        {
            ((Button) findViewById(R.id.ar_submit)).setText("Submit Round");
        }
        else
        {
            ((Button) findViewById(R.id.ar_submit)).setText("Running Stats");
        }
         //todo highlight current row
        for (int i=0;i<9;i++)
        {
            if (i==((currentLine%9)-1))
            {
                H[i].setBackgroundColor(Color.CYAN);
            }
            else
            {
                H[i].setBackgroundColor(Color.WHITE);
            }
        }
    }

    public void generateTotals()
    {
        int total;
        int rTotal=0;
        for (int i =0; i<curFirst%9; i++)
        {
            total=0;
            for (int j = 0; j < 14; j++)
            {
                if (score[i][j].equals(""))
                {
                    break;
                }
                total +=1; rTotal+=1;
            }
            tView[i].setVisibility(View.VISIBLE);
            tView[i].setText(Integer.toString(total));
        }

        for (int i =curFirst%9; i<9; i++)
        {
            tView[i].setVisibility(View.INVISIBLE);
        }
        ((TextView)findViewById(R.id.ar_rdt)).setText(Integer.toString(rTotal));
    }
}

