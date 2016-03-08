package com.nutrons.stronghold.autos;

import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.commands.arm.ChangeArmSetpointCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveDistancePIDCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnToAngleCmd;
import com.nutrons.stronghold.commands.drivetrain.auto.Aim;
import com.nutrons.stronghold.commands.intake.SuckRollersCmd;
import com.nutrons.stronghold.commands.shooter.FireBall;
import com.nutrons.stronghold.commands.shooter.RetractShooterAndJaw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LowBarTwoBallAuto extends CommandGroup {
    
    public  LowBarTwoBallAuto() {
    	addSequential(new MoveArmToIntakePositionCmd());
    	addSequential(new DriveDistancePIDCmd(10.0, 2.0));
    	addSequential(new ChangeArmSetpointCmd(-1790.0));
    	addSequential(new Aim());
    	addSequential(new FireBall());

    	
    	addSequential(new RetractShooterAndJaw());
    	addSequential(new MoveArmToIntakePositionCmd());
    	addSequential(new TurnToAngleCmd(-Robot.lastUsedAngle));
    	addSequential(new SuckRollersCmd());
    	addSequential(new DriveDistancePIDCmd(-15.0, 2.0));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new DriveDistancePIDCmd(15.0, 2.0));
    	addSequential(new ChangeArmSetpointCmd(-1790.0));
    	addSequential(new Aim());
    	addSequential(new FireBall());
    }
}
