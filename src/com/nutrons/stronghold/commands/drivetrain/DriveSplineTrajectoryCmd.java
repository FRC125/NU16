package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.lib.TrajectoryWrapper;
import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.controllers.SplineProfile;
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.io.TextFileDeserializer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DriveSplineTrajectoryCmd extends Command {

	private Path path;
	private TextFileDeserializer deserializer;
	
	private SplineProfile leftProfile;
	private SplineProfile rightProfile;
	
	private CANTalon.SetValueMotionProfile leftSetOutput;
	private CANTalon.SetValueMotionProfile rightSetOutput;
	
    public DriveSplineTrajectoryCmd() {
    	requires(Robot.dt);
    }

    protected void initialize() {
    	this.path = deserializer.deserialize("trajectory");
    	
    	Robot.dt.leftDriveA.changeControlMode(TalonControlMode.MotionProfile);
    	Robot.dt.rightDriveB.changeControlMode(TalonControlMode.MotionProfile);
    	
    	this.leftProfile = new SplineProfile(Robot.dt.leftDriveA, new TrajectoryWrapper(this.path.getLeftWheelTrajectory()));
    	this.rightProfile = new SplineProfile(Robot.dt.rightDriveB, new TrajectoryWrapper(this.path.getRightWheelTrajectory()));
    	
    	this.leftProfile.startMotionProfile();
    	this.rightProfile.startMotionProfile();
    }

    protected void execute() {
    	this.leftProfile.control();
    	this.rightProfile.control();
    	
    	this.leftSetOutput = leftProfile.getSetValue();
    	this.rightSetOutput = rightProfile.getSetValue();
    	
    	Robot.dt.leftDriveA.set(leftSetOutput.value);
    	Robot.dt.rightDriveB.set(rightSetOutput.value);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	this.end();
    }
}