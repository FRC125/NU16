package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveCDFArmDownCmd extends Command {

    public MoveCDFArmDownCmd() {
    	requires(Robot.dt);
    }

    protected void initialize() {
    	Robot.dt.MoveCDFArmDown();
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
