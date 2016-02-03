package com.nutrons.stronghold.subsystems;

import com.kauailabs.navx_mxp.AHRS;
import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.drivetrain.TankDriveCmd;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
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
	public CANTalon leftDrive = new CANTalon(RobotMap.LEFT_DRIVE);
    public CANTalon rightDrive = new CANTalon(RobotMap.RIGHT_DRIVE);
    
    // Constants
    public static final double P_TURN = 0.01;
    public static final double I_TURN = 0.0;
    public static final double D_TURN = 0.1;
    
    public static final double F_DRIVE = 0.0;
    public static final double P_DRIVE = 0.5;
    public static final double I_DRIVE = 0.0;
    public static final double D_DRIVE = 0.0;
    
    public PIDController turnToAngle = new PIDController(P_TURN, I_TURN, D_TURN, new GyroWrapper(), new TurnToAngle());
    
    // Navx
    private Byte update_rate_hz = 50;
    private SerialPort serialPort;
    private AHRS imu;
    
    public Drivetrain() {
    	this.leftDrive.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	this.rightDrive.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	
    	this.leftDrive.setF(this.F_DRIVE);
    	this.leftDrive.setF(this.P_DRIVE);
    	
    	
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
     * Sets drivetrain to be used to follow a motion profile
     */
    public void motionProfileMode() {
    	this.leftDrive.changeControlMode(TalonControlMode.MotionProfile);
    	this.rightDrive.changeControlMode(TalonControlMode.MotionProfile);
    }
    
    /**
     * Sets drivetrain to be controlled by joystick input
     */
    public void operatorMode() {
    	this.leftDrive.changeControlMode(TalonControlMode.PercentVbus);
    	this.rightDrive.changeControlMode(TalonControlMode.PercentVbus);
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
     * Gets position of the leftDrive encoder
     * @return Encoder distance
     */
    public double getLeftEncoder() {
    	return this.leftDrive.getEncPosition();
    }
    
    /**
     * Gets position of the rightDrive encoder
     * @return Encoder distance
     */
    public double getRightEncoder() {
    	return this.rightDrive.getEncPosition();
    }
    
    /**
     * 
     * @param Joystick input value
     * @return Returns ramped motor power value
     */
    public double mapJoystickToPowerOutput(double input) {
        if (Math.abs(input) < 0.05) {
            // Stop if joystick is near zero
            return 0.0;
        } else {
            double mapping;
            if (Math.abs(input) <= 0.75) {
                mapping = 0.95 * ((0.5 * Math.pow(Math.abs(input), 2.0)) + 0.2);
                mapping = (input >= 0) ? mapping : -mapping;
                return mapping;
            } else {
                mapping = 2.16 * Math.abs(input) - 1.16;
                mapping = (input >= 0) ? mapping : -mapping;
                return mapping;
            }
        }
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
    
    public class GyroWrapper implements PIDSource {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
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