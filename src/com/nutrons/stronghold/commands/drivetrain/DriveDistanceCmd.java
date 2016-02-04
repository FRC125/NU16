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
	
	private static final double TOLERANCE = 1.0;
	
    public DriveDistanceCmd(double distance) {
    	requires(Robot.dt);
    	
    	this.distance = distance;
    }

    protected void initialize() {
    	Robot.dt.leftDrive.setPosition(0);
    	Robot.dt.rightDrive.setPosition(0);
    	
    	Robot.dt.leftDrive.setSetpoint(this.distance);
    	Robot.dt.rightDrive.setSetpoint(this.distance);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Math.abs(Robot.dt.leftDrive.getClosedLoopError()) < this.TOLERANCE && Math.abs(Robot.dt.rightDrive.getClosedLoopError()) < this.TOLERANCE;
    }

    protected void end() {
    	Robot.dt.operatorMode();
    	Robot.dt.stop();
    }

    protected void interrupted() {
    	this.end();
    }
}
