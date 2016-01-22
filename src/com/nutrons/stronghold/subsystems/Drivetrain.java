
package com.nutrons.stronghold.subsystems;

import com.kauailabs.navx_mxp.AHRS;
import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.drivetrain.TankDriveCmd;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Drivetrain extends Subsystem {
    
    // Motors
	private Talon leftDrive = new Talon(RobotMap.LEFT_DRIVE);
    private Talon rightDrive = new Talon(RobotMap.RIGHT_DRIVE);
	
    // Sensors
    private Encoder leftDriveEncoder = new Encoder(RobotMap.LEFT_DRIVE_ENCODER_A, RobotMap.LEFT_DRIVE_ENCODER_B);
    private Encoder rightDriveEncoder = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER_A, RobotMap.RIGHT_DRIVE_ENCODER_B);
    
    // Constants
    public static final double wheelDiam = 0.5;
    public static final double P_DISTANCE = 0.3;
    public static final double I_DISTANCE = 0.0;
    public static final double D_DISTANCE = 0.0;
    public static final double P_TURN = 0.0001;
    public static final double I_TURN = 0.01;
    public static final double D_TURN = 0.05;
    
    // PIDs
    public PIDController driveDistance = new PIDController(P_DISTANCE, I_DISTANCE, D_DISTANCE, new EncoderWrapper(), new DriveDistance());
    public PIDController turnToAngle = new PIDController(P_TURN, I_TURN, D_TURN, new GyroWrapper(), new TurnToAngle());
    
    // Navx
    private Byte update_rate_hz = 50;
    private SerialPort serialPort;
    private AHRS imu;
    
    public Drivetrain() {
    	this.leftDriveEncoder.setDistancePerPulse((wheelDiam * Math.PI)/256.0);
    	this.rightDriveEncoder.setDistancePerPulse((wheelDiam * Math.PI)/256.0);
    	
    	try {
    		Port p = SerialPort.Port.kUSB;
    		serialPort = new SerialPort(57600, p);
    		imu = new AHRS(serialPort, update_rate_hz);
    	}catch(Exception e) {
    		System.out.println("Woops navx nonono");
    	}
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new TankDriveCmd());
    }
    
    /**
     * Drives left and right side of the drivetrain
     * @param leftPower Power for the left side of the dt
     * @param rightPower Power for the right side of the dt 
     */
    public void driveLR(double leftPower, double rightPower) {
    	this.leftDrive.set(leftPower);
    	this.rightDrive.set(-rightPower);
    }
    
    /**
     * Stops drivetrain from moving
     */
    public void stop() {
    	this.driveLR(0, 0);
    }
    
    /**
     * Returns the distance traveled by the left side of the drivetrain in feet
     * @return Distance in feet
     */
    public double getLeftDistanceInFeet() {
    	return this.leftDriveEncoder.getDistance();
    }
    
    /**
     * Returns the distance traveled by the right side of the drivetrain in feet
     * @return Distance in feet
     */
    public double getRightDistanceInFeet() {
    	return this.rightDriveEncoder.getDistance();
    }
    
    /**
     * Resets both the left and drive encoder
     */
    public void resetEncoders() {
    	this.leftDriveEncoder.reset();
    	this.rightDriveEncoder.reset();
    }
    
    /**
     * Returns the current gyro angle
     * @return Angle in degrees
     */
    public double getGyroInDegrees() {
    	return this.imu.getYaw();
    }
    
    /**
     * Returns the current gyro angle
     * @return Angle in radians
     */
    public double getGyroAngleInRadians() {
    	return (this.getGyroInDegrees() * Math.PI) / 180.0;
    }
    
    /**
     * Zeros the gyro
     */
    public void zeroGyro() {
    	this.imu.zeroYaw();
    }
    
    public class EncoderWrapper implements PIDSource {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			return getLeftDistanceInFeet();
		}
    }
    
    public class DriveDistance implements PIDOutput {
    	
		@Override
		public void pidWrite(double output) {
			leftDrive.pidWrite(output);
			rightDrive.pidWrite(-output);
		}
    	
    }
    
    public class GyroWrapper implements PIDSource {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kRate;
		}

		@Override
		public double pidGet() {
			return getGyroInDegrees();
		}
    }
    
    public class TurnToAngle implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			leftDrive.pidWrite(leftDrive.get() + output);
			rightDrive.pidWrite(rightDrive.get() + output);
		}
    	
    }
}

