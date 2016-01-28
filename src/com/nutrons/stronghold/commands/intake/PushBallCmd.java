package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Lydia Xing
 */
public class PushBallCmd extends Command {

    public PushBallCmd() {
        requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intake.pushBall();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	Robot.intake.stopIntakeMotor();
    }

    protected void interrupted() {
    	this.end();
    }
}
