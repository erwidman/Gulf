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

				int par = courseInformation[0][column];
				int strokes = roundInformation[row][column];
				roundTotal[row] += strokes;

				if (par - strokes == 1) {
					percentages[0] += 1.0;
				} else if (par - strokes == 0) {
					percentages[1] += 1.0;
				} else if (par - strokes == -1) {
					percentages[2] += 1.0;
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			percentages[i] = percentages[i]
					/ (roundInformation.length * roundInformation[0].length);
		}
		return percentages;
	}

	public double Handicap() {
		int averageOverPar = 0;
		if (CoursePar != 0) {
			for (int i = 0; i < roundTotal.length; i++) {
				averageOverPar += roundTotal[i] - CoursePar;
			}
		}
		averageOverPar = averageOverPar / roundTotal.length;
		return averageOverPar * .8;
	}
	
	public int Par () {
		CoursePar=0;
		for(int column = 0; column < courseInformation[0].length; column++){
			CoursePar+=courseInformation[0][column];
		}
		
		return CoursePar;
	}

	public Stat_methods(int[][] roundInformation, int[][] courseInformation) {
		this.roundInformation = roundInformation;
		this.courseInformation = courseInformation;
	}

}
