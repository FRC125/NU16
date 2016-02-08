package com.nutrons.stronghold.networktables;

/** @author Asher Gottlieb
 *  stores canSee, offsetFromCenter, and its age **/
public class AlignmentData {
	private boolean canSee;
	private int offsetFromCenter;
	private long creationTime;
	
	public AlignmentData(boolean canSee, int offsetFromCenter, long creationTime){
		this.canSee = canSee;
		this.offsetFromCenter = offsetFromCenter;
		this.creationTime = creationTime;
	}
	
	public AlignmentData(boolean canSee, int offsetFromCenter){
		this(canSee, offsetFromCenter, System.currentTimeMillis());
	}
	
	public AlignmentData(AlignmentData data){
		this(data.canSee(),data.offestFromCenter(),data.creationTime());
	}
	
	public long creationTime(){
		return this.creationTime;
	}
	
	public long age(){
		return System.currentTimeMillis()-creationTime;
	}
	
	public boolean canSee(){
		return canSee;
	}
	
	public int offestFromCenter(){
		return offsetFromCenter;
	}
	
}
