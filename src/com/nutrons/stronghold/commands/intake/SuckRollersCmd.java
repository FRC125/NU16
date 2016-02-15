package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class SuckRollersCmd extends Command {

    public SuckRollersCmd() {
        requires(Robot.intake);
    }

    protected void initialize() {
    	Robot.intake.driveRollers(-1.0);
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
