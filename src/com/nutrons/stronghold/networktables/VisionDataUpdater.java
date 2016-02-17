package com.nutrons.stronghold.networktables;

import com.nutrons.stronghold.RobotMap;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * @author Asher Gottlieb
 * This class runs in a second thread and updates the
 * DataController's instance variables from the NetworkTables
 * **/
public class VisionDataUpdater implements Runnable{
	private DataController dc;
	private VisionCommunicationMode mode;
	private boolean continueChecking;
	private NetworkTable network;
	private int updateFrequency;
	
	/** @param dc the DataController to update 
	 *  @param network the networktable where vision related data is stored
	 *  @param mode the type of data to request and update
	 * **/
	public VisionDataUpdater(DataController dc, NetworkTable network, VisionCommunicationMode mode){
		this.dc = dc;
		this.mode = mode;
		this.network = network;
		this.updateFrequency = RobotMap.UPDATE_FREQUENCY;
	}
	
	/** invoke this to stop the Thread **/
	public void stopChecking(){
		this.continueChecking = false;
	}
	
	public void run() {
		this.network.putNumber("whichData", this.mode.ID());
		this.continueChecking = true;
		this.network.putBoolean("updateInProgress", true);
		while(continueChecking){
			if(!this.network.getBoolean("updateInProgress", false)){
				switch(this.mode){
					case ALIGNMENT:
						updateAlignmentData();
					case RELATIVETARGET:
						updateRelativeTargetData();
					case ALL:
						updateAlignmentData();
						updateRelativeTargetData();
				}
				this.network.putBoolean("updateInProgress", true);
			}
			try {
				Thread.sleep(this.updateFrequency);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** updates the DataController's AlignmentData **/
	private void updateAlignmentData(){
		ITable datatable = this.network.getSubTable(RobotMap.VISION_DATA);
		dc.alignmentData = new AlignmentData(datatable.getBoolean("canSee", false), (int) datatable.getNumber("offsetFromCenter", 0));
	}
	
	/** updates the DataController's RelativeTargetData **/
	private void updateRelativeTargetData(){
		ITable datatable = this.network.getSubTable(RobotMap.VISION_DATA);
		dc.relativeTargetData = new RelativeTargetData(datatable.getNumber("distance", 0), datatable.getNumber("angle", 0));
	}
}
