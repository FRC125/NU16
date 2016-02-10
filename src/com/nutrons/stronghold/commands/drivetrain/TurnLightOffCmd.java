package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class TurnLightOffCmd extends Command {

    public TurnLightOffCmd() {
    	requires(Robot.dt);
    }

    protected void initialize() {
    	Robot.dt.turnLightOff();
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