package com.nutrons.stronghold.commands.shooter;

import com.nutrons.lib.Utils;
import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class MoveArmToPositionCmd extends Command {

    public MoveArmToPositionCmd() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    	Robot.shooter.arm.setFeedbackDevice(FeedbackDevice.QuadEncoder); 
    	Robot.shooter.arm.reverseSensor(true);
    	Robot.shooter.arm.changeControlMode(TalonControlMode.Position);
    	Robot.shooter.arm.set(1000);
    	Robot.shooter.arm.setPID(0.6, 0.0, 0.0);
    	Robot.shooter.arm.configPeakOutputVoltage(12.0, -12.0);
    	Robot.shooter.arm.enable();
    }

    protected void execute() {
    	System.out.println("PID is running!!!!!");
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    	Robot.shooter.stopArm();
    }

    protected void interrupted() {
    	this.end();
    }
}