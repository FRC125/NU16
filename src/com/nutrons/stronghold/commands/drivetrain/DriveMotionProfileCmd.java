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
    	
    	leftProfile = new MotionProfile(Robot.dt.leftDrive);
    	rightProfile = new MotionProfile(Robot.dt.rightDrive);
    	
    	leftProfile.startMotionProfile();
    	rightProfile.startMotionProfile();
    }

    protected void execute() {
    	leftProfile.control();
    	rightProfile.control();
    	
    	leftSetOutput = leftProfile.getSetValue();
    	rightSetOutput = rightProfile.getSetValue();
    	
    	Robot.dt.leftDrive.set(leftSetOutput.value);
    	Robot.dt.rightDrive.set(rightSetOutput.value);
    }

    protected boolean isFinished() {
        return leftProfile.getSetValue() == CANTalon.SetValueMotionProfile.Hold && rightProfile.getSetValue() == CANTalon.SetValueMotionProfile.Hold;
    }

    protected void end() {
    	Robot.dt.operatorMode();
    	Robot.dt.stop();
    }

    protected void interrupted() {
    	this.end();
    }
}
