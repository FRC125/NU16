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
    	
    	Robot.dt.leftDriveA.configEncoderCodesPerRev((int)(256 / 0.14));
    	Robot.dt.rightDriveB.configEncoderCodesPerRev((int)(256 / 0.14));
    	
    	Robot.dt.leftDriveA.enableBrakeMode(true);
    	Robot.dt.rightDriveB.enableBrakeMode(true);
    	
    	Robot.dt.leftDriveA.set(this.distance);
    	Robot.dt.rightDriveB.set(this.distance);
    	
    	Robot.dt.leftDriveA.setPID(0.016, 0.0, 0.08);
    	Robot.dt.rightDriveB.setPID(0.016, 0.0, 0.08);
    	
    	Robot.dt.leftDriveA.enable();
    	Robot.dt.rightDriveB.enable();
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return false;//Math.abs(Robot.dt.leftDriveA.getClosedLoopError()) < 3.0;
    }

    protected void end() {
    	Robot.dt.leftDriveA.disable();
    	Robot.dt.rightDriveB.disable();
    	Robot.dt.stop();
    	Robot.dt.leftDriveA.enableBrakeMode(false);
    	Robot.dt.rightDriveB.enableBrakeMode(false);
    }

    protected void interrupted() {
    	this.end();
    }
}