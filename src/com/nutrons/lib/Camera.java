package com.nutrons.lib;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Camera {
	
	private NetworkTable table;
	
	public Camera(NetworkTable table) {
		this.table = table;
	}
	
	/**
	 * Gets camera angle offset
	 * @return Angle offset to target
	 */
	public double getAngle() {
		return this.table.getNumber("angle", -1.0);
	}
	
	/**
	 * Gets distance to target
	 * @return Distance to target
	 */
	public double getDistance() {
		return this.table.getNumber("distance", -1.0);
	}
}