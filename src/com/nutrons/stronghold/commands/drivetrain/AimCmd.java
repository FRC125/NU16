package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.lib.MovingAverage;
import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class AimCmd extends Command {

	private double angle;
	
	private Timer timer = new Timer();
	
	private MovingAverage errors = new MovingAverage(10);
	
    public AimCmd() {
    	requires(Robot.dt);
    	this.angle = Robot.getAngle((int)(Robot.gripX));
    	System.out.println(this.angle);
    	/*
    	if(this.angle > 0 ) {
    		Robot.dt.aimInvert = -1.0;
    	}else {
    		Robot.dt.aimInvert = 1.0;
    	}
    	*/
    }

    protected void initialize() {
    	Robot.dt.turnLightOn();
    	Robot.dt.resetGyro();
    	Robot.dt.aimShot.reset();
    	Robot.dt.aimShot.setSetpoint(this.angle);
    	Robot.dt.aimShot.setInputRange(-180.0, 180.0);
    	Robot.dt.aimShot.setOutputRange(-1.0, 1.0);
    	Robot.dt.aimShot.setAbsoluteTolerance(1.0);
    	Robot.dt.aimShot.setContinuous();
    	Robot.dt.aimShot.setPID(0.05, 0.002, 0.00);
    	Robot.dt.aimShot.enable();
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    
    }

    protected boolean isFinished() {
        return  timer.get() > 10.0;
    }

    protected void end() {
    	Robot.dt.aimShot.disable();
    	Robot.dt.stop();
    	timer.stop();
    	timer.reset();
    	Robot.dt.turnLightOff();
    }

    protected void interrupted() {
    	this.end();
    }
}
