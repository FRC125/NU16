package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.controllers.ArmUpMotionProfile;
import com.nutrons.stronghold.controllers.MotionProfile;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class MoveArmUpWithProfileCmd extends Command {

	private ArmUpMotionProfile armProfile;
	
	private CANTalon.SetValueMotionProfile armProfileOutput;
	
    public MoveArmUpWithProfileCmd() {
        requires(Robot.arm);
    }

    protected void initialize() {
    	Robot.arm.arm1.setFeedbackDevice(FeedbackDevice.QuadEncoder); 
    	Robot.arm.arm1.reverseSensor(false);
    	Robot.arm.arm1.changeControlMode(TalonControlMode.MotionProfile);
    	Robot.arm.arm1.configPeakOutputVoltage(12.0, -12.0);
    	Robot.arm.arm2.changeControlMode(TalonControlMode.Follower);
    	Robot.arm.arm2.set(Robot.arm.arm1.getDeviceID());
    	
    	this.armProfile = new ArmUpMotionProfile(Robot.arm.arm1);
    	this.armProfile.startMotionProfile();
    }

    protected void execute() {
    	this.armProfile.control();
    	
    	this.armProfileOutput = this.armProfile.getSetValue();
    	
    	Robot.arm.arm1.set(this.armProfileOutput.value);
    }

    protected boolean isFinished() {
        return this.armProfileOutput == CANTalon.SetValueMotionProfile.Hold;
    }

    protected void end() {
    	Robot.arm.arm1.setFeedbackDevice(FeedbackDevice.QuadEncoder); 
    	Robot.arm.arm1.reverseSensor(false);
    	Robot.arm.arm1.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.arm.arm1.configPeakOutputVoltage(12.0, -12.0);
    	Robot.arm.arm2.changeControlMode(TalonControlMode.Follower);
    	Robot.arm.arm2.set(Robot.arm.arm1.getDeviceID());
    	Robot.arm.arm1.set(0.0);
    }

    protected void interrupted() {
    	this.end();
    }
}
