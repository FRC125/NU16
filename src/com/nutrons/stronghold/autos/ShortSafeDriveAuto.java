package com.nutrons.stronghold.autos;

import com.nutrons.stronghold.commands.arm.MoveArmToHoldSafeModeCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveDistanceTimePIDCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShortSafeDriveAuto extends CommandGroup {
    
    public  ShortSafeDriveAuto() {
    	addParallel(new DriveDistanceTimePIDCmd(0.25));
    	addSequential(new MoveArmToIntakePositionCmd());
    	
    	addSequential(new MoveArmToHoldSafeModeCmd());
    	addSequential(new DriveDistanceTimePIDCmd(1.65));
    	
    }
}
