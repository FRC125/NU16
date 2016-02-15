package com.nutrons.stronghold.subsystems;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.lib.Ultrasonic;
import com.nutrons.stronghold.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Intake extends Subsystem {

	// Motors
	private CANTalon rollers = new CANTalon(RobotMap.INTAKE_MOTOR);
	
	// Solenoids
	private DoubleSolenoid jaw = new DoubleSolenoid(RobotMap.JAW_A, RobotMap.JAW_B);
	
	// Sensors
	private Ultrasonic isBallCenter = new Ultrasonic(RobotMap.ULTRASONIC_RX, RobotMap.ULTRASONIC_TX);
	
	// Utils
	private DebouncedBoolean isBallCenterDebouncedBoolean = new DebouncedBoolean(10);
	
	// Power Distribution Board
	public PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	// Constants
	public static final double BALL_CENTER_MARGIN = 5.0;
	
    public Intake() {
    	this.rollers.changeControlMode(TalonControlMode.PercentVbus);
    }
    
    public void initDefaultCommand() {
    	
    }
    
    /**
     * Drive intake motor
     * @param power Intake motor power
     */
    public void driveRollers(double power) {
    	this.rollers.set(power);
    }
    
    /**
     * Stops the intake motor
     */
    public void stopRollers() {
    	this.driveRollers(0.0);
    }
    
    /**
	 * Returns status of ball center
	 * @return is the ball center?
	 */
	public boolean isBallCentered() {
		this.isBallCenterDebouncedBoolean.feed(this.isBallCenter.getDistance() < this.BALL_CENTER_MARGIN);
		return this.isBallCenterDebouncedBoolean.get();
	}
	
	public void openJaw() {
		this.jaw.set(Value.kForward);
	}
	
	public void closeJaw() {
		this.jaw.set(Value.kReverse);
	}
	
	public double getRollersCurrent() {
		return this.pdp.getCurrent(13);
	}
}