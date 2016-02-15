package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.commands.arm.MoveArmToPositionCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class IntakeBallAndMoveToSavePos extends CommandGroup {
    
    public  IntakeBallAndMoveToSavePos() {
    	addSequential(new SuckRollersCmd());
    	addSequential(new WaitCommand(1.0));
    	addSequential(new WaitForBallToEnterCmd());
    	addSequential(new MoveArmToPositionCmd(-250.0));
    	addSequential(new StopRollersCmd());
    	
    }
}
