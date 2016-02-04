package com.nutrons.lib.utils;

/**
 * 
 * @author John Zhang
 *
 */

public class MovingAverage {
	
	int index = 0;
	double[] readings;
	int sampleSize;

	MovingAverage(int sampleSize) {
		readings = new double[sampleSize];
	}

	/**
	 * 
	 * @param The new reading coming in
	 */
	public void update(double newReading) {
		newReading = readings[index % sampleSize];
	}

	/**
	 * 
	 * @param The new reading coming in
	 * @return Moving average of current readings
	 */
	public double getMovingAverage(double newReading) {
		update(newReading);
		double sum = 0.0;
		for (int i = 0; i < sampleSize; i++) {
			sum += readings[i];
		}
		return sum / sampleSize;
	}
}