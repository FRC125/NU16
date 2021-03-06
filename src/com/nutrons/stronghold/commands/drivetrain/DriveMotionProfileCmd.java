package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.controllers.MotionProfile;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DriveMotionProfileCmd extends Command {

	private MotionProfile leftProfile;
	private MotionProfile rightProfile;
	
	private CANTalon.SetValueMotionProfile leftSetOutput;
	private CANTalon.SetValueMotionProfile rightSetOutput;
	
    public DriveMotionProfileCmd() {
        requires(Robot.dt);        
    }

    protected void initialize() {
    	Robot.dt.leftDriveA.changeControlMode(TalonControlMode.MotionProfile);
    	Robot.dt.rightDriveB.changeControlMode(TalonControlMode.MotionProfile);
    	
    	this.leftProfile = new MotionProfile(Robot.dt.leftDriveA);
    	this.rightProfile = new MotionProfile(Robot.dt.rightDriveB);
    	
    	this.leftProfile.startMotionProfile();
    	this.rightProfile.startMotionProfile();
    }

    protected void execute() {
    	this.leftProfile.control();
    	this.rightProfile.control();
    	
    	this.leftSetOutput = leftProfile.getSetValue();
    	this.rightSetOutput = rightProfile.getSetValue();
    	
    	Robot.dt.leftDriveA.set(leftSetOutput.value);
    	Robot.dt.rightDriveB.set(rightSetOutput.value);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	this.end();
    }
}