package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Lydia Xing
 */
public class IntakeBallCmd extends Command {

    public IntakeBallCmd() {
       requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intake.intakeBall();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.intake.stopIntakeMotor();
    }

    protected void interrupted() {
    	this.end();
    }
}
