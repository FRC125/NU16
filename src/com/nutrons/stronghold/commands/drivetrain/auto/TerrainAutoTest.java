package com.nutrons.stronghold.commands.drivetrain.auto;

import com.nutrons.stronghold.commands.drivetrain.DriveTimeCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnToAngleCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TerrainAutoTest extends CommandGroup {
    
    public  TerrainAutoTest() {
    	addSequential(new DriveTimeCmd(2.5));
    	addSequential(new WaitCommand(1.0));
    	addSequential(new TurnToAngleCmd(180.0));
    	addSequential(new WaitCommand(1.0));
    	addSequential(new DriveTimeCmd(2.5));
    }
}
