package com.nutrons.stronghold.commands.climber;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Andreas L.C.
 *
 */
public class ClimbCmd extends Command {

    public ClimbCmd() {
    
    }  
    
    protected void initialize() {
    	Robot.climber.climb();
    }
   
    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    
    protected void interrupted() {
    }
}
