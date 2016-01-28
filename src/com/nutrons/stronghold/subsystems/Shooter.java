package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez, Abdulrahim Mezrouh, Andreas Leonard-Calcano
 *
 *
 */
public class Shooter extends Subsystem {
	
	private DoubleSolenoid shooter1 = new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_1A, RobotMap.SHOOTER_SOLENOID_1B);
	private DoubleSolenoid shooter2 = new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_2A, RobotMap.SHOOTER_SOLENOID_2B);
	private DoubleSolenoid shooter3 = new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_3A, RobotMap.SHOOTER_SOLENOID_3B);
	private DoubleSolenoid shooter4 = new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_4A, RobotMap.SHOOTER_SOLENOID_4B);
    private DoubleSolenoid hood = new DoubleSolenoid(RobotMap.HOOD_SOLENOID_A, RobotMap.HOOD_SOLENOID_A);
   
	
	public void initDefaultCommand() {
		
	}
	
	public void deployShooter() {
		shooter1.set(Value.kForward);
		shooter2.set(Value.kForward);
		shooter3.set(Value.kForward);
		shooter4.set(Value.kForward);
	}
	
	public void retractShooter() {
		shooter1.set(Value.kReverse);
		shooter2.set(Value.kReverse);
		shooter3.set(Value.kReverse);
		shooter4.set(Value.kReverse);
	}
	
	public void deployHood() {
		hood.set(Value.kForward);
	}
	
	public void retractHood() {
		hood.set(Value.kReverse);
	}
}