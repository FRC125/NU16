package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class WaitForTargetCmd extends Command {

    public WaitForTargetCmd() {
    	
    }

    protected void initialize() {
    
    }

    protected void execute() {
    
    }

    protected boolean isFinished() {
        return Robot.isTargetSeen();
    }

    protected void end() {
    
    }

    protected void interrupted() {

    }
}