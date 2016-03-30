package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveArmToShootingPositionUsingMotionProfileCmd extends CommandGroup {
    
    public  MoveArmToShootingPositionUsingMotionProfileCmd() {
    	if(Math.abs(Robot.arm.getArmPosition()) < 15) {
    		addSequential(new MoveArmUpWithProfileCmd());
    	}
    	addSequential(new MoveArmToPositionCmd(Arm.ARM_UP));
    }
}
