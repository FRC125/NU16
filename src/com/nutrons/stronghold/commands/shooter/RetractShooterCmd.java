package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class RetractShooterCmd extends Command {

    public RetractShooterCmd() {
        requires(Robot.shooter);
    }

    protected void initialize() {
    	Robot.shooter.retractShooter();
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