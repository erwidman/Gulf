package com.teamgolf.golfdb;

import android.util.Log;

/**
 * Created by Robby Reid on 11/11/2016.
 */

public class Stat_methods {
	private int roundInformation[][];

	// rows are par, mens, womens, children in order
	private int courseInformation[][];
	private int roundTotal[];
	private int CoursePar = 0;

	public double AverageRoundScore() {
		double average = 0;
		int totalHoles = roundInformation.length * roundInformation[0].length;
		roundTotal = new int[roundInformation.length];
		for (int row = 0; row < roundInformation.length; row++) {
			for (int column = 0; column < roundInformation[0].length; column++) {
				average += roundInformation[row][column] + 0.0;

				roundTotal[row] += roundInformation[row][column];
			}
		}
		return average / totalHoles;
	}

	public int TimesPlayed() {
		return roundTotal.length;
	}

	public double[] Percentages() {
		double[] percentages = new double[3];
		roundTotal = new int[roundInformation.length];
		for (int row = 0; row < roundInformation.length; row++) {
			for (int column = 0; column < roundInformation[0].length; column++) {

			}
		}
		return percentages;
	}

	public static int TimesPlayer(int [] [] input){
		return input.length;
	}
	public static double averageRoundScore(int [] [] input){
		double average = 0;
		double [] averages = new double[input.length];
		int index = 0;
		for(int i = 0; i<input.length; i++){
			for(int j = 0; j < input[0].length; j++) {
				Log.d("Tmp",Integer.toString(input[i][j]));
				average += input[i][j];
			}
			averages[index] = average;
			index++;
			average = 0;
		}

		double numerator = 0.0;
		double denomenator = input[0].length;
		for(int i = 0 ; i < averages.length; i++){
			numerator += averages[i];
		}

		return numerator/denomenator;
	}
	public static int coursePar(int [] [] input){
		int totalPar =0;
		for(int i = 0; i <input[0].length; i++){
			totalPar += input[0][i];
		}
		return totalPar;
	}

	public static int overallScore(int [] [] input){
		int total = 0;
		for(int i = 0; i < input.length; i++){
			for(int j = 0; j < input[0].length; j++){
				total += input[i][j];
			}
		}

		return total;
	}

	public static int aboveBelowPar(int [] [] round, int [] [] course){
		return overallScore(round)-coursePar(course);
	}



}


