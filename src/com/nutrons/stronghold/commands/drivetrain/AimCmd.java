package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class AimCmd extends Command {
	
	
	private Timer timer = new Timer();
	private double angle;
	
    public AimCmd() {
    	requires(Robot.dt);
    }

    protected void initialize() {
    	angle =  Robot.getAngle();
    	
    	if(this.angle != -27.0) {
	    	Robot.dt.resetGyro();
	    	Robot.dt.aimShot.reset();
	    	Robot.dt.aimShot.setSetpoint(this.angle);
	    	Robot.dt.aimShot.setInputRange(-180.0, 180.0);
	    	Robot.dt.aimShot.setOutputRange(-1.0, 1.0);
	    	Robot.dt.aimShot.setAbsoluteTolerance(1.0);
	    	Robot.dt.aimShot.setContinuous();
	    	Robot.dt.aimShot.setPID(0.05, 0.01, 0.1);
	    	Robot.dt.aimShot.enable();
    	}
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    
    }

    protected boolean isFinished() {
        return  timer.get() > 3.0;
    }

    protected void end() {
    	Robot.dt.aimShot.disable();
    	Robot.dt.stop();
    	timer.stop();
    	timer.reset();
    }

    protected void interrupted() {
    	this.end();
    }
}