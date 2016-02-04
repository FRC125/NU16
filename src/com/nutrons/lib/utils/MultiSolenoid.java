/**
 * 
 */
package com.nutrons.lib.utils;

import edu.wpi.first.wpilibj.Solenoid;

/**
 * @author Andreas Leonard- Calcano and Abdulrahim Mezrouh
 *
 */

public class MultiSolenoid {
	private Solenoid solenoids[];

	MultiSolenoid(int[] ports) {
		Solenoid[] solenoids = new Solenoid[ports.length];
		for (int i = 0; i < ports.length; i++) {
			solenoids[i] = new Solenoid(ports[i]);
		}
	}
	
	/**
	 * deploy all pistons
	 */
	
	public void deployMultiSolenoids() {
		for (int i = 0; i < solenoids.length; i++) {
			solenoids[i].set(true);
		}
	}
	
	/**
	 * retract all pistons
	 */
	public void retractMultiSolenoids() {
		for (int i = 0; i < solenoids.length; i++) {
			solenoids[i].set(false);
		}
	}

}
