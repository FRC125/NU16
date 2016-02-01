package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Lydia Xing 
 */
public class RetractShooterCmd extends Command {

    public RetractShooterCmd() {
        requires(Robot.shooter);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooter.retractShooter();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }
    protected void interrupted() {
    }
}