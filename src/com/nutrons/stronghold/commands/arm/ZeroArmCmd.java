package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroArmCmd extends Command {

    public ZeroArmCmd() {
    	
    }

    protected void initialize() {
    	Robot.arm.zeroArm();
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
