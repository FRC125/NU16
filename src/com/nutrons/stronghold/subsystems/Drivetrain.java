package com.nutrons.stronghold.subsystems;

import com.kauailabs.navx_mxp.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Drivetrain extends Subsystem {
    
	// navx gyro
	private Byte update_rate_hz = 50;
    private SerialPort serialPort;
    private AHRS imu;
    
    public Drivetrain() {
    	
    	try {
    		Port p = SerialPort.Port.kMXP;
    		serialPort = new SerialPort(57600, p);
    		imu = new AHRS(serialPort, update_rate_hz);
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
}