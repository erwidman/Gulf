package com.teamgolf.golfdb;

/**
 * Created by Robby Reid on 11/11/2016.
 */

public class Stat_methods {
    private int roundInformation[][];
    private int courseInformation[];
    private int roundTotal[];
    int CoursePar = 0;

    public int AverageRoundScore()
    {
        int average = 0;
        int totalHoles =  roundInformation.length * roundInformation[0].length;
        roundTotal = new int[roundInformation.length];
        for (int row= 0; row < roundInformation[0].length; row++)
        {
            for (int column = 0; column < roundInformation.length; column++) {
                average += roundInformation[row][column];
                roundTotal[row]+=roundInformation[row][column];
            }
        }
        return average / totalHoles;
    }
    public int TimesPlayed (){
        return roundTotal.length;
    }
    public int[] Percentages()
    {
        int[] percentages = new int[3];

        for (int row= 0; row < roundInformation[0].length; row++)
        {
            for (int column = 0; column < roundInformation.length; column++) {
                int par = courseInformation[column];
                CoursePar+=par;
                int strokes = roundInformation[row][column];

                if(par-strokes == 1){
                    percentages[0]+=1;
                }else if(par-strokes == 0){
                    percentages[1] +=1;
                }else if(par-strokes ==-1){
                    percentages[2]+=1;
                }
            }
        }

        for (int i =0; i <3; i++){
            percentages[i] = percentages[i] / (roundInformation.length * roundInformation[0].length);
        }
        return percentages;
    }
/*
<<<<<<< HEAD
    public double Handicap(){
    	int averageOverPar = 0;
    	if(CoursePar!=0){
    		for(int i = 0; i < roundTotal.length; i++){
    			averageOverPar+=roundTotal[i] - CoursePar;
    		}
    	}
    	averageOverPar = averageOverPar / roundTotal.length;
    	return averageOverPar*.8;
    }/*
=======
//    public int Handicap(){
//    	int averageOverPar = 0;
//    	if(CoursePar!=0){
//    		for(int i = 0; i < roundTotal.length; i++){
//    			averageOverPar+=roundTotal[i] - coursePar;
//    		}
//    	}
//    	averageOverPar = averageOverPar / roundTotal.length;
//    	return averageOverPar*.8;
//    }
>>>>>>> a1966f8a60ce1a24df01602950a7331dbf14eb23
*/
    public void Stat_methods(int[][] roundInformation,int[]  courseInformation){
        this.roundInformation = roundInformation;
        this.courseInformation = courseInformation;
    }
    public void Stat_methods(int[][] roundInformation){
        this.roundInformation=roundInformation;
    }
}
