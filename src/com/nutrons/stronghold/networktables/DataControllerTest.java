package com.nutrons.stronghold.networktables;

import javax.swing.JFrame;

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
		DataController dc = new DataController(network,5000);
		dc.startRequesting(VisionCommunicationMode.ALL);
		
		while(true){
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			try{
				AlignmentData adata = dc.getAlignmentData();
				RelativeTargetData rtdata = dc.getRelativeTargetData();
				System.out.print("canSee: " + clip(adata.canSee()) + " offsetFromCenter: " + clip(adata.offestFromCenter()));
				System.out.print("angle:  " + clip(rtdata.angle())+" distance: "+clip(rtdata.distance()) + "\r");
			}catch(ExpiredDataException e){
				System.out.print("Data Expired\r");
			}

		}

	}
	private static String clip(Object o){
		String s = String.valueOf(o);
		s += "     ";
		return s.substring(0,5);
	}

}
