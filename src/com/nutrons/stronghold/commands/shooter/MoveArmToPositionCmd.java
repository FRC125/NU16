package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class MoveArmToPositionCmd extends Command {

    public MoveArmToPositionCmd() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooter.driveArm(12 * Robot.oi.getLeftJoystickY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    
    }

    protected void interrupted() {

    }
}
