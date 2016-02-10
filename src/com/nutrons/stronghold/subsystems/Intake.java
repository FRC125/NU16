package com.nutrons.stronghold.subsystems;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.lib.Ultrasonic;
import com.nutrons.stronghold.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Intake extends Subsystem {

	// Motors
	private CANTalon rollers = new CANTalon(RobotMap.INTAKE_MOTOR);
	
	// Sensors
	private Ultrasonic isBallCenter = new Ultrasonic(RobotMap.ULTRASONIC_RX, RobotMap.ULTRASONIC_TX);
	
	// Utils
	private DebouncedBoolean isBallCenterDebouncedBoolean = new DebouncedBoolean(10);
	
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
}