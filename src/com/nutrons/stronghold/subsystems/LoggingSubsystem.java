package com.nutrons.stronghold.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.nutrons.stronghold.commands.logging.LoggingCommand;
import com.nutrons.stronghold.Robot;

/**
 *
 */
public class LoggingSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LoggingCommand());
    	
    }
}

