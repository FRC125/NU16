package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class RetractPistonsCmd extends Command {

    public RetractPistonsCmd() {
        requires(Robot.dt);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.dt.retractPistons();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
