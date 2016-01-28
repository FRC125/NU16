package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.intake.IntakeBallCmd;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Lydia Xing
 */

public class Intake extends Subsystem {

	private Talon intakeMotor = new Talon(RobotMap.INTAKE_MOTOR);
	private Solenoid ballPusherPiston = new Solenoid(RobotMap.BALL_PUSHER_PISTON);

	public void initDefaultCommand() {
		setDefaultCommand(new IntakeBallCmd());
	}

	/**
	 * Intake a ball
	 */
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
	 * Push a ball
	 */
	public void pushBall() {
		this.ballPusherPiston.set(true);
	}
	
	/**
	 * Stop the intake motor
	 */
	public void stopIntakeMotor() {
		this.intakeMotor.set(0);
	}
}
