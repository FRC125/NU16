package com.nutrons.stronghold.subsystems;

import com.kauailabs.navx_mxp.AHRS;
import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.drivetrain.CheesyDriveCmd;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Relay;
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
	
	//Sensors
	private Byte update_rate_hz = 50;
    private SerialPort serialPort;
    private AHRS imu;
    
    private Encoder leftDriveEncoder = new Encoder(RobotMap.LEFT_DRIVE_ENCODER_A, RobotMap.LEFT_DRIVE_ENCODER_B);
    private Encoder rightDriveEncoder = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER_A, RobotMap.RIGHT_DRIVE_ENCODER_B);
    
    // Light
    private Relay light = new Relay(RobotMap.LIGHT_RELAY);
    
    // Constants
    public double P_HEADING = 0.01;
    public double I_HEADING = 0.0;
    public double D_HEADING = 0.0;
    
    public double P_TURN = 0.0065;
    public double I_TURN = 0.0001;
    public double D_TURN = 0.0015;
    
    public double P_DISTANCE = 0.001;
    public double I_DISTANCE = 0.0;
    public double D_DISTANCE = 0.0;
    
    private PIDController holdHeading = new PIDController(this.P_HEADING, this.I_HEADING, this.D_HEADING, new GyroWrapper(), new HoldHeadingOutput());
    public PIDController turnToAngle = new PIDController(this.P_TURN, this.I_TURN, this.D_TURN, new GyroWrapper(), new TurnToAngleOutput());
    public PIDController driveDistance = new PIDController(this.P_DISTANCE, this.I_DISTANCE, this.D_DISTANCE, new EncoderWrapper(), new DriveDistanceOutput());
    
    public Drivetrain() {
    	
    	this.setPercentDrive();
    	
    	try {
    		Port p = SerialPort.Port.kMXP;
    		this.serialPort = new SerialPort(57600, p);
    		this.imu = new AHRS(this.serialPort, this.update_rate_hz);
    	}catch(Exception e) {
    		System.err.println("Cannot initilize Navx in Drivetrain Subsystem!!!");
    	}
    	
    	this.holdHeading.setInputRange(-180.0, 180.0);
    	this.holdHeading.setOutputRange(-1.0, 1.0);
    	this.holdHeading.setAbsoluteTolerance(5.0);
    	this.holdHeading.setContinuous();
    	
    	this.leftDriveEncoder.setDistancePerPulse(256.0);
    	this.rightDriveEncoder.setDistancePerPulse(256.0);
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
    
    private volatile double headingWheel;
    /**
     * Drive using only one joystick 
     * @param throttle Power value (power forward/backwards)
     * @param wheel Power to turn anywhere
     * @param quickTurn Button to turn fast!
     */
    public void driveCheesy(double throttle, double wheel, boolean holdHeading) {
        double coeff = 1.0;
    	
        if(Robot.oi.getSlowDrivingMode()) coeff = 0.7;
        
        if(Robot.oi.getQuickTurn()) {
        	driveLR(-0.9, 0.9);
        }
        
        if(holdHeading) {
        	if(!this.holdHeading.isEnabled()) this.holdHeading.enable();
        	this.holdHeading.setSetpoint(0.0);
        	driveLR((throttle * 0.5) - headingWheel, (throttle * 0.5) + headingWheel);
        }else {
        	this.holdHeading.disable();
        	wheel = wheel * 0.35;
        	driveLR((throttle - wheel) * coeff, (throttle + wheel) * coeff);
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
    	return this.leftDriveEncoder.getDistance();
    }
    
    public double getRightDistance() {
    	return this.rightDriveEncoder.getDistance();
    }
    
    public void resetEncoders() {
    	this.leftDriveEncoder.reset();
    	this.rightDriveEncoder.reset();
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
    
    public class EncoderWrapper implements PIDSource {

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			return getLeftDistance();
		}

		@Override
		public void setPIDSourceType(PIDSourceType arg0) {
			
		}
	}
    public class DriveDistanceOutput implements PIDOutput {

		@Override
		public void pidWrite(double throttle) {
			driveCheesy(throttle, 0, true);
		}
    	
    }
}