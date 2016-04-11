package com.nutrons.stronghold.autos;



import com.nutrons.stronghold.commands.arm.MoveArmToHoldSafeModeCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveDistanceTimeBackwardsPIDCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveDistanceTimePIDCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author basedTaha
 *
 */

public class LowBarSafeDriveAutoBackwards extends CommandGroup{

	public LowBarSafeDriveAutoBackwards() {
    	addParallel(new DriveDistanceTimeBackwardsPIDCmd(0.25));
    	addSequential(new MoveArmToIntakePositionCmd());
    	addSequential(new MoveArmToHoldSafeModeCmd());
    	addSequential(new DriveDistanceTimeBackwardsPIDCmd(1.90));
    }
}
