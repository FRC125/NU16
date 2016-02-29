package com.nutrons.stronghold.commands.drivetrain.auto;

import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.commands.drivetrain.AimCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnLightOnCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnToAngleCmd;
import com.nutrons.stronghold.commands.drivetrain.WaitForTargetCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class Aim extends CommandGroup {
    
    public  Aim() {
    	addSequential(new TurnLightOnCmd()); 
    	addSequential(new WaitForTargetCmd());
    	addSequential(new AimCmd());
    }
}
