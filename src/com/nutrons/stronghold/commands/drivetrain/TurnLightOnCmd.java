package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class TurnLightOnCmd extends Command {

    public TurnLightOnCmd() {
    	requires(Robot.dt);
    }

    protected void initialize() {
    	Robot.dt.visionLightsOn();
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