package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DriveDistanceCmd extends Command {

	double distance;
	
    public DriveDistanceCmd(double distance) {
        requires(Robot.dt);
        this.distance = distance;
    }

    protected void initialize() {
    	Robot.dt.driveDistance.reset();
    	Robot.dt.driveDistance.setInputRange(-distance, distance);
    	Robot.dt.driveDistance.setOutputRange(-1.0, 1.0);
    	Robot.dt.driveDistance.setAbsoluteTolerance(1.0);
    	Robot.dt.driveDistance.setPercentTolerance(5.0);
    	Robot.dt.driveDistance.setSetpoint(this.distance);
    	Robot.dt.driveDistance.enable();
    }

    protected void execute() {
    
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    
    }

    protected void interrupted() {
    
    }
}
