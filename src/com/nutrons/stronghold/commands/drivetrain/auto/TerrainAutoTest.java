package com.nutrons.stronghold.commands.drivetrain.auto;

import com.nutrons.stronghold.commands.arm.MoveArmToPositionCmd;
import com.nutrons.stronghold.commands.drivetrain.DriveMotionProfileCmd;
import com.nutrons.stronghold.controllers.OverTerrainDefenceProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class TerrainAutoTest extends CommandGroup {
    
    public  TerrainAutoTest() {
    	addParallel(new MoveArmToPositionCmd(-400.0));
    	addParallel(new DriveMotionProfileCmd(OverTerrainDefenceProfile.Points, OverTerrainDefenceProfile.kNumPoints));
    }
}