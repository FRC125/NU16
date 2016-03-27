package com.nutrons.stronghold.autos;

import com.nutrons.stronghold.commands.arm.MoveArmToHoldSafeModeCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToShootingPosition;
import com.nutrons.stronghold.commands.drivetrain.DriveDistanceTimePIDCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnToAngleCmd;
import com.nutrons.stronghold.commands.drivetrain.auto.Aim;
import com.nutrons.stronghold.commands.shooter.FireBall;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RockWallNoAngleOneBallAuto extends CommandGroup {
    
    public  RockWallNoAngleOneBallAuto() {
    	
    	addSequential(new LowBarSafeDriveAuto());
    	addSequential(new WaitCommand(0.25));
    	addSequential(new MoveArmToShootingPosition());
    	addSequential(new WaitCommand(0.25));
    	addSequential(new Aim());
    	addSequential(new WaitCommand(0.25));
    	addSequential(new FireBall());
    	addSequential(new WaitCommand(1.0));
    	addSequential(new MoveArmToIntakePositionCmd());
    	
    }
}
