package com.nutrons.stronghold.commands.drivetrain.auto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.commands.drivetrain.DrivePathCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnToAngleCmd;
import com.team254.lib.trajectory.io.TextFileDeserializer;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DriveAuto extends CommandGroup {
    
	
	private StringBuilder sb = new StringBuilder();
    
	public  DriveAuto(){
    	File file = new File("/home/lvuser/NUTRONS_TEST.txt");
    	
    	FileReader fileReader;
		try {
			fileReader = new FileReader(file);
			
			BufferedReader bufferReader = new BufferedReader(fileReader);
	    	String lineBuffer;
	    	while((lineBuffer = bufferReader.readLine()) != null) {
	    		sb.append(lineBuffer);
	    		sb.append("\n");
	        } 
	    	
	    	TextFileDeserializer deserializer = new TextFileDeserializer();
	    	
	    	addSequential(new DrivePathCmd(deserializer.deserialize(sb.toString())));
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

