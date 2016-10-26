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
    public TextView hole1Text;
    public TextView hole2Text;
    public TextView hole3Text;
    public TextView hole4Text;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_round);

        if (firstRun==1) {
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

        }
    }
    public void prev(View v)
    {
        TextView hole1Text=(TextView) findViewById(R.id.br_h1);
        TextView hole2Text=(TextView) findViewById(R.id.br_h2);
        TextView hole3Text=(TextView) findViewById(R.id.br_h3);
        TextView hole4Text=(TextView) findViewById(R.id.br_h4);
    }


    public void next(View v)
    {
        TextView hole1Text=(TextView) findViewById(R.id.br_h1);
        TextView hole2Text=(TextView) findViewById(R.id.br_h2);
        TextView hole3Text=(TextView) findViewById(R.id.br_h3);
        TextView hole4Text=(TextView) findViewById(R.id.br_h4);
    }
}
