package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DriveDistanceCmd extends Command {

	private double distance;
	
    public DriveDistanceCmd(double distance) {
        requires(Robot.dt);
        this.distance = distance;
    }

    protected void initialize() {
    	Robot.dt.resetEncoders();
    	Robot.dt.driveDistance.setAbsoluteTolerance(1);
    	Robot.dt.driveDistance.setInputRange(-this.distance, this.distance);
    	Robot.dt.driveDistance.setOutputRange(-1, 1);
    	Robot.dt.driveDistance.setSetpoint(this.distance);
    	Robot.dt.driveDistance.enable();
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return Robot.dt.driveDistance.onTarget();
    }

    protected void end() {
    	Robot.dt.driveDistance.disable();
    	Robot.dt.stop();
    }

    protected void interrupted() {
    	this.end();
    }
}
