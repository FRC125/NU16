
package com.nutrons.stronghold;

import com.nutrons.stronghold.subsystems.Drivetrain;
import com.nutrons.stronghold.subsystems.Intake;
import com.nutrons.stronghold.subsystems.Shooter;
import com.team254.lib.util.VisionServer;
import java.io.IOException;
import com.nutrons.stronghold.commands.drivetrain.DriveDistanceCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveDistancePIDCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveMotionProfileCmd;
import com.nutrons.stronghold.autos.LowBarOneBallAuto;
import com.nutrons.stronghold.autos.SafeDriveAuto;
import com.nutrons.stronghold.commands.drivetrain.DoNothingAuto;
import com.nutrons.stronghold.commands.drivetrain.TurnToAngleCmd;
import com.nutrons.stronghold.commands.drivetrain.auto.LowBarAuto;
import com.nutrons.stronghold.commands.drivetrain.auto.TerrainAutoTest;
import com.nutrons.stronghold.subsystems.Arm;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static Compressor compressor;
	
	public static Drivetrain dt = new Drivetrain();
	public static Intake intake = new Intake();
	public static Shooter shooter = new Shooter();
	public static Arm arm = new Arm();
	
	public static OI oi;
	
	// From server
	private static double cameraAngle = 5000.0;
	public static volatile double gripX = 0.0;
	public static volatile double gripHeight = 0.0;
	public static volatile double[] centerXArray;
	public static volatile double[] heightArray;
	private static volatile double[] gripAreaArray;
	public static volatile double lastUsedAngle = 0.0;
	
	// Grip network
	private final NetworkTable grip = NetworkTable.getTable("GRIP");
	private Process gripProcess;
	
	private final double[]  DUMMY = {5000};

    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		compressor = new Compressor();
		
        chooser = new SendableChooser();
        chooser.addDefault("Turn to angle", new TurnToAngleCmd(-90.0));
        chooser.addObject("One ball auto", new LowBarOneBallAuto());
        chooser.addObject("Drive only", new DriveDistancePIDCmd(10.0, 2.0));
        chooser.addObject("Do nothing", new DoNothingAuto());
        chooser.addObject("Safe drive auto", new SafeDriveAuto());
        
        SmartDashboard.putData("Auto mode", chooser);
        
        updateDashboard();
        
        Arm.setpoint = this.arm.getArmPosition();
        
        this.arm.enableBreak();
        
        /*
         * Connects to grip
         * This should automatically initilize everything for vision
         */
        
        try {
            this.gripProcess = new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateGripNetwork();
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	updateDashboard();
    	this.dt.enableBreakMode();
    	this.gripProcess.destroy();
    	this.arm.disableBreak();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
		this.arm.disableBreak();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        Arm.setpoint = this.arm.getArmPosition();
        if (autonomousCommand != null) autonomousCommand.start();
        updateGripNetwork();
        updateDashboard();
        this.arm.enableBreak();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
        updateGripNetwork();
        
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	Arm.setpoint = this.arm.getArmPosition();
        if (autonomousCommand != null) autonomousCommand.cancel();
        updateGripNetwork();
        updateDashboard();
        Robot.arm.zeroArm();
        this.arm.enableBreak();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateGripNetwork();
        updateDashboard();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        updateGripNetwork();
    }
    
    public void updateDashboard() {
    	this.cameraAngle = Double.valueOf(VisionServer.getInstance().getAngle());
    	
    	SmartDashboard.putNumber("headingAngle", this.dt.getAngleInDegrees());
    	//SmartDashboard.putBoolean("zeroButton", this.shooter.isZeroButtonPressed());
    	SmartDashboard.putNumber("armPosition", this.arm.getArmPosition());
    	SmartDashboard.putNumber("ArmError", this.arm.getArmError());
    	SmartDashboard.putNumber("leftDistance", this.dt.getLeftDistance());
    	SmartDashboard.putNumber("RightDistance", this.dt.getRightDistance());
    	SmartDashboard.putBoolean("armSwitch", this.arm.isZeroButtonPressed());
    	SmartDashboard.putNumber("intakeCurrent", this.intake.getRollersCurrent());
    	SmartDashboard.putNumber("cameraAngleBeaglebone", this.getCameraAngleFromBeaglebone());
    	SmartDashboard.putNumber("turnError", this.dt.turnToAngle.getError());
    	SmartDashboard.putBoolean("isTurnEnable", this.dt.turnToAngle.isEnable());
    	SmartDashboard.putBoolean("isTurnOnTarget", this.dt.turnToAngle.onTarget());
    	
    	SmartDashboard.putNumber("AngleToTurnAim", AngleCalculator.getHorizontalCameraAngle(Robot.gripX));
    	SmartDashboard.putNumber("AngleToTurnAimRobot", getAngle());
    	SmartDashboard.putBoolean("isArmOnTarget", Math.abs(this.arm.arm1.getClosedLoopError()) < 100.0);
    	SmartDashboard.putNumber("gripIgnore", RobotMap.GRIP_IGNORE_VALUE);
    }
    
    public static double getCameraAngleFromBeaglebone() {
    	return cameraAngle;
    }
    
    private void updateGripNetwork() {
    	Robot.centerXArray = grip.getSubTable("myContoursReport").getNumberArray("centerX", DUMMY);
    	Robot.heightArray = grip.getSubTable("myContoursReport").getNumberArray("height", DUMMY);
        Robot.gripAreaArray = grip.getSubTable("myContoursReport").getNumberArray("area", DUMMY);
        // Prevents RoboRIO from using two different frames of data
        if(Robot.centerXArray.length!=Robot.gripAreaArray.length&&Robot.heightArray.length!=Robot.gripAreaArray.length){
        	//this.updateGripNetwork();
        	return;
        }
        
        if(Robot.centerXArray.length != 0) {
        	double maxArea = 0;
        	int maxIndex = 0;
        	for(int i = 0; i < Robot.gripAreaArray.length; i++){
        		if(Robot.gripAreaArray[i]>maxArea){
        			maxArea = Robot.gripAreaArray[i];
        			maxIndex = i;
        		}
        	}
        	Robot.gripX = Robot.centerXArray[maxIndex];
        	Robot.gripHeight = Robot.heightArray[maxIndex];
        }else {
        	Robot.gripX = 0.0;
        	Robot.gripHeight = 0.0;
        }
    }
    
    public static double getAngle(){
    	return AngleCalculator.getHorizontalAngle(Robot.gripHeight, Robot.gripX);
    }
    
    public static boolean isTargetSeen() {
    	return AngleCalculator.isTargetSeen(Robot.gripX);
    }
}
