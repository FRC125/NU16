package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTimeCmd extends Command {

	private double time;
	private Timer timer = new Timer();
	
    public DriveTimeCmd(double time) {
        requires(Robot.dt);
        this.time = time;
    }

    protected void initialize() {
    	Robot.dt.resetGyro();
    	Robot.dt.driveHoldingHeading(0.6);
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return timer.get() > this.time;
    }

    protected void end() {
    	Robot.dt.disableHoldHeading();
    	Robot.dt.stop();
    }

    protected void interrupted() {
    	this.end();
    }
}
