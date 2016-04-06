package com.nutrons.stronghold.autos;

import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.commands.arm.ChangeArmSetpointCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToShootingPosition;
import com.nutrons.stronghold.commands.drivetrain.DriveDistancePIDCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveDistanceTimeBackwardsPIDCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnToAngleCmd;
import com.nutrons.stronghold.commands.drivetrain.auto.Aim;
import com.nutrons.stronghold.commands.intake.SuckRollersCmd;
import com.nutrons.stronghold.commands.shooter.FireBall;
import com.nutrons.stronghold.commands.shooter.RetractShooterAndJaw;
import com.nutrons.stronghold.commands.intake.SuckRollersCmd;
import com.nutrons.stronghold.commands.intake.CenterBallCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 A
 */
public class LowBarTwoBallAuto extends CommandGroup {
	
	
    public  LowBarTwoBallAuto(double angle) {
    	addSequential(new LowBarSafeDriveAuto()); // ≈ 2.5 seconds 
    	addSequential(new TurnToAngleCmd(angle)); // ≈ 1 second  
    	addSequential(new WaitCommand(0.125)); // = 0.125 seconds
    	shoot(0.25); // ≈ 5.5 seconds 
    	addSequential(new WaitCommand(1.0)); // = 1 second 
    	addSequential(new MoveArmToIntakePositionCmd()); // ≈ 1 second
    	addParallel(new TurnToAngleCmd(-1 * angle)); // ≈ 1 second 
    	// RETRACE STEPS ----> turn beck to initial position
    	addSequential(new LowBarSafeDriveAutoBackwards()); // ≈ 2.5 seconds
    	addSequential(new SuckRollersCmd());
    	centerDrive();
    	addSequential(new LowBarSafeDriveAuto());
    	addSequential(new TurnToAngleCmd(angle)); // ≈ 1 second
    	addSequential(new WaitCommand(0.125)); // = 0.125 seconds
    	shoot(0.25); // ≈ 5.5 seconds 
    	
    
    	
    }
    
    public void shoot(double waitTime){
    	addSequential(new MoveArmToShootingPosition()); // ≈ 1 second
    	addSequential(new WaitCommand(waitTime)); // waitTime seconds 
    	addSequential(new Aim()); // ≈ 2 seconds
    	addSequential(new WaitCommand(waitTime)); // waitTime seconds 
    	addSequential(new FireBall()); // ≈ 2 seconds 
    	
    	//total amount of time ≈ 5 + 2(waitTime) seconds 
    }
    
    public void centerDrive(){
    	addSequential(new LowBarSafeDriveAuto());
    	addParallel(new CenterBallCmd());
    }
}
