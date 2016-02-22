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
    	this.angle = Robot.getCameraAngle();
    }

    protected void initialize() {
    	Robot.dt.turnLightOn();
    	Robot.dt.resetGyro();
    	Robot.dt.turnToAngle.reset();
    	Robot.dt.turnToAngle.setSetpoint(this.angle);
    	Robot.dt.turnToAngle.setInputRange(-180.0, 180.0);
    	Robot.dt.turnToAngle.setOutputRange(-1.0, 1.0);
    	Robot.dt.turnToAngle.setAbsoluteTolerance(10.0);
    	//Robot.dt.turnToAngle.setPercentTolerance(5.0);
    	Robot.dt.turnToAngle.setContinuous();
    	
    	Robot.dt.turnToAngle.enable();
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    
    }

    protected boolean isFinished() {
        return errors.getAverage(Math.abs(this.angle - Robot.dt.getAngleInDegrees())) < 5.0 || timer.get() < 4.0;
    }

    protected void end() {
    	Robot.dt.turnToAngle.disable();
    	Robot.dt.stop();
    	timer.stop();
    	timer.reset();
    	Robot.dt.turnLightOff();
    }

    protected void interrupted() {
    	this.end();
    }
}
