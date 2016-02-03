package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Lydia Xing
 */

public class Intake extends Subsystem {

	// Motors
	private Talon intakeMotor = new Talon(RobotMap.INTAKE);
	
	// Solenoids
	private Solenoid jawPiston = new Solenoid(RobotMap.BALL_PUSHER_PISTON);
	
	// Sensors
	private DigitalInput banner = new DigitalInput(RobotMap.INTAKE_BANNER);

	public void initDefaultCommand() {
		
	}
	
	/**
	 * Use banner sensor to detect ball
	 */
	private boolean isballPresent() {
		return this.banner.get();
	}
	
	/**
	 * Intake a ball
	 *
	public void intakeBall() {
		this.intakeMotor.set(1);
	}

	/**
	 * Output a ball
	 */
	public void outputBall() {
		this.intakeMotor.set(-1);
	}
	
	/**
	 * Open the jaw
	 */
	public void deployJaw() {
		this.jawPiston.set(true);
	}
	
	/**
	 * Close the jaw
	 */
	public void retractJaw() {
		this.jawPiston.set(false);
	}
	
	/**
	 * Stop the intake motor
	 */
	public void stopIntakeMotor() {
		this.intakeMotor.set(0);
	}
}