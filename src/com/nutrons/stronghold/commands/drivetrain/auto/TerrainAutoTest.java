package com.nutrons.stronghold.commands.drivetrain.auto;

import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class TerrainAutoTest extends CommandGroup {
    
    public  TerrainAutoTest() {
    	addSequential(new MoveArmToIntakePositionCmd());
    	addSequential(new WaitCommand(2.0));
    	addSequential(new DriveInSaveModeAuto());
    }
}