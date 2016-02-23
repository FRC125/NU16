package com.nutrons.stronghold.commands.arm;

import com.nutrons.lib.Utils;
import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class MoveArmToPositionCmd extends Command {

	private double position;
	
    public MoveArmToPositionCmd(double position) {
    	requires(Robot.arm);
    	this.position = position;
    }

    protected void initialize() {
    	Robot.arm.arm1.setFeedbackDevice(FeedbackDevice.QuadEncoder); 
    	Robot.arm.arm1.reverseSensor(false);
    	Robot.arm.arm1.changeControlMode(TalonControlMode.Position);
    	Robot.arm.arm1.set(this.position);
    	Robot.arm.arm1.setPID(0.95, 0.0, 0.0);
    	Robot.arm.arm1.configPeakOutputVoltage(12.0, -12.0);
    	Robot.arm.arm2.changeControlMode(TalonControlMode.Follower);
    	Robot.arm.arm2.set(Robot.arm.arm1.getDeviceID());
    	Robot.arm.arm1.enable();
    }

    protected void execute() {
    	//System.out.println("PID is running!!!!!");
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.arm.stop();
    }

    protected void interrupted() {
    	this.end();
    }
}