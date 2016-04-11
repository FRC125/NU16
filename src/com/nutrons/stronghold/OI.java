package com.nutrons.stronghold;

import com.nutrons.lib.Utils;
import com.nutrons.stronghold.commands.arm.ChangeArmSetpointCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePosition;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToPositionCmd;
import com.nutrons.stronghold.commands.arm.MoveCDFArmDownCmd;
import com.nutrons.stronghold.commands.arm.MoveCDFArmUpCmd;
import com.nutrons.stronghold.commands.arm.ZeroArmCmd;
import com.nutrons.stronghold.commands.climber.ClimbCmd;
import com.nutrons.stronghold.commands.climber.DeployHooksCmd;
import com.nutrons.stronghold.commands.climber.RetractHooksCmd;
import com.nutrons.stronghold.commands.climber.UnclimbCmd;
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
	private Button fastDrivingModeButton = new JoystickButton(driverPad, 5);
	private Button holdHeadingModeButton = new JoystickButton(driverPad, 1);
	private Button invertButton = new JoystickButton(driverPad, 6);
	private Button cdfDown = new JoystickButton(driverPad, 2);
	private Button cdfUp = new JoystickButton(driverPad, 4);
	
	// Buttons operator
	private Button openJawButton = new JoystickButton(this.operatorPad, 8);
	private Button closeJawButton = new JoystickButton(this.operatorPad, 6);
	private Button fireButton = new JoystickButton(this.operatorPad, 5);
	private Button retractShooterButton = new JoystickButton(this.operatorPad, 7);
	private Button intakeBallButton = new JoystickButton(this.operatorPad, 2);
	private Button spitBallButton = new JoystickButton(this.operatorPad, 3);
	private Button climb = new JoystickButton(this.operatorPad, 10);
	private Button moveArmToIntakeButton = new JoystickButton(this.operatorPad, 15);
	private Button moveArmToShootingPosButton = new JoystickButton(this.operatorPad, 13);
	private Button moveArmToSavePosButton = new JoystickButton(this.operatorPad, 4);
	private Button lightButton = new JoystickButton(this.operatorPad, 12);
	private Button aim = new JoystickButton(this.operatorPad, 11);
	private Button deployHooks = new JoystickButton(this.operatorPad, 9);
	private Button moveArmToClimbingPosition = new JoystickButton(this.operatorPad, 1);
	
	public OI() {
		holdHeadingModeButton.whenPressed(new ZeroGyroCmd());
		
		this.openJawButton.whenPressed(new OpenJawCmd());
		this.closeJawButton.whenPressed(new RetractShooterAndJaw());
		
		this.fireButton.whenPressed(new FireBall());
		this.retractShooterButton.whenPressed(new RetractShooterCmd());
		
		this.spitBallButton.whenPressed(new SpitRollersCmd());
		this.intakeBallButton.whenReleased(new StopRollersCmd());
		
		this.intakeBallButton.whenPressed(new SuckRollersCmd());
		this.spitBallButton.whenReleased(new StopRollersCmd());
		
		this.climb.whenPressed(new ClimbCmd());
		this.climb.whenReleased(new UnclimbCmd());
		
		this.deployHooks.whenPressed(new DeployHooksCmd());
		this.deployHooks.whenReleased(new RetractHooksCmd());
		
		this.moveArmToIntakeButton.whenPressed(new MoveArmToIntakePositionCmd());
		
		this.moveArmToShootingPosButton.whenPressed(new MoveArmToPositionCmd(-1800.0));
		this.moveArmToSavePosButton.whenPressed(new MoveArmToPositionCmd(-600.0));
		this.moveArmToClimbingPosition.whenPressed(new MoveArmToPositionCmd(-2200.0)); // get the distance from smart dashboard
		
		this.lightButton.whenPressed(new MoveArmToIntakePosition());
		
		this.aim.whenPressed(new Aim());
		
		cdfDown.whileHeld(new MoveCDFArmDownCmd());
		cdfUp.whileHeld(new MoveCDFArmUpCmd());
	}
	
	public double getLeftJoystickY() {
		return Utils.deadband(this.driverPad.getRawAxis(1), 0.15, 0.0);
	}
	
	public double getRightJoystickY() {
	return Utils.deadband(this.driverPad.getRawAxis(5), 0.15, 0.0);
	}
	
	public double getrightJoystickX() {
		return Utils.deadband(this.driverPad.getRawAxis(4), 0.15, 0.0);
	}
	
	public double getLeftJoystickOperatorY() {
		return Utils.deadband(this.operatorPad.getRawAxis(1), 0.15, 0.0);
	}
	
	public boolean getQuickTurn() {
		return this.quickTurnButton.get();
	}
	
	public boolean getSlowDrivingMode() {
		return this.fastDrivingModeButton.get();
	}
	
	public boolean getHoldHeadingMode() {
		return this.holdHeadingModeButton.get();
	}
	
	public boolean getInvertButton() {
		return this.invertButton.get();
	}
}
