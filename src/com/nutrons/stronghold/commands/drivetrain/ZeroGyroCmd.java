package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroGyroCmd extends Command {

    public ZeroGyroCmd() {
        requires(Robot.dt);
    }

    protected void initialize() {
    	Robot.dt.resetGyro();
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
