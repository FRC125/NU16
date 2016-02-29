package com.nutrons.stronghold.subsystems;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToPositionCmd;
import com.nutrons.stronghold.commands.arm.MoveArmWithJoystickCmd;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Arm extends Subsystem {
    
	// Motors
	public CANTalon arm1 = new CANTalon(RobotMap.ARM_1_MOTOR);
	public CANTalon arm2 = new CANTalon(RobotMap.ARM_2_MOTOR);
	
	// Sensors
	private DigitalInput zeroButton = new DigitalInput(RobotMap.ZERO_BUTTON);
		
	// Utils
	private DebouncedBoolean zeroButtonDebouncedBoolean = new DebouncedBoolean(5);
	
	// Constants
	private static double P_ARM_POSITION = 0.001;
	private static double I_ARM_POSITION = 0.0;
	private static double D_ARM_POSITION = 0.0;
	private static double F_ARM_POSITION = 0.0;
		
	public static double setpoint = 0.0;
	
	public void Arm() {
		this.arm1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	
		this.arm2.changeControlMode(TalonControlMode.Follower);
    	this.arm2.set(this.arm1.getDeviceID());
    	this.arm2.setInverted(true);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new MoveArmToIntakePositionCmd());
    }
    
    public void driveArm(double power) {
    	this.arm1.set(power);
    }
    
	/**
	 * Returns the status of zero button
	 * @return Is the button pressed?
	 */
	public boolean isZeroButtonPressed() {
		this.zeroButtonDebouncedBoolean.feed(!this.zeroButton.get());
		return this.zeroButtonDebouncedBoolean.get();
	}
    
    public void zeroArm() {
    	this.arm1.setPosition(0);
    }
    
    public double getArmPosition() {
    	return this.arm1.getPosition();
    }
    
    public double getArmError() {
    	return this.arm1.getClosedLoopError();
    }
    
    public void stop() {
    	this.arm1.changeControlMode(TalonControlMode.PercentVbus);
    	this.arm2.changeControlMode(TalonControlMode.Follower);
    	this.arm2.set(Robot.arm.arm1.getDeviceID());
    	this.arm2.setInverted(true);
    	this.arm1.set(0.0);
    }
}