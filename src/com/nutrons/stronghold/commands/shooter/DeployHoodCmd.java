package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Lydia Xing
 */
public class DeployHoodCmd extends Command {

    public DeployHoodCmd() {
        requires(Robot.shooter);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooter.deployHood();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
