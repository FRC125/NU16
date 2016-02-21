package com.nutrons.stronghold.commands.drivetrain.auto;

import com.nutrons.stronghold.commands.arm.MoveArmToPositionCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveDistanceCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveInSaveModeAuto extends CommandGroup {
    
    public  DriveInSaveModeAuto() {
    	addParallel(new MoveArmToPositionCmd(-600.0));
    	addParallel(new DriveDistanceCmd(11.0));
    }
}
