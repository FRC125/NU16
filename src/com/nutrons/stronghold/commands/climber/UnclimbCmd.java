package com.nutrons.stronghold.commands.climber;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Andreas L.C.
 *
 */
public class UnclimbCmd extends Command {

    public UnclimbCmd() {
    	requires(Robot.climber);
    }  
    
    protected void initialize() {
    	Robot.climber.unclimb();
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
