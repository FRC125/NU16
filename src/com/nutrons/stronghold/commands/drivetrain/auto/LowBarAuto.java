package com.nutrons.stronghold.commands.drivetrain.auto;

import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveTimeCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class LowBarAuto extends CommandGroup {
    
    public  LowBarAuto() {
    	addSequential(new MoveArmToIntakePositionCmd());
    	addSequential(new DriveTimeCmd(3.0));
    }
}