package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Lydia Xing
 */
public class OpenJawCmd extends Command {

    public OpenJawCmd() {
        requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intake.deployJaw();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    	this.end();
    }
}