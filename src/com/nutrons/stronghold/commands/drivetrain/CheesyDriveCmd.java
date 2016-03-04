package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class CheesyDriveCmd extends Command {

    public CheesyDriveCmd() {
    	requires(Robot.dt);
    }

    protected void initialize() {
    	Robot.dt.leftDriveA.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.dt.rightDriveB.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.dt.frontDriveLightsOn();
    	Robot.dt.backDriveLightsOff();
    }

    protected void execute() {
    	Robot.dt.driveCheesy(Robot.oi.getLeftJoystickY(), Robot.oi.getrightJoystickX(), Robot.oi.getHoldHeadingMode());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    
    }

    protected void interrupted() {

    }
}
