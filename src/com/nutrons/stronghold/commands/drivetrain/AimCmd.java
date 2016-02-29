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
	
	private Timer timer = new Timer();
	
	private MovingAverage errors = new MovingAverage(10);
	
    public AimCmd() {
    	requires(Robot.dt);
    }

    protected void initialize() {
    	Robot.dt.resetGyro();
    	Robot.dt.aimShot.reset();
    	Robot.dt.aimShot.setSetpoint(Robot.getAngle());
    	Robot.dt.aimShot.setInputRange(-180.0, 180.0);
    	Robot.dt.aimShot.setOutputRange(-1.0, 1.0);
    	Robot.dt.aimShot.setAbsoluteTolerance(1.0);
    	Robot.dt.aimShot.setContinuous();
    	Robot.dt.aimShot.setPID(0.05, 0.01, 0.1);
    	Robot.dt.aimShot.enable();
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    	System.out.println(Robot.dt.aimShot.getSetpoint());
    
    }

    protected boolean isFinished() {
        return  timer.get() > 3.0;
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
