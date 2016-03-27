package com.nutrons.stronghold.commands.drivetrain.auto;

import com.nutrons.stronghold.commands.drivetrain.AimCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnLightOnCmd;
import com.nutrons.stronghold.commands.drivetrain.WaitForTargetCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Aim extends CommandGroup {
    
    public  Aim() {
    	addSequential(new WaitForTargetCmd());
    	addSequential(new AimCmd());
    }
}
