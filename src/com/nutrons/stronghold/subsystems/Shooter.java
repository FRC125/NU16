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
	
	// Motors
	public CANTalon arm = new CANTalon(RobotMap.ARM_MOTOR);
	
	// Solenoids
	private DoubleSolenoid shooter1 = new DoubleSolenoid(RobotMap.SHOOTER1_A, RobotMap.SHOOTER1_B);
	private Solenoid shooter2 = new Solenoid(RobotMap.SHOOTER2);
	private Solenoid shooter3 = new Solenoid(RobotMap.SHOOTER3);
	private Solenoid shooter4 = new Solenoid(RobotMap.SHOOTER4);
	
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
		//this.arm.configEncoderCodesPerRev();
	}
	
	public void armPositionMode() {
		this.arm.changeControlMode(TalonControlMode.Position);
		this.arm.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.arm.reverseSensor(true);
		this.arm.setAllowableClosedLoopErr(5);
		
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
	
	public void fireShooter() {
		this.shooter1.set(Value.kForward);
		this.shooter2.set(true);
		this.shooter3.set(true);
		this.shooter4.set(true);
	}
	
	public void retractShooter() {
		this.shooter1.set(Value.kReverse);
		this.shooter2.set(false);
		this.shooter3.set(false);
		this.shooter4.set(false);
	}
}