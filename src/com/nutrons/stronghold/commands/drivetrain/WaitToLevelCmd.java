package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitToLevelCmd extends Command {

    public WaitToLevelCmd() {
        requires(Robot.dt);
    }

    protected void initialize() {
    
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
