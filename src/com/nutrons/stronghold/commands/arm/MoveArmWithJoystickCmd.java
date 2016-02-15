package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArmWithJoystickCmd extends Command {

    public MoveArmWithJoystickCmd() {
        requires(Robot.arm);
    }

    protected void initialize() {
    	Robot.arm.arm1.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.arm.arm2.setInverted(true);
    }

    protected void execute() {
    	Robot.arm.arm1.set(Robot.oi.getLeftJoystickOperatorY() * 0.7);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
