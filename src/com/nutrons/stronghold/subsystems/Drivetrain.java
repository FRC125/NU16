
package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.commands.drivetrain.TankDriveCmd;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectoryFollower;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.kauailabs.*;
import com.kauailabs.navx_mxp.AHRS;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Drivetrain extends Subsystem {
    
	// Motors
	private Talon leftDrive = new Talon(Robot.robotMap.LEFT_DRIVE);
	private Talon rightDrive = new Talon(Robot.robotMap.RIGHT_DRIVE);

	// Sensors
	private Encoder leftDriveEncoder = new Encoder(Robot.robotMap.ENCODER_LEFT_DRIVETRAIN_A, Robot.robotMap.ENCODER_LEFT_DRIVETRAIN_B, false, Encoder.EncodingType.k4X);
	private Encoder rightDriveEncoder = new Encoder(Robot.robotMap.ENCODER_RIGHT_DRIVETRAIN_A, Robot.robotMap.ENCODER_RIGHT_DRIVETRAIN_B, false, Encoder.EncodingType.k4X);
	
	// Constants
	public double WHEEL_DIAM = 6;
	
	// Navx setup
	private Byte update_rate_hz= 50;
	private SerialPort serialPort;
	private AHRS imu;
	
	
	public Drivetrain() {
		try{
			Port p = SerialPort.Port.kUSB;
			serialPort = new SerialPort(57600, p);
			imu = new AHRS(serialPort, update_rate_hz);
		}catch(Exception e){
			System.out.println("Woops - navx failed");
		}
		leftDriveEncoder.setDistancePerPulse(WHEEL_DIAM*Math.PI/128.0);
		rightDriveEncoder.setDistancePerPulse(WHEEL_DIAM*Math.PI/128.0);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TankDriveCmd());
    }
    
    /**
     * Sets power to the left and right side of the drivetrain
     * @param leftPower Left drivetrain motor power
     * @param rightPower Right drivetrain motor power
     */
    public void driveLR(double leftPower, double rightPower) {
    	this.leftDrive.set(leftPower);
    	this.rightDrive.set(-rightPower);
    }
    
    /**
     * Stops drivetrain from moving. Sets power to zero
     */
    public void stop() {
    	this.driveLR(0, 0);
    }
    
    /**
     * Gets total distance traveled by left side of drivetrain
     * @return Distance traveled - left 
     */
    public double getLeftEncoderDistance() {
    	return this.leftDriveEncoder.getDistance()/25.0;
    }
    
    /**
     * Gets total distance traveled by left side of drivetrain
     * @return Distance traveled - right
     */
    public double getRightEncoderDistance() {
    	return this.rightDriveEncoder.getDistance()/25.0;
    }
    
    /**
     * Resets the left and right drivetrain encoders
     */
    public void resetEncoders() {
    	this.leftDriveEncoder.reset();
    	this.rightDriveEncoder.reset();
    }   
    
    public void zeroGyro(){
    	imu.zeroYaw();
    }
    
    public double getGyroAngleInRadians() {
    	return imu.getYaw();
    }
}

