package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author basedTaha
 *
 */

public class TurnToZeroCMD extends Command{
	
	private double angle; 
	private Timer timer = new Timer();
	
	public TurnToZeroCMD(){
		this.angle = 0; 
		
		requires(Robot.dt);
	}
	
	
	protected void initialize() {
	    	this.timer.start();
	    	Robot.dt.resetGyro();
	    	Robot.dt.turnToAngle.reset();
	    	Robot.dt.turnToAngle.setSetpoint(Robot.dt.getAngleAccumilator() * -1.0);
	    	Robot.dt.turnToAngle.setInputRange(-180.0, 180.0);
	    	Robot.dt.turnToAngle.setOutputRange(-1.0, 1.0);
	    	Robot.dt.turnToAngle.setAbsoluteTolerance(10.0);
	    	Robot.dt.turnToAngle.setContinuous();
	    	Robot.dt.turnToAngle.setPID(Robot.dt.P_TURN, Robot.dt.I_TURN, Robot.dt.D_TURN);
	    	Robot.dt.turnToAngle.enable();
	    }

	    protected void execute() {
	    
	    }

	    protected boolean isFinished() {
	        return this.timer.get() > 3.0;
	    }

	    protected void end() {
	    	this.timer.stop();
	    	this.timer.reset();
	    	Robot.dt.turnToAngle.disable();
	    	Robot.dt.stop();
	    }

	    protected void interrupted() {
	    	this.end();
	    }

}
