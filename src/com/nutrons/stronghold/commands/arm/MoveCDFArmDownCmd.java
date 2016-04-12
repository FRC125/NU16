package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveCDFArmDownCmd extends Command {

	private Timer timer = new Timer();
	
    public MoveCDFArmDownCmd() {
    	requires(Robot.cdf);
    }

    protected void initialize() {
    	timer.start();
    	Robot.cdf.MoveCDFArmDown();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return timer.get() > 3.0;
    }

    protected void end() {
    	timer.stop();
    	timer.reset();
    	Robot.cdf.StopCDF();
    }

    protected void interrupted() {
    	this.end();
    }
}
