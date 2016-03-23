package com.nutrons.stronghold.commands.drivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Aim extends CommandGroup {
    
    public  Aim() {
    	addSequential(new TurnLightOnCmd()); 
    	addSequential(new WaitForTargetCmd());
    	addSequential(new AimCmd());
    }
}
