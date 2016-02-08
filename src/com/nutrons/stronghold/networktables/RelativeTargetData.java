package com.nutrons.stronghold.networktables;

/** @author Asher Gottlieb 
 *  stores distance, angle, and its age **/
public class RelativeTargetData {
	private double distance;
	private double angle;
	private long creationTime;
	
	public RelativeTargetData(double distance, double angle, long creationTime){
		this.distance = distance;
		this.angle = angle;
		this.creationTime = creationTime;
	}
	
	public RelativeTargetData(double distance, double angle) {
		this(distance, angle, System.currentTimeMillis());
	}
	
	public RelativeTargetData(RelativeTargetData data) {
		this(data.distance(),data.angle(),data.creationTime());
	}

	public long creationTime(){
		return this.creationTime;
	}
	
	public long age(){
		return System.currentTimeMillis()-creationTime;
	}
	
	public double distance() {
		return distance;
	}

	public double angle() {
		return angle;
	}	
}
