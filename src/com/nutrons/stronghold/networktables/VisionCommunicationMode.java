package com.nutrons.stronghold.networktables;

/** @author Asher Gottlieb
 *  each mode specifies which data the coprocessor should update
 *  each enum type corresponds with a class
 *  (ALL tells the coprocessor to update all data)
 * **/
public enum VisionCommunicationMode {
	ALIGNMENT(0), RELATIVETARGET(1), ALL(2);
	private int ID;
	
	private VisionCommunicationMode(int ID){
		this.ID = ID;
	}
	
	public int ID(){
		return this.ID;
	}
	
}
