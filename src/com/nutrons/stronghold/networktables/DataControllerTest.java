package com.nutrons.stronghold.networktables;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/** @author Asher Gottlieb 
 *  This allows DataController to run locally for testing purposes,
 *  however since NetworkTables.jar is a wrapper for a compiled library
 *  which only works on the RoboRIO, the desktop version of
 *  NetworkTables.jar must be used. 
 *   **/
public class DataControllerTest {

	public static void main(String[] args) {
		NetworkTable.setServerMode();
		NetworkTable.initialize();
		NetworkTable network = NetworkTable.getTable("visionTable");
		network.getSubTable("data").putBoolean("testBoolean", true);
		DataController dc = new DataController(network,5000);
		dc.startRequesting(VisionCommunicationMode.ALIGNMENT);
		
		while(true){
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			try{
				AlignmentData data = dc.getAlignmentData();
				System.out.println("canSee: "+data.canSee()+" offsetFromCenter: "+data.offestFromCenter() + " age: " + data.age());
			}catch(ExpiredDataException e){
				System.out.println("Data Expired");
			}
			
		}

	}

}
