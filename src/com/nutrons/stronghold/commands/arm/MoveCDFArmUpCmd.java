package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveCDFArmUpCmd extends Command {

    public MoveCDFArmUpCmd() {
    	requires(Robot.dt);
    }

    protected void initialize() {
    	Robot.dt.MoveCDFArmUp();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.dt.StopCDF();
    }

    protected void interrupted() {
    	this.end();
    }
}
