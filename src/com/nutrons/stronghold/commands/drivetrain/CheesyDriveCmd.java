package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

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
    
    }

    protected void execute() {
    	Robot.dt.driveCheesy(Robot.oi.getLeftJoystickY(), Robot.oi.getrightJoystickX(), Robot.oi.getQuickTurn());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    
    }

    protected void interrupted() {

    }
}
