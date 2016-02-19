package com.nutrons.lib;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Camera {
	
	private NetworkTable table;
	
	public Camera(NetworkTable table) {
		this.table = table;
	}
	
	public double getAngle() {
		return this.table.getNumber("angle", -1.0);
	}
	
	public double getDistance() {
		return this.table.getNumber("distance", -1.0);
	}
}