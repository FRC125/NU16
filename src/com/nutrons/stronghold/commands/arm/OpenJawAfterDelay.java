package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.commands.intake.OpenJawCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class OpenJawAfterDelay extends CommandGroup {
    
    public  OpenJawAfterDelay() {
    	addSequential(new WaitCommand(0.7));
    	addSequential(new OpenJawCmd());
    }
}
