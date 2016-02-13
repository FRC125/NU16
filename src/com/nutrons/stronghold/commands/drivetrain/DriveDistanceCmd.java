package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DriveDistanceCmd extends Command {

	double distance;
	double error = .1;
	
    public DriveDistanceCmd(double distance) {
        requires(Robot.dt);
        this.distance = distance;
    }

    protected void initialize() {
    	Robot.dt.resetEncoders();
    	
    	Robot.dt.leftDriveA.changeControlMode(TalonControlMode.Position);
    	Robot.dt.rightDriveB.changeControlMode(TalonControlMode.Position);
    	
    	Robot.dt.leftDriveA.set(this.distance);
    	Robot.dt.rightDriveB.set(this.distance);
    	
    	Robot.dt.leftDriveA.enable();
    	Robot.dt.rightDriveB.enable();
    }

    protected void execute() {
    	System.out.println("DRIVE DISTANCE!!!!!!!!");
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.dt.leftDriveA.disable();
    	Robot.dt.rightDriveB.disable();
    	Robot.dt.stop();
    }

    protected void interrupted() {
    	this.end();
    }
}
