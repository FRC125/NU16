package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DeployShooterCmd extends Command {
	
    public DeployShooterCmd() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooter.deployShooter();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}