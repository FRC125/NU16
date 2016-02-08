package com.nutrons.stronghold.commands.shooter;

import com.nutrons.lib.Utils;
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
    	Robot.shooter.driveArm(Utils.deadband(Robot.oi.getLeftJoystickY(), 0.05, 0.0));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    
    }

    protected void interrupted() {

    }
}