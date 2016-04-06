package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez, Andreas L.c 
 *
 */
public class Climber extends Subsystem {

	private DoubleSolenoid pullHooks = new DoubleSolenoid(RobotMap.PULL_HOOKS_A, RobotMap.PULL_HOOKS_B);
	private Solenoid deployHooks = new Solenoid(RobotMap.DEPLOY_HOOKS);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void deployHooks() {
    	this.deployHooks.set(true);
    }
    
    public void climb() {
    	this.pullHooks.set(Value.kForward);
    }
    
    public void unclimb() {
    	this.pullHooks.set(Value.kReverse);
    }

}