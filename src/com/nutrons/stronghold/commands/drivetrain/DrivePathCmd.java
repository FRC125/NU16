package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.controllers.TrajectoryDriveController;
import com.team254.lib.trajectory.Path;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DrivePathCmd extends Command {
	
	private Path path;
	private double heading;

	private TrajectoryDriveController trajectoryDriveController;
	
    public DrivePathCmd(Path path) {
    	requires(Robot.dt);
    	this.path = path;
    }

    protected void initialize() {
    	Robot.dt.resetEncoders();
    	Robot.dt.zeroGyro();
    	
    	this.trajectoryDriveController = new TrajectoryDriveController();
    	this.trajectoryDriveController.loadProfile(this.path.getLeftWheelTrajectory(), this.path.getRightWheelTrajectory(), 1.0, this.heading);
    }

    protected void execute() {
    	trajectoryDriveController.update();
    	trajectoryDriveController.loadProfileNoReset(this.path.getLeftWheelTrajectory(), this.path.getRightWheelTrajectory());
    	
    }

    protected boolean isFinished() {
        return this.trajectoryDriveController.onTarget();
    }

    protected void end() {
    	Robot.dt.stop();
    }

    protected void interrupted() {
    	this.end();
    }
}