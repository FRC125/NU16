package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.climber.UnclimbCmd;

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

	private Solenoid pullHooksA = new Solenoid(1, RobotMap.PULL_HOOKS_A);
	private Solenoid pullHooksB = new Solenoid(1, RobotMap.PULL_HOOKS_B);
	private Solenoid deployHooks = new Solenoid(1, RobotMap.DEPLOY_HOOKS);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new UnclimbCmd());
    }
    
    public void deployHooks() {
    	this.deployHooks.set(true);
    }
    
    public void retractHooks() {
    	this.deployHooks.set(false);
    }
    
    public void climb() {
    	this.pullHooksA.set(true);
    	this.pullHooksB.set(false);
    }
    
    public void unclimb() {
    	this.pullHooksA.set(false);
    	this.pullHooksB.set(true);
    }

}