package com.nutrons.stronghold.subsystems;

import com.kauailabs.navx_mxp.AHRS;
import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.drivetrain.CheesyDriveCmd;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Drivetrain extends Subsystem {
    
	// Motors
	private CANTalon leftDriveA = new CANTalon(RobotMap.LEFT_DRIVE_MOTOR_A);
	private CANTalon leftDriveB = new CANTalon(RobotMap.LEFT_DRIVE_MOTOR_B);
	private CANTalon rightDriveA = new CANTalon(RobotMap.RIGHT_DRIVE_MOTOR_A);
	private CANTalon rightDriveB = new CANTalon(RobotMap.RIGHT_DRIVE_MOTOR_B);
	
	// navx gyro
	private Byte update_rate_hz = 50;
    private SerialPort serialPort;
    private AHRS imu;
    
    // Light
    private Relay light = new Relay(RobotMap.LIGHT_RELAY);
    
    public Drivetrain() {
    	
    	this.setPercentDrive();
    	
    	try {
    		Port p = SerialPort.Port.kMXP;
    		this.serialPort = new SerialPort(57600, p);
    		this.imu = new AHRS(this.serialPort, this.update_rate_hz);
    	}catch(Exception e) {
    		System.err.println("Cannot initilize Navx in Drivetrain Subsystem!!!");
    	}
    }
	
    public void initDefaultCommand() {
    	setDefaultCommand(new CheesyDriveCmd());
    }
    
    /**
     * Returns the current heading angle in degrees
     * @return Heading angle in degrees
     */
    public double getAngleInDegrees() {
    	return this.imu.getYaw();
    }
    
    /**
     * Resets the gyro back to zero
     */
    public void resetGyro() {
    	this.imu.zeroYaw();
    }
    
    /**
     * Sets up drivetrain mode to percentVBus (aka regular -1 to 1)
     */
    public void setPercentDrive() {
    	this.leftDriveA.changeControlMode(TalonControlMode.PercentVbus);
    	this.leftDriveB.changeControlMode(TalonControlMode.PercentVbus);
    	this.rightDriveA.changeControlMode(TalonControlMode.PercentVbus);
    	this.rightDriveB.changeControlMode(TalonControlMode.PercentVbus);
    	
    	this.leftDriveA.configNominalOutputVoltage(+0.0f, -0.0f); 
		this.leftDriveA.configPeakOutputVoltage(+12.0f, 0.0f);
		
		this.leftDriveB.configNominalOutputVoltage(+0.0f, -0.0f); 
		this.leftDriveB.configPeakOutputVoltage(+12.0f, 0.0f);
		
		this.rightDriveA.configNominalOutputVoltage(+0.0f, -0.0f); 
		this.rightDriveA.configPeakOutputVoltage(+12.0f, 0.0f);
		
		this.rightDriveB.configNominalOutputVoltage(+0.0f, -0.0f); 
		this.rightDriveB.configPeakOutputVoltage(+12.0f, 0.0f);
    }
    
    
    /**
     * Drives drivetrain using left and right power
     * @param leftPower Power number between -1 to 1 for the left side of dt
     * @param rightPower Power number between -1 to 1 for the right side of dt
     */
    public void driveLR(double leftPower, double rightPower) {
    	this.leftDriveA.set(leftPower);
    	this.leftDriveB.set(leftPower);
    	this.rightDriveA.set(-rightPower);
    	this.rightDriveB.set(-rightPower);
    }
    
    /**
     * Drive using only one joystick 
     * @param throttle Power value (power forward/backwards)
     * @param wheel Power to turn anywhere
     * @param quickTurn Button to turn fast!
     */
    public void driveCheesy(double throttle, double wheel, boolean quickTurn) {
        double angularPower;
        double overPower;
        double rPower;
        double lPower;

        if (quickTurn) {
            overPower = 1.0;
            angularPower = wheel;
        } else {
            overPower = 0.0;
            angularPower = throttle * wheel;
        }
        rPower = throttle;
        lPower = throttle;
        lPower += angularPower;
        rPower -= angularPower;
        if (lPower > 1.0) {
            rPower -= overPower * (lPower - 1.0);
            lPower = 1.0;
        } else if (rPower > 1.0) {
            lPower -= overPower * (rPower - 1.0);
            rPower = 1.0;
        } else if (lPower < -1.0) {
            rPower += overPower * (-1.0 - rPower);
            lPower = -1.0;
        } else if (rPower < -1.0) {
            lPower += overPower * (-1.0 - rPower);
            rPower = -1.0;
        }
        driveLR(lPower, rPower);
    }
    
    /**
     * Turns camera light on
     */
    public void turnLightOn() {
    	this.light.set(Value.kForward);
    }
    
    /**
     * Turns camera light off
     */
    public void turnLightOff() {
    	this.light.set(Value.kOff);
    }
}