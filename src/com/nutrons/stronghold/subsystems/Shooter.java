package com.nutrons.stronghold.subsystems;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.stronghold.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Shooter extends Subsystem {
	
	// Solenoids
	private Solenoid shooter1 = new Solenoid(RobotMap.SHOOTER1);
	private Solenoid shooter2 = new Solenoid(RobotMap.SHOOTER2);
	private Solenoid shooter3 = new Solenoid(RobotMap.SHOOTER3);
	private Solenoid shooter4 = new Solenoid(RobotMap.SHOOTER4);
	
	// Sensors
	private DigitalInput zeroButton = new DigitalInput(RobotMap.ZERO_BUTTON);
	
	// Utils
	private DebouncedBoolean zeroButtonDebouncedBoolean = new DebouncedBoolean(5);
	
	public void initDefaultCommand() {
		
	}
	
	/**
	 * Returns the status of zero button
	 * @return Is the button pressed?
	 */
	public boolean isZeroButtonPressed() {
		this.zeroButtonDebouncedBoolean.feed(!this.zeroButton.get());
		return this.zeroButtonDebouncedBoolean.get();
	}
	
	public void fireShooter() {
		this.shooter1.set(true);
		this.shooter2.set(true);
		this.shooter3.set(true);
		this.shooter4.set(true);
	}
	
	public void retractShooter() {
		this.shooter1.set(false);
		this.shooter2.set(false);
		this.shooter3.set(false);
		this.shooter4.set(false);
	}
}