package com.nutrons.lib;

import com.team254.lib.trajectory.Trajectory;

public class TrajectoryWrapper {
	
	private Trajectory trajectory;
	private double[][] temp;
	
	public TrajectoryWrapper(Trajectory trajectory) {
		this.trajectory = trajectory;
		temp = new double[this.trajectory.getNumSegments()][2];
	}
	
	/**
	 * Converts Spline Trajectory to TalonSRX Trajectory
	 * @return Trajectory for TalonSRX
	 */
	public double[][] get() {
		for(int i = 0; i < this.trajectory.getNumSegments(); i++) {
			for(int j = 0; j < 3; j++) {
				if(j == 0) {
					this.temp[i][j] = this.trajectory.getSegment(i).pos;
				}else if (j == 1) {
					this.temp[i][j] = this.trajectory.getSegment(i).vel;
				}else if(j ==2) {
					this.temp[i][j] = 10;
				}
			}
		}
		return this.temp;
	}
	
	public int getNumberOfPoints() {
		return this.trajectory.getNumSegments();
	}
}
