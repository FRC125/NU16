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
    	Robot.dt.zeroGyro();
    	Robot.dt.turnToAngle.setAbsoluteTolerance(1);
    	Robot.dt.turnToAngle.setInputRange(-this.angle, this.angle);
    	Robot.dt.turnToAngle.setOutputRange(-1.0, 1.0);
    	Robot.dt.turnToAngle.setSetpoint(this.angle);
    	Robot.dt.turnToAngle.setContinuous();
    	Robot.dt.turnToAngle.enable();
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return Robot.dt.turnToAngle.onTarget();
    }

    protected void end() {
    	Robot.dt.turnToAngle.disable();
    	Robot.dt.stop();
    }

    protected void interrupted() {
    	this.end();
    }
}
