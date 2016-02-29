package com.nutrons.lib;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;

/**
 * 
 * @author John Zhang
 *
 */
public class Ultrasonic {
	
	private AnalogInput input;
	private DigitalOutput output;
	private final double SCALE = 0.0098; // 9.8 mV/in
	
	public Ultrasonic(int analogPort, int RXPort) {
		input = new AnalogInput(analogPort);
		output = new DigitalOutput(RXPort);
		output.set(true);
	}
	
	/**
	 * Gets ultrasonic distance
	 * @return Distance to object
	 */
	public double getDistance() {
		return input.getVoltage() / SCALE;
	}
}