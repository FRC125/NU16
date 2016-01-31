package com.nutrons.lib.utils;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Camera {
	
	private NetworkTable table;
	
	public Camera(NetworkTable table) {
		
	}
	
	public double getDistanceToTargetInFeet() {
		return table.getNumber("distance", 0.0);
	}
	
	public double getAngleToTarget() {
		return table.getNumber("angle", 0.0);
	}
}
