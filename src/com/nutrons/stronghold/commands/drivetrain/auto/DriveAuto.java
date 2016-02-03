package com.nutrons.stronghold.commands.drivetrain.auto;

import com.nutrons.stronghold.commands.drivetrain.DriveMotionProfileCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DriveAuto extends CommandGroup {
    
	public  DriveAuto() {
		addSequential(new DriveMotionProfileCmd());
	}
}