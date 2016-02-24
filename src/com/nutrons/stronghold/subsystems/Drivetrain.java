package com.nutrons.stronghold.subsystems;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.navx_mxp.AHRS;
import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.drivetrain.CheesyDriveCmd;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Drivetrain extends Subsystem {
    
	// Motors
	public CANTalon leftDriveA = new CANTalon(RobotMap.LEFT_DRIVE_MOTOR_A);
	public CANTalon leftDriveB = new CANTalon(RobotMap.LEFT_DRIVE_MOTOR_B);
	public CANTalon rightDriveA = new CANTalon(RobotMap.RIGHT_DRIVE_MOTOR_A);
	public CANTalon rightDriveB = new CANTalon(RobotMap.RIGHT_DRIVE_MOTOR_B);
    
    // Light
    private Relay light = new Relay(RobotMap.LIGHT_RELAY);
    
    // Arduino gyro
    private Gyro gyro = new AnalogGyro(1);
    
    // Constants
    public double P_HEADING = 0.025;
    public double I_HEADING = 0.0;
    public double D_HEADING = 0.01;
    
    public double P_TURN = 0.0065;
    public double I_TURN = 0.0;
    public double D_TURN = 0.0;
    
    public double P_DISTANCE = 0.001;
    public double I_DISTANCE = 0.0;
    public double D_DISTANCE = 0.0;
    
    public static double aimInvert = 1.0;
    
    private PIDController holdHeading = new PIDController(this.P_HEADING, this.I_HEADING, this.D_HEADING, new GyroWrapper(), new HoldHeadingOutput());
    public PIDController turnToAngle = new PIDController(this.P_TURN, this.I_TURN, this.D_TURN, new GyroWrapper(), new TurnToAngleOutput());
    public PIDController aimShot = new PIDController(0.02, 0.0, 0.0, new GyroWrapper(), new AimShotOutput());
    public PIDController driveDistance = new PIDController(0.001, 0.0, 0.0, new EncoderWrapper(), new DriveDistancePIDOutput());
    
    public Drivetrain() {
    	
    	this.setPercentDrive();
    	
    	this.holdHeading.setInputRange(-180.0, 180.0);
    	this.holdHeading.setOutputRange(-1.0, 1.0);
    	this.holdHeading.setAbsoluteTolerance(5.0);
    	this.holdHeading.setContinuous();
    	
    	this.turnToAngle.setAbsoluteTolerance(10.0);
    	
    	this.leftDriveB.changeControlMode(TalonControlMode.Follower);
    	this.leftDriveB.set(this.leftDriveA.getDeviceID());
    	
    	this.rightDriveA.changeControlMode(TalonControlMode.Follower);
    	this.rightDriveA.set(this.rightDriveB.getDeviceID());
    	
    	this.leftDriveA.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	this.rightDriveB.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	
    	this.leftDriveA.configEncoderCodesPerRev((int)(256 / 0.14));
    	this.rightDriveB.configEncoderCodesPerRev((int)(256 / 0.14));
    	
    	this.leftDriveA.configNominalOutputVoltage(+0.0f, -0.0f); 
		this.leftDriveA.configPeakOutputVoltage(12.0f, -12.0f);
		
		this.leftDriveB.configNominalOutputVoltage(+0.0f, -0.0f); 
		this.leftDriveB.configPeakOutputVoltage(12.0f, -12.0f);
		
		this.rightDriveA.configNominalOutputVoltage(+0.0f, -0.0f); 
		this.rightDriveA.configPeakOutputVoltage(12.0f, -12.0f);
		
		this.rightDriveB.configNominalOutputVoltage(+0.0f, -0.0f); 
		this.rightDriveB.configPeakOutputVoltage(12.0f, -12.0f);
		
		this.leftDriveA.reverseOutput(true);
		this.leftDriveA.reverseSensor(true);
    	
    	this.leftDriveA.setPID(0.02, 0.0, 0.08);
    	this.rightDriveB.setPID(0.02, 0.0, 0.08);
    }
	
    public void initDefaultCommand() {
    	setDefaultCommand(new CheesyDriveCmd());
    }
    
    /**
     * Returns the current heading angle in degrees
     * @return Heading angle in degrees
     */
    public double getAngleInDegrees() {
    	return this.gyro.getAngle();
    }
    
    /**
     * Resets the gyro back to zero
     */
    public void resetGyro() {
    	this.gyro.reset();
    }
    
    /**
     * Sets up drivetrain mode to percentVBus (aka regular -1 to 1)
     */
    public void setPercentDrive() {
    	this.leftDriveA.changeControlMode(TalonControlMode.PercentVbus);
    	this.rightDriveB.changeControlMode(TalonControlMode.PercentVbus);
    }
    
    
    /**
     * Drives drivetrain using left and right power
     * @param leftPower Power number between -1 to 1 for the left side of dt
     * @param rightPower Power number between -1 to 1 for the right side of dt
     */
    public void driveLR(double leftPower, double rightPower) {
    	this.leftDriveA.set(leftPower);
    	this.rightDriveB.set(-rightPower);
    }
    
    private volatile double headingWheel;
    /**
     * Drive using only one joystick 
     * @param throttle Power value (power forward/backwards)
     * @param wheel Power to turn anywhere
     * @param quickTurn Button to turn fast!
     */
    public void driveCheesy(double throttle, double wheel, boolean holdHeading) {
        double coeff = 1.0;
    	double invert = 1.0;
    	
    	if(Robot.oi.getInvertButton()) invert = -1.0;
        
        if(Robot.oi.getSlowDrivingMode()) coeff = 0.7;
        
        if(Robot.oi.getQuickTurn()) {
        	driveLR(-0.9, 0.9);
        }
        
        if(holdHeading) {
        	if(!this.holdHeading.isEnabled()) this.holdHeading.enable();
        	this.holdHeading.setSetpoint(0.0);
        	driveLR((throttle * 0.5 * invert) - (headingWheel * invert), (throttle * 0.5 * invert) + (headingWheel * invert));
        }else {
        	this.holdHeading.disable();
        	wheel = wheel * 0.6;
        	driveLR(((throttle* invert) - (wheel)) * coeff  , ((throttle* invert) + (wheel)) * coeff);
        }
        
        
        double angularPower;
        double overPower;
        double rPower;
        double lPower;

        if (Robot.oi.getQuickTurn()) {
            overPower = 1.0;
            angularPower = wheel;
        } else {
            overPower = 0.0;
            angularPower = throttle * wheel;
        }
        
    }
    
    /**
     * Drives drivetrain straight
     * @param throttle
     */
    public void driveHoldingHeading(double throttle) {
    	throttle *= -1.0;
    	if(!this.holdHeading.isEnabled()){
    		this.holdHeading.reset();
    		this.holdHeading.enable();
    	}
    	this.holdHeading.setSetpoint(0.0);
    	driveLR((throttle * 0.5) - headingWheel, (throttle * 0.5) + headingWheel);
    }
    
    /**
     * Disables hold heading PID
     */
    public void disableHoldHeading() {
    	this.holdHeading.disable();
    	this.holdHeading.reset();
    }
    
    /**
     * Turns camera light on
     */
    public void turnLightOn() {
    	this.light.set(Relay.Value.kForward);
    }
    
    /**
     * Turns camera light off
     */
    public void turnLightOff() {
    	this.light.set(Relay.Value.kOff);
    }
    
    public void stop() {
    	this.setPercentDrive();
    	Robot.dt.driveLR(0.0, 0.0);
    }
    
    public double getLeftDistance() {
    	return this.leftDriveA.getPosition();
    }
    
    public double getRightDistance() {
    	return this.rightDriveB.getPosition();
    }
    
    public void resetEncoders() {
    	this.leftDriveA.setPosition(0.0);
    	this.rightDriveB.setPosition(0.0);
    }
    
    private class GyroWrapper implements PIDSource {

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			return getAngleInDegrees();
		}

		@Override
		public void setPIDSourceType(PIDSourceType arg0) {

		}
	}
    
    private class HoldHeadingOutput implements PIDOutput {

		@Override
		public void pidWrite(double wheel) {
			headingWheel = wheel;
		}
    }
    
    public class TurnToAngleOutput implements PIDOutput {

		@Override
		public void pidWrite(double power) {
			driveLR(-power, power);
		}
    }
   
    
    public class AimShotOutput implements PIDOutput {

		@Override
		public void pidWrite(double power) {
			driveLR(-power * aimInvert, power * aimInvert);
		}
    	
    }
    
    public class EncoderWrapper implements PIDSource {

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			// TODO Auto-generated method stub
			return Robot.dt.rightDriveB.getPosition();
		}

		@Override
		public void setPIDSourceType(PIDSourceType arg0) {
			// TODO Auto-generated method stub
		}
    }
    
    public class DriveDistancePIDOutput implements PIDOutput {

		@Override
		public void pidWrite(double power) {
			driveHoldingHeading(power);
		}
    	
    }
}