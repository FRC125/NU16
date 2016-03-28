package com.nutrons.stronghold.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveArmToIntakePosition extends CommandGroup {
    
    public  MoveArmToIntakePosition() {
    	addParallel(new MoveArmToIntakePositionCmd());
    	addSequential(new MoveCDFArmDownCmd());
    }
}
