package com.nutrons.stronghold.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class MoveArmToShootingPositionCmd extends CommandGroup {
    
    public  MoveArmToShootingPositionCmd() {
    	addSequential(new MoveArmToPositionCmd(45.0));
    }
}