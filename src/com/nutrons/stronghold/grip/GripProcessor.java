package com.nutrons.stronghold.grip;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.nutrons.stronghold.RobotMap;

public class GripProcessor {
	private static final int gripForceTerminateDelay = 10000;
	
	private Process process;

	public boolean startGrip(String filename){
		try {
			this.process = Runtime.getRuntime().exec(new String[]{RobotMap.JAVA_LOCATION, "-jar", "/home/lvuser/grip.jar", filename});
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	} 
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
