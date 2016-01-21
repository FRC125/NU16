package com.nutrons.stronghold.controllers;

import com.nutrons.stronghold.Robot;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectoryFollower;
import com.team254.lib.util.ChezyMath;

public class TrajectoryDriveController {
	
	public Trajectory trajectory;
	public TrajectoryFollower followerLeft = new TrajectoryFollower();
	public TrajectoryFollower followerRight = new TrajectoryFollower();
	
	private double direction;
	private double heading;
	private double kTurn = -3.0/80.0;
	
	public TrajectoryDriveController() {
		this.initTrajectory();
	}
	
	/**
     * Initializes trajectories
     */
    private void initTrajectory() {
    	this.followerLeft.configure(0.9/8.3, 0, 0, 1.0/8.3, 1.0/5.6);
    	this.followerRight.configure(0.9/8.3, 0, 0, 1.0/8.3, 1.0/5.6);
    }
    
    /**
     * Reset trajectory values
     */
    private void reset() {
    	this.followerLeft.reset();
    	this.followerRight.reset();
    	Robot.dt.resetEncoders();
    }
    
    /**
     * Checks if the drivetrain is following the trajectory correctly
     * @return Is the drivetrain following trajectory
     */
    public boolean onTarget() {
    	return followerLeft.isFinishedTrajectory();
    }
    
    /**
     * Load trajectory to trajectory follower
     * @param leftProfile Trajectory for left side of drivetrain
     * @param rightProfile Trajectory for right side of drivetrain
     * @param direction Direction of trajectory
     * @param heading Heading angle of trajectory
     */
    public void loadProfile(Trajectory leftProfile, Trajectory rightProfile, double direction, double heading) {
    	this.followerLeft.setTrajectory(leftProfile);
    	this.followerRight.setTrajectory(rightProfile);
    	this.direction = direction;
    	this.heading = heading;
    }
    
    /**
     * Load trajectory to trajectory follower without reseting config values
     * @param leftProfile Trajectory for left side of drivetrain
     * @param rightProfile Trajectory for right side of drivetrain
     */
    public void loadProfileNoReset(Trajectory leftProfile, Trajectory rightProfile) {
    	this.followerLeft.setTrajectory(leftProfile);
    	this.followerRight.setTrajectory(rightProfile);
    }
    
    /**
     * Set follower's trajectory
     * @param trajectory Trajectory to follow
     */
    public void setTrajectory(Trajectory trajectory) {
    	this.trajectory = trajectory;
    }
    
    public void update() {
    	if (onTarget()) {
    		Robot.dt.stop();
        }else {
        	double distanceL = direction * Robot.dt.getLeftDistanceInFeet();
        	double distanceR = direction * Robot.dt.getRightDistanceInFeet();

        	double speedLeft = direction * followerLeft.calculate(distanceL);
        	double speedRight = direction * followerRight.calculate(distanceR);
          
        	double goalHeading = followerLeft.getHeading();
        	double observedHeading = Robot.dt.getGyroAngleInRadians();

        	double angleDiffRads = ChezyMath.getDifferenceInAngleRadians(observedHeading, goalHeading);
        	double angleDiff = Math.toDegrees(angleDiffRads);

        	double turn = kTurn * angleDiff;
        	System.out.println((speedLeft - turn) + ", " + (speedRight + turn) );
        	Robot.dt.driveLR(speedLeft - turn, speedRight + turn);
        }
    }
}

