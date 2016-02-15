package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
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
    	Robot.arm.arm1.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.arm.arm2.changeControlMode(TalonControlMode.Follower);
    	Robot.arm.arm2.set(Robot.arm.arm1.getDeviceID());
    	Robot.arm.arm2.setInverted(true);
    }

    protected void execute() {
    	Robot.arm.driveArm(0.2);
    }

    protected boolean isFinished() {
        return Robot.arm.isZeroButtonPressed();
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