package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DeployPistonsCmd extends Command {

    public DeployPistonsCmd() {
        requires(Robot.dt);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.dt.deployPistons();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
