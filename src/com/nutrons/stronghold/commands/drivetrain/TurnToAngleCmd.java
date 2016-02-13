package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class TurnToAngleCmd extends Command {

	private double angle;
	
    public TurnToAngleCmd(double angle) {
    	requires(Robot.dt);
    	
    	this.angle = angle;
    }

    protected void initialize() {
    	Robot.dt.resetGyro();
    	Robot.dt.turnToAngle.reset();
    	Robot.dt.turnToAngle.setInputRange(-180.0, 180.0);
    	Robot.dt.turnToAngle.setOutputRange(-1.0, 1.0);
    	Robot.dt.turnToAngle.setAbsoluteTolerance(2.5);
    	Robot.dt.turnToAngle.setSetpoint(this.angle);
    	Robot.dt.turnToAngle.enable();
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
