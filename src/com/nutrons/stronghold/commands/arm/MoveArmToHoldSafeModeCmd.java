package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.subsystems.Arm;

import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class MoveArmToHoldSafeModeCmd extends Command {

	private double position;
	
    public MoveArmToHoldSafeModeCmd() {
    	requires(Robot.arm);
    	this.position = Arm.ARM_SAFE;
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
    	
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	this.end();
    }
}