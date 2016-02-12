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
    	Robot.arm.arm.setFeedbackDevice(FeedbackDevice.QuadEncoder); 
    	Robot.arm.arm.reverseSensor(true);
    	Robot.arm.arm.changeControlMode(TalonControlMode.Position);
    	Robot.arm.arm.set(this.position);
    	Robot.arm.arm.setPID(0.6, 0.0, 0.0);
    	Robot.arm.arm.configPeakOutputVoltage(12.0, -12.0);
    	Robot.arm.arm.enable();
    }

    protected void execute() {
    	System.out.println("PID is running!!!!!");
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
}