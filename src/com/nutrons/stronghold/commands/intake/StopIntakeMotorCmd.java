package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Lydia Xing
 */
public class StopIntakeMotorCmd extends Command {

    public StopIntakeMotorCmd() {
        requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intake.stopIntakeMotor();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.intake.stopIntakeMotor();
    }

    protected void interrupted() {
    	this.end();
    }
}
