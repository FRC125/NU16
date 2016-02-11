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
    	Robot.shooter.zeroArm();
    	Robot.shooter.armPositionMode();
    	Robot.shooter.moveArmToPosition(180);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return Robot.shooter.getArmError() < 2;
    }

    protected void end() {
    	Robot.shooter.stopArm();
    }

    protected void interrupted() {
    	this.end();
    }
}