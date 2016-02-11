package com.nutrons.stronghold.subsystems;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.shooter.MoveArmToPositionCmd;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Shooter extends Subsystem {
	
	// Motors
	private CANTalon arm = new CANTalon(RobotMap.ARM_MOTOR);
	
	// Sensors
	private DigitalInput zeroButton = new DigitalInput(RobotMap.ZERO_BUTTON);
	
	// Utils
	private DebouncedBoolean zeroButtonDebouncedBoolean = new DebouncedBoolean(5);

	// Constants
	private static double P_ARM_POSITION = 0.001;
	private static double I_ARM_POSITION = 0.0;
	private static double D_ARM_POSITION = 0.0;
	private static double F_ARM_POSITION = 0.0;
	
	
	public Shooter() {
		this.armPositionMode();
	}
	
	public void initDefaultCommand() {
		
	}
	
	/**
	 * drives arm with a power from -1 to 1
	 * @param power Arm power
	 */
	public void driveArm(double power) {
		this.arm.set(power);
	}
	
	public double getArmPosition() {
		return this.arm.getPosition();
	}
	
	public void zeroArm() {
		this.arm.setPosition(0.0);
		this.arm.clearIAccum();
	}
	
	/**
	 * Returns the status of zero button
	 * @return Is the button pressed?
	 */
	public boolean isZeroButtonPressed() {
		this.zeroButtonDebouncedBoolean.feed(!this.zeroButton.get());
		return this.zeroButtonDebouncedBoolean.get();
	}
	
	public void armPercentMode() {
		this.arm.changeControlMode(TalonControlMode.PercentVbus);
		this.arm.configNominalOutputVoltage(+0.0f, -0.0f); 
		this.arm.configPeakOutputVoltage(+12.0f, 0.0f);
		this.arm.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.arm.configEncoderCodesPerRev((4096/3)/360);
	}
	
	public void armPositionMode() {
		this.arm.changeControlMode(TalonControlMode.Position);
		this.arm.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.arm.reverseSensor(true);
		this.arm.setAllowableClosedLoopErr(5);
		this.arm.configEncoderCodesPerRev(500);
		
		this.arm.setF(this.F_ARM_POSITION);
		this.arm.setP(this.P_ARM_POSITION);
		this.arm.setI(this.I_ARM_POSITION);
		this.arm.setD(this.D_ARM_POSITION);
	}
	
	public void moveArmToPosition(double pos) {
		this.arm.set(pos);
	}
	
	public double getArmError() {
		return Math.abs(this.arm.getClosedLoopError());
	}
	
	public void stopArm() {
		this.armPercentMode();
		this.arm.set(0.0);
	}
}