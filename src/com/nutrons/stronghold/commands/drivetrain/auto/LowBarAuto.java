package com.nutrons.stronghold.commands.drivetrain.auto;

import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToPositionCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveDistanceCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveMotionProfileCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveTimeCmd;
import com.nutrons.stronghold.controllers.OverTerrainDefenceProfile;
import com.nutrons.stronghold.controllers.UnderLowBarProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class LowBarAuto extends CommandGroup {
    
    public  LowBarAuto() {
    	addSequential(new MoveArmToIntakePositionCmd());
    	addSequential(new DriveTimeCmd(3.0));
    }
}