package com.nutrons.stronghold.commands.drivetrain;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AimRobot extends CommandGroup {
    
	private double angleToTurn;
	
    public  AimRobot() {
    	aim(0);
    	
    }
    
    private void aim(double counter) {
    	if(this.angleToTurn != 5000.0) {
    		addSequential(new TurnToAngleCmd(this.angleToTurn));
    	}else if (counter < 4){
    		aim(counter + 1);
    	}
    }
}
