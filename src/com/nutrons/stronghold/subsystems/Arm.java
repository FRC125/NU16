package com.nutrons.stronghold.subsystems;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToPositionCmd;

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
		
	public static volatile double setpoint = 0.0;
	
	public void Arm() {
		this.arm1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	
		this.arm2.changeControlMode(TalonControlMode.Follower);
    	this.arm2.set(this.arm1.getDeviceID());
    	this.arm2.setInverted(true);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new MoveArmToIntakePositionCmd());
    }
    
    /**
     * Moves arm based on power from -1.0 to 1.0
     * @param power Power value from -1.0 to 1.0
     */
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
    
	/**
	 * Zeros arm position
	 */
    public void zeroArm() {
    	this.arm1.setPosition(0);
    }
    
    /**
     * Gets arm current position
     * @return Current arm position
     */
    public double getArmPosition() {
    	return this.arm1.getPosition();
    }
    
    /**
     * Gets arm position error
     * @return Arm's error to target
     */
    public double getArmError() {
    	return this.arm1.getClosedLoopError();
    }
    
    /**
     * Stops the arm
     */
    public void stop() {
    	this.arm1.changeControlMode(TalonControlMode.PercentVbus);
    	this.arm2.changeControlMode(TalonControlMode.Follower);
    	this.arm2.set(Robot.arm.arm1.getDeviceID());
    	this.arm2.setInverted(true);
    	this.arm1.set(0.0);
    }
    
    public void enableBreak() {
    	this.arm1.enableBrakeMode(true);
    	this.arm2.enableBrakeMode(true);
    }
    
    public void disableBreak() {
    	this.arm1.enableBrakeMode(false);
    	this.arm2.enableBrakeMode(false);
    }
}