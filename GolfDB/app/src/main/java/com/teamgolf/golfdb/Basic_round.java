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
    private int curHole;
    private int firstRun=1;
    public TextView hole1Text;
    public TextView hole2Text;
    public TextView hole3Text;
    public TextView hole4Text;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_round);

        if (firstRun==1)
        {
            TextView hole1Text=(TextView) findViewById(R.id.br_hole1);
            TextView hole2Text=(TextView) findViewById(R.id.br_hole2);
            TextView hole3Text=(TextView) findViewById(R.id.br_hole3);
            TextView hole4Text=(TextView) findViewById(R.id.br_hole4);
            this.curHole=1;
            firstRun=0;
            char [] a = {' ', ' '};
            a[0]=1;
            hole1Text.setText(a,0,1);
            a[0]=2;
            hole2Text.setText(a,0,1);
            a[0]=3;
            hole3Text.setText(a,0,1);
            a[0]=4;
            hole4Text.setText(a,0,1);

        }
    }
    public void prev(View v)
    {
        TextView hole1Text=(TextView) findViewById(R.id.br_hole1);
        TextView hole2Text=(TextView) findViewById(R.id.br_hole2);
        TextView hole3Text=(TextView) findViewById(R.id.br_hole3);
        TextView hole4Text=(TextView) findViewById(R.id.br_hole4);
    }


    public void next(View v)
    {
        TextView hole1Text=(TextView) findViewById(R.id.br_hole1);
        TextView hole2Text=(TextView) findViewById(R.id.br_hole2);
        TextView hole3Text=(TextView) findViewById(R.id.br_hole3);
        TextView hole4Text=(TextView) findViewById(R.id.br_hole4);
    }
}
