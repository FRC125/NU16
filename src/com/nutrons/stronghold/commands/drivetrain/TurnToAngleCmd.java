package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.lib.MovingAverage;
import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class TurnToAngleCmd extends Command {

	private double angle;
	
	private MovingAverage errors = new MovingAverage(10);
	
    public TurnToAngleCmd(double angle) {
    	requires(Robot.dt);
    	
    	this.angle = angle;
    }

    protected void initialize() {
    	Robot.dt.resetGyro();
    	Robot.dt.turnToAngle.reset();
    	Robot.dt.turnToAngle.setSetpoint(this.angle);
    	Robot.dt.turnToAngle.setInputRange(-180.0, 180.0);
    	Robot.dt.turnToAngle.setOutputRange(-1.0, 1.0);
    	Robot.dt.turnToAngle.setAbsoluteTolerance(10.0);
    	//Robot.dt.turnToAngle.setPercentTolerance(5.0);
    	Robot.dt.turnToAngle.setContinuous();
    	Robot.dt.turnToAngle.setPID(Robot.dt.P_TURN, Robot.dt.I_TURN, Robot.dt.D_TURN);
    	Robot.dt.turnToAngle.enable();
    }

    protected void execute() {
    
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.dt.turnToAngle.disable();
    	Robot.dt.stop();
    }

    protected void interrupted() {
    	this.end();
    }
}
