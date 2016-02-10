package com.nutrons.stronghold.subsystems;

import com.kauailabs.navx_mxp.AHRS;
import com.nutrons.stronghold.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
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
}