package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.subsystems.Arm;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class ChangeArmSetpointCmd extends Command {
	
	private double setpoint;
	
    public ChangeArmSetpointCmd(double setpoint) {
    	this.setpoint = setpoint;
    }

    protected void initialize() {
    	Arm.setpoint = setpoint;
    }

    protected void execute() {
    
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    
    }

    protected void interrupted() {

    }
}
