package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class MoveArmToIntakePositionCmd extends Command {

    public MoveArmToIntakePositionCmd() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooter.driveArm(0.15);
    }

    protected boolean isFinished() {
        return Robot.shooter.isZeroButtonPressed();
    }

    protected void end() {
    	Robot.shooter.driveArm(0.0);
    }

    protected void interrupted() {
    	this.end();
    }
}