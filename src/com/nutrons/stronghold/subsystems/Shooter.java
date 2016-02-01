package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
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
	
	// Motors
	private CANTalon arm = new CANTalon(RobotMap.ARM);
	
	// Solenoids
	private DoubleSolenoid shooter1 = new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_1A, RobotMap.SHOOTER_SOLENOID_1B);
	private DoubleSolenoid shooter2 = new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_2A, RobotMap.SHOOTER_SOLENOID_2B);
	private DoubleSolenoid shooter3 = new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_3A, RobotMap.SHOOTER_SOLENOID_3B);
	private DoubleSolenoid shooter4 = new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_4A, RobotMap.SHOOTER_SOLENOID_4B);
    private DoubleSolenoid hood = new DoubleSolenoid(RobotMap.HOOD_SOLENOID_A, RobotMap.HOOD_SOLENOID_A);
	
    // Constants
    private double F_ARM = 0.0;
    private double P_ARM = 0.1;
    private double I_ARM = 0.0;
    private double D_ARM = 0.0;
    private int POS_TOLERANCE = 1;
    
    public Shooter() {
    	this.startArm();
    }
    
	public void initDefaultCommand() {
		
	}
	
	/**
	 * Deploys shooter pistons
	 */
	public void deployShooter() {
		shooter1.set(Value.kForward);
		shooter2.set(Value.kForward);
		shooter3.set(Value.kForward);
		shooter4.set(Value.kForward);
	}
	
	/**
	 * Retracts shooter pistons
	 */
	public void retractShooter() {
		shooter1.set(Value.kReverse);
		shooter2.set(Value.kReverse);
		shooter3.set(Value.kReverse);
		shooter4.set(Value.kReverse);
	}
	
	/**
	 * Deploy hood piston
	 */
	public void deployHood() {
		hood.set(Value.kForward);
	}
	
	/**
	 * Retract hood piston
	 */
	public void retractHood() {
		hood.set(Value.kReverse);
	}
	
	/**
	 * Sets arm position
	 * @param pos Positon arm must go to
	 */
	public void moveArmToPosition(double pos) {
		arm.setPosition(pos);
	}
	
	/**
	 * sets arm back to zero
	 */
	public void zeroArm() {
		arm.setPosition(0);
	}
	
	/**
	 * Checks if arm reached its goal
	 * @return arm status
	 */
	public boolean isArmAtTarget() {
		return Math.abs(arm.getClosedLoopError()) < this.POS_TOLERANCE;
	}
	
	/**
	 * Sets up arm to be used in Positon Mode
	 */
	public void startArm() {
		this.arm.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	this.arm.reverseSensor(false);
    	this.arm.configNominalOutputVoltage(+0.0f, -0.0f); 
        this.arm.configPeakOutputVoltage(+12.0f, 0.0f);
        
        this.arm.setProfile(0);
        this.arm.setF(F_ARM);
        this.arm.setP(P_ARM);
        this.arm.setI(I_ARM);
        this.arm.setD(D_ARM);
        
        this.arm.changeControlMode(TalonControlMode.Position);
	}
	
	/**
	 * Stops arm from moving
	 */
	public void stopArm() {
		this.arm.changeControlMode(TalonControlMode.PercentVbus);
		this.arm.set(0.0);
	}
	
}