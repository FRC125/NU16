package com.nutrons.stronghold.commands.climber;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class RetractHooksCmd extends Command {

    public RetractHooksCmd() {
        requires(Robot.climber);
    }

    protected void initialize() {
    	Robot.climber.retractHooks();
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
