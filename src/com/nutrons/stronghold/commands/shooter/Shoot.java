package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.commands.arm.MoveArmToPositionCmd;
import com.nutrons.stronghold.commands.drivetrain.AimCmd;
import com.nutrons.stronghold.commands.intake.OpenJawCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class Shoot extends CommandGroup {
    
    public  Shoot() {
    	addSequential(new OpenJawCmd());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AimCmd());
    }
}
