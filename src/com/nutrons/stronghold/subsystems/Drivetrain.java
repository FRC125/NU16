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
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

