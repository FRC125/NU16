package com.nutrons.stronghold.autos;

import com.nutrons.stronghold.commands.arm.ChangeArmSetpointCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToPositionWithTimeoutCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveDistancePIDCmd;
import com.nutrons.stronghold.commands.drivetrain.auto.Aim;
import com.nutrons.stronghold.commands.shooter.FireBall;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Asher
 *
 */
public class LowBarOneBallAuto extends CommandGroup {
    
    public  LowBarOneBallAuto() {
    	addSequential(new DriveDistancePIDCmd(10.0, 2.0));
    	addSequential(new MoveArmToPositionWithTimeoutCmd(-1800.0));
    	addSequential(new Aim());
    	addSequential(new FireBall());
    }
}