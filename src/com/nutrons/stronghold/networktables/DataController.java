package com.nutrons.stronghold.networktables;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/** @author Asher Gottlieb
 *  This class is the interface between the main RoboRIO loop
 *  and NetworkTables access vision data
 * **/
public class DataController {

	
	NetworkTable network;
	
	volatile AlignmentData alignmentData;
	volatile RelativeTargetData relativeTargetData;

	private VisionDataUpdater updater;
	private Thread updaterThread;
	
	private int defaultExpirationTime;
	
	/** @param network the networktable where vision related data is stored
	 *  @param defaultExpirationTime the expiration time used when retrieving data without specifying an expiration time
	 * **/
	public DataController(NetworkTable network, int defaultExpirationTime){
		this.network = network;
		this.alignmentData = new AlignmentData(false, 0, 0);
		this.relativeTargetData = new RelativeTargetData(0, 0, 0);
		this.defaultExpirationTime = defaultExpirationTime;
		this.updater = new VisionDataUpdater(this, network, VisionCommunicationMode.ALIGNMENT);
	}
	
	/** mode specifies the data the coprocessor should update,
	 *  more information in the enum type
	 *  **/
	public void startRequesting(VisionCommunicationMode mode){		
		this.updater.stopChecking();
		this.updater = new VisionDataUpdater(this, network, mode);
		this.updaterThread = new Thread(this.updater);
		this.updaterThread.start();
	}
	
	/** Stops requesting new data from the coprocessor **/
	public void stopRequesting(){
		this.updater.stopChecking();
	}
	
	/** Retrieves most recently collected AlignmentData  
	 *  @param expirationTime if the data's age is longer than this, an ExpiredDataException is thrown **/
	public AlignmentData getAlignmentData(int expirationTime) throws ExpiredDataException {
		if(this.alignmentData.age()>expirationTime){
			throw new ExpiredDataException();
		}
		return new AlignmentData(this.alignmentData);
	}
	
	/** Retrieves most recently collected RelativeTargetData
	 *  @param expirationTime if the data's age is longer than this, an ExpiredDataException is thrown **/
	public RelativeTargetData getRelativeTargetData(int expirationTime) throws ExpiredDataException {
		if(this.relativeTargetData.age()>expirationTime){
			throw new ExpiredDataException();
		}
		return new RelativeTargetData(this.relativeTargetData);
	}
	
	/** Retrieves most recently collected AlignmentData using defaultExpirationTime **/
	public AlignmentData getAlignmentData() throws ExpiredDataException {
		return this.getAlignmentData(this.defaultExpirationTime);
	}
	
	/** Retrieves most recently collected RelativeTargetData using defaultExpirationTime **/
	public RelativeTargetData getRelativeTargetData() throws ExpiredDataException{
		return this.getRelativeTargetData(this.defaultExpirationTime);
	}
	
}
