package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.controllers.TrajectoryDriveController;
import com.team254.lib.trajectory.Path;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DrivePathCmd extends Command {

	private Timer timer;
	
	private Path path;
	private double heading;
	private double timeout;

	private TrajectoryDriveController trajectoryDriveController;
	
    public DrivePathCmd(Path path, double timeout) {
    	requires(Robot.dt);
    	
    	this.path = path;
    	this.timeout = timeout;
    }

    protected void initialize() {
    	trajectoryDriveController = new TrajectoryDriveController();
    	this.timer.start();
    	Robot.dt.resetEncoders();
    	trajectoryDriveController.loadProfile(this.path.getLeftWheelTrajectory(), this.path.getRightWheelTrajectory(), 1.0, this.heading);
    }

    protected void execute() {
    	trajectoryDriveController.update();
    	trajectoryDriveController.loadProfileNoReset(this.path.getLeftWheelTrajectory(), this.path.getRightWheelTrajectory());
    }

    protected boolean isFinished() {
        return this.timer.get() > this.timeout || trajectoryDriveController.onTarget();
    }

    protected void end() {
    	this.timer.stop();
    	this.timer.reset();
    	Robot.dt.stop();
    }

    protected void interrupted() {
    	this.end();
    }
}
