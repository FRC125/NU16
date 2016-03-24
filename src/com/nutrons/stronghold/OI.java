package com.nutrons.stronghold;

import com.nutrons.lib.Utils;
import com.nutrons.stronghold.commands.arm.ChangeArmSetpointCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePosition;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToPositionCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToShootingPosition;
import com.nutrons.stronghold.commands.arm.MoveCDFArmDownCmd;
import com.nutrons.stronghold.commands.arm.MoveCDFArmUpCmd;
import com.nutrons.stronghold.commands.arm.ZeroArmCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnLightOffCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnLightOnCmd;
import com.nutrons.stronghold.commands.drivetrain.ZeroGyroCmd;
import com.nutrons.stronghold.commands.drivetrain.auto.Aim;
import com.nutrons.stronghold.commands.intake.OpenJawCmd;
import com.nutrons.stronghold.commands.intake.SpitRollersCmd;
import com.nutrons.stronghold.commands.intake.StopRollersCmd;
import com.nutrons.stronghold.commands.intake.SuckRollersCmd;
import com.nutrons.stronghold.commands.shooter.FireBall;
import com.nutrons.stronghold.commands.shooter.FireBallCmd;
import com.nutrons.stronghold.commands.shooter.RetractShooterAndJaw;
import com.nutrons.stronghold.commands.shooter.RetractShooterCmd;
import com.nutrons.stronghold.commands.shooter.Shoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class OI {
	
	// Joysticks
	public Joystick driverPad = new Joystick(0);
	public Joystick operatorPad = new Joystick(1);
	
	// Buttons driver
	private Button quickTurnButton = new JoystickButton(this.driverPad, 7);
	private Button fastDrivingModeButton = new JoystickButton(driverPad, 6);
	private Button cdfDown = new JoystickButton(driverPad, 2);
	
	// Buttons operator
	private Button openJawButton = new JoystickButton(this.operatorPad, 8);
	private Button closeJawButton = new JoystickButton(this.operatorPad, 6);
	private Button fireButton = new JoystickButton(this.operatorPad, 5);
	private Button retractShooterButton = new JoystickButton(this.operatorPad, 7);
	private Button intakeBallButton = new JoystickButton(this.operatorPad, 2);
	private Button spitBallButton = new JoystickButton(this.operatorPad, 3);
	private Button zeroArmButton = new JoystickButton(this.operatorPad, 10);
	private Button moveArmToIntakeButton = new JoystickButton(this.operatorPad, 15);
	private Button moveArmToShootingPosButton = new JoystickButton(this.operatorPad, 13);
	private Button moveArmToSavePosButton = new JoystickButton(this.operatorPad, 4);
	private Button lightButton = new JoystickButton(this.operatorPad, 12);
	private Button aim = new JoystickButton(this.operatorPad, 11);
	
	public OI() {
		this.openJawButton.whenPressed(new OpenJawCmd());
		this.closeJawButton.whenPressed(new RetractShooterAndJaw());
		
		this.fireButton.whenPressed(new FireBall());
		this.retractShooterButton.whenPressed(new RetractShooterCmd());
		
		this.spitBallButton.whenPressed(new SpitRollersCmd());
		this.intakeBallButton.whenReleased(new StopRollersCmd());
		
		this.intakeBallButton.whenPressed(new SuckRollersCmd());
		this.spitBallButton.whenReleased(new StopRollersCmd());
		
		this.zeroArmButton.whenPressed(new ZeroArmCmd());
		
		this.moveArmToIntakeButton.whenPressed(new MoveArmToIntakePosition());
		
		this.moveArmToShootingPosButton.whenPressed(new MoveArmToPositionCmd(-1800.0));
		this.moveArmToSavePosButton.whenPressed(new MoveArmToPositionCmd(-600.0));
		
		this.lightButton.whenPressed(new MoveArmToShootingPosition());
		
		this.aim.whenPressed(new Aim());
	}
	
	public double getLeftJoystickY() {
		return Utils.deadband(this.driverPad.getRawAxis(1), 0.005, 0.0);
	}
	
	public double getRightJoystickY() {
	return Utils.deadband(this.driverPad.getRawAxis(5), 0.005, 0.0);
	}
	
	public double getrightJoystickX() {
		return Utils.deadband(this.driverPad.getRawAxis(4), 0.05, 0.0);
	}
	
	public double getLeftJoystickOperatorY() {
		return Utils.deadband(this.operatorPad.getRawAxis(1), 0.005, 0.0);
	}
	
	public boolean getQuickTurn() {
		return this.quickTurnButton.get();
	}
	
	public boolean getSlowDrivingMode() {
		return this.fastDrivingModeButton.get();
	}
	
	public boolean getHoldHeadingMode() {
		return Math.abs(this.driverPad.getRawAxis(3)) > 0.9;
	}
	
	public boolean getCDFUpButton() {
		return Math.abs(this.driverPad.getRawAxis(2)) > 0.9;
	}
	
	public boolean getCDFDownButton() {
		return this.cdfDown.get();
	}
}