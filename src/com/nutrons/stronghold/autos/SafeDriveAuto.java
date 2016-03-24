package com.nutrons.stronghold.autos;

import com.nutrons.stronghold.commands.arm.MoveArmToHoldSafeModeCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveDistanceTimePIDCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SafeDriveAuto extends CommandGroup {
    
    public  SafeDriveAuto() {
    	addParallel(new DriveDistanceTimePIDCmd(0.25));
    	addSequential(new MoveArmToIntakePositionCmd());
    	
    	addSequential(new MoveArmToHoldSafeModeCmd());
    	addSequential(new DriveDistanceTimePIDCmd(1.75));
    	
    }
}
