package com.teamgolf.golfdb;

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
}


