package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class MoveArmToPositionWithTimeoutCmd extends Command {

	private double position;
	
	private Timer timer = new Timer();
	
    public MoveArmToPositionWithTimeoutCmd(double position) {
    	requires(Robot.arm);
    	this.position = position;
    }

    protected void initialize() {
    	this.timer.start();
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
    	
    }

    protected boolean isFinished() {
        return this.timer.get() > 1.5;
    }

    protected void end() {
    	Robot.arm.stop();
    	this.timer.stop();
    }

    protected void interrupted() {
    	this.end();
    }
}