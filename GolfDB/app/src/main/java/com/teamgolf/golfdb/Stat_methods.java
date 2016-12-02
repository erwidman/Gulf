package com.teamgolf.golfdb;

import android.util.Log;

/**
 * Created by Robby Reid on 11/11/2016.
 */

public class Stat_methods {



	public static double[] Percentages(int roundInformation[][], int courseInformation[][]) {

		// rows are par, mens, womens, children in order
		int roundTotal[] = new int[roundInformation.length];
		double[] percentages = new double[6];

		for (int row = 0; row < roundInformation.length; row++) {
			for (int column = 0; column < roundInformation[0].length; column++) {

				int par = courseInformation[0][column];
				int strokes = roundInformation[row][column];
				roundTotal[row] += strokes;

				if (par - strokes == 2) {
					//eagles
					percentages[0] += 1.0;
				} else if (par - strokes == 1) {
					//birdies
					percentages[1] += 1.0;
				} else if (par - strokes == 0) {
					//par
					percentages[2] += 1.0;
				}else if (par - strokes == -1){
					//beyond
					percentages[3] += 1.0;
				}else if (par - strokes <= -2){
					//beyond
					percentages[4] += 1.0;
				} else if (strokes == 1){
					//hole in one
					percentages[5] +=1.0;
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			percentages[i] = percentages[i]
					/ (roundInformation.length * roundInformation[0].length);
		}
		return percentages;
	}

	public static double Handicap(int roundInformation[][], int courseInformation[][]) {
		int averageOverPar = 0;
		int roundTotal[] = new int[roundInformation.length];

		// Calculates round total
		for (int row = 0; row < roundInformation.length; row++) {
			for (int column = 0; column < roundInformation[0].length; column++) {
				roundTotal[row] += roundInformation[row][column];
			}
		}

		int CoursePar = Stat_methods.coursePar(courseInformation);

		if (CoursePar != 0) {
			for (int i = 0; i < roundTotal.length; i++) {
				averageOverPar += roundTotal[i] - CoursePar;
			}
		}
		averageOverPar = averageOverPar / roundTotal.length;
		return averageOverPar * .8;
	}





	//------------------------------------------------------------------------------------------------------------
	public static int TimesPlayed(int [] [] input){
		return input.length;
	}
	public static double averageRoundScore(int [] [] input){
		double average = 0;
		double [] averages = new double[input.length];
		int index = 0;
		for(int i = 0; i<input.length; i++){
			for(int j = 0; j < input[0].length; j++) {
				average += input[i][j];
			}
			averages[index] = average;
			index++;
			average = 0;
		}

		double numerator = 0.0;
		double denomenator = input.length;
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


