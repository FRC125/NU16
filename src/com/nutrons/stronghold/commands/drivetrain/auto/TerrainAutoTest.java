package com.nutrons.stronghold.commands.drivetrain.auto;

import com.nutrons.stronghold.commands.arm.MoveArmToPositionCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveMotionProfileCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TerrainAutoTest extends CommandGroup {
    
    public  TerrainAutoTest() {
    	addParallel(new MoveArmToPositionCmd(-400.0));
    	addParallel(new DriveMotionProfileCmd());
    }
}
