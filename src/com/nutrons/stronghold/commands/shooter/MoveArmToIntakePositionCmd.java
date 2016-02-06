package com.nutrons.stronghold.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Abdul Mezrouh, Camilo Gonzalez
 *
 */
public class MoveArmToIntakePositionCmd extends CommandGroup {
	
    public  MoveArmToIntakePositionCmd() {
    	addSequential(new MoveArmToPositionCmd(0.0));
    }
}