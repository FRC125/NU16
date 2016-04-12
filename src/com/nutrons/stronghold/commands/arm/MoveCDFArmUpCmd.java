package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveCDFArmUpCmd extends Command {

    public MoveCDFArmUpCmd() {
    	requires(Robot.cdf);
    }

    protected void initialize() {
    	Robot.cdf.MoveCDFArmUp();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.cdf.StopCDF();
    }

    protected void interrupted() {
    	this.end();
    }
}
