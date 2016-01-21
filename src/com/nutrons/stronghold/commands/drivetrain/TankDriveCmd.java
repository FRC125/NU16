package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class TankDriveCmd extends Command {

    public TankDriveCmd() {
        requires(Robot.dt);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.dt.driveLR(Robot.oi.getLeftJoystickY(), Robot.oi.getRightJoystickY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.dt.stop();
    }

    protected void interrupted() {
    	this.end();
    }
}