package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class CenterBallCmd extends Command {

    public CenterBallCmd() {
        requires(Robot.intake);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	Robot.intake.driveRollers(1);
    }

    protected boolean isFinished() {
        return Robot.intake.isBallCentered();
    }

    protected void end() {
    	Robot.intake.stopRollers();
    }

    protected void interrupted() {
    	this.end();
    }
}