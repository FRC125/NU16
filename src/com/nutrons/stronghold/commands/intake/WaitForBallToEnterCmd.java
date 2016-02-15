package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class WaitForBallToEnterCmd extends Command {

    public WaitForBallToEnterCmd() {
        requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.intake.getRollersCurrent() > 9.0;
    }

    protected void end() {

    }

    protected void interrupted() {
    
    }
}
