package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class MoveArmToIntakePositionCmd extends Command {

    public MoveArmToIntakePositionCmd() {
    	requires(Robot.arm);
    }

    protected void initialize() {
    	Robot.oi.driverPad.setRumble(RumbleType.kLeftRumble, 1);
    }

    protected void execute() {
    	Robot.arm.driveArm(0.15);
    }

    protected boolean isFinished() {
        return Robot.shooter.isZeroButtonPressed();
    }

    protected void end() {
    	Robot.arm.driveArm(0.0);
    	Robot.arm.zeroArm();
    	Robot.oi.driverPad.setRumble(RumbleType.kLeftRumble, 0);
    }

    protected void interrupted() {
    	this.end();
    }
}