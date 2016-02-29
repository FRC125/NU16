package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.RobotMap;
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
	private DoubleSolenoid shooter1 = new DoubleSolenoid(RobotMap.SHOOTER1_A, RobotMap.SHOOTER1_B);
	private DoubleSolenoid shooter2 = new DoubleSolenoid(RobotMap.SHOOTER2_A, RobotMap.SHOOTER2_B);
	private Solenoid shooter3 = new Solenoid(RobotMap.SHOOTER3);
	private Solenoid shooter4 = new Solenoid(RobotMap.SHOOTER4);
	
	public void initDefaultCommand() {
		
	}
	
	/**
	 * Fires catapult
	 */
	public void fireShooter() {
		this.shooter1.set(Value.kForward);
		this.shooter2.set(Value.kForward);
		this.shooter3.set(true);
		this.shooter4.set(true);
	}
	
	/**
	 * Retracts catapult
	 */
	public void retractShooter() {
		this.shooter1.set(Value.kReverse);
		this.shooter2.set(Value.kReverse);
		this.shooter3.set(false);
		this.shooter4.set(false);
	}
}