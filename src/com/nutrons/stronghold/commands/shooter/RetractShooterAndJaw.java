package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.commands.intake.CloseJawCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class RetractShooterAndJaw extends CommandGroup {
    
    public  RetractShooterAndJaw() {
        addSequential(new RetractShooterCmd());
        addSequential(new CloseJawCmd());
    }
}
