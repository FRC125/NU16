package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Asher
 *
 */
public class DriveDistanceTimeBackwardsPIDCmd extends Command {

	private double time;
	
	private Timer timer = new Timer();
	
    public DriveDistanceTimeBackwardsPIDCmd(double time) {
        requires(Robot.dt);
        this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.timer.reset();
    	this.timer.start();
    	Robot.dt.resetEncoders();
    	Robot.dt.resetGyro();
    	
    	Robot.dt.leftDriveA.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.dt.leftDriveB.changeControlMode(TalonControlMode.Follower);
    	Robot.dt.leftDriveB.set(Robot.dt.leftDriveA.getDeviceID());
    	
    	Robot.dt.rightDriveB.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.dt.rightDriveA.changeControlMode(TalonControlMode.Follower);
    	Robot.dt.rightDriveA.set(Robot.dt.rightDriveB.getDeviceID());
    	
    	/*
    	Robot.dt.driveDistance.setInputRange(-this.distance, this.distance * 1.5);
    	Robot.dt.driveDistance.setOutputRange(-1.0, 1.0);
    	Robot.dt.driveDistance.setAbsoluteTolerance(2.0);
    	Robot.dt.driveDistance.setSetpoint(this.distance);
    	Robot.dt.driveDistance.enable();
    	*/
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.dt.driveHoldingHeading(-1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return  timer.get() > this.time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.dt.driveDistance.disable();
    	Robot.dt.stop();
    	
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}