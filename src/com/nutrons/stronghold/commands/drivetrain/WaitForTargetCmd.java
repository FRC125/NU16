package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class WaitForTargetCmd extends Command {
	
	private Timer timer = new Timer();
	
    public WaitForTargetCmd() {
    	
    }

    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    
    }

    protected boolean isFinished() {
        return Robot.isTargetSeen() || timer.get() > 3.0;
    }

    protected void end() {
    	timer.stop();
    	timer.reset();
    }

    protected void interrupted() {

    }
}