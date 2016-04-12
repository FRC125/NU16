package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopCDFArmCmd extends Command {

    public StopCDFArmCmd() {
    	requires(Robot.cdf);
    }

    protected void initialize() {
    	Robot.cdf.StopCDF();
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
