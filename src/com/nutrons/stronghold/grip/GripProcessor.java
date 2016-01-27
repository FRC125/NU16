package com.nutrons.stronghold.grip;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GripProcessor {
	private static final int gripForceTerminateDelay = 10000;
	
	private Process process;
	private String profileLocation;
	private String javaLocation;

	private String gripLocation;
	
	public GripProcessor(String javaLocation, String gripLocation, String profileLocation){
		this.javaLocation = javaLocation;
		this.profileLocation = profileLocation;
		this.gripLocation = gripLocation;
	}
	/** starts grip **/
	public boolean startGrip(){
		try {
			this.process = Runtime.getRuntime().exec(new String[]{this.javaLocation, "-jar", this.gripLocation, this.profileLocation});
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	} 
	/** stops grip, after a delay will forcibly terminate grip **/
	public void stopGrip(){ 
		this.process.destroy();
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				process.destroyForcibly();
			}
		}, gripForceTerminateDelay);
	}
}
