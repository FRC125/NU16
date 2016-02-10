package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class StopRollersCmd extends Command {

    public StopRollersCmd() {
        requires(Robot.intake);
    }

    protected void initialize() {
    	Robot.intake.driveRollers(0.0);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    
    }
}
