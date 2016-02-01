package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.controllers.MotionProfile;
import edu.wpi.first.wpilibj.CANTalon;
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
    	Robot.dt.motionProfileMode();
    	
    	this.leftProfile = new MotionProfile(Robot.dt.leftDrive);
    	this.rightProfile = new MotionProfile(Robot.dt.rightDrive);
    	
    	this.leftProfile.startMotionProfile();
    	this.rightProfile.startMotionProfile();
    }

    protected void execute() {
    	this.leftProfile.control();
    	this.rightProfile.control();
    	
    	this.leftSetOutput = leftProfile.getSetValue();
    	this.rightSetOutput = rightProfile.getSetValue();
    	
    	Robot.dt.leftDrive.set(leftSetOutput.value);
    	Robot.dt.rightDrive.set(rightSetOutput.value);
    }

    protected boolean isFinished() {
        return this.leftProfile.getSetValue() == CANTalon.SetValueMotionProfile.Hold && this.rightProfile.getSetValue() == CANTalon.SetValueMotionProfile.Hold;
    }

    protected void end() {
    	Robot.dt.operatorMode();
    	Robot.dt.stop();
    }

    protected void interrupted() {
    	this.end();
    }
}