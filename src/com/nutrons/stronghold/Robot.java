
package com.nutrons.stronghold;

import com.nutrons.stronghold.subsystems.Drivetrain;
import com.nutrons.stronghold.subsystems.Intake;
import com.nutrons.stronghold.subsystems.Shooter;
import com.team254.lib.util.VisionServer;
import com.nutrons.lib.Camera;
import com.nutrons.stronghold.commands.drivetrain.DriveDistanceCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveMotionProfileCmd;
import com.nutrons.stronghold.commands.drivetrain.Nothing;
import com.nutrons.stronghold.commands.drivetrain.TurnToAngleCmd;
import com.nutrons.stronghold.commands.drivetrain.auto.LowBarAuto;
import com.nutrons.stronghold.commands.drivetrain.auto.TerrainAutoTest;
import com.nutrons.stronghold.controllers.OverTerrainDefenceProfile;
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
import edu.wpi.first.wpilibj.vision.USBCamera;

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
	
	CameraServer server = CameraServer.getInstance();
	
	public static OI oi;
	
	// From server
	private static double cameraAngle = 5000.0;

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
        chooser.addDefault("Auto", new TurnToAngleCmd(90.0));
        chooser.addObject("Drive distance", new DriveDistanceCmd(5.0));
        chooser.addObject("low bar no camera auto", new LowBarAuto());
        chooser.addObject("Terrain no camera auto", new TerrainAutoTest());
        chooser.addObject("Do nothing", new Nothing());
        chooser.addObject("Drive Trajectory", new DriveMotionProfileCmd(OverTerrainDefenceProfile.Points, OverTerrainDefenceProfile.kNumPoints));
        
        SmartDashboard.putData("Auto mode", chooser);
        
        updateDashboard();
        
        server.setQuality(50);
        server.startAutomaticCapture("cam1");
        
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	updateDashboard();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
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
        if (autonomousCommand != null) autonomousCommand.start();
        updateDashboard();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        updateDashboard();
        Robot.arm.zeroArm();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
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
    	SmartDashboard.putNumber("cameraAngle", this.cameraAngle);
    	SmartDashboard.putNumber("turnError", this.dt.turnToAngle.getError());
    	SmartDashboard.putBoolean("isTurnEnable", this.dt.turnToAngle.isEnable());
    	SmartDashboard.putBoolean("isTurnOnTarget", this.dt.turnToAngle.onTarget());
    	
    	server = CameraServer.getInstance();
    }
    
    public static double getCameraAngle() {
    	return cameraAngle;
    }
}