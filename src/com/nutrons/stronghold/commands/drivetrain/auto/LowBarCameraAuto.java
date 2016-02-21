package com.nutrons.stronghold.commands.drivetrain.auto;

import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToPositionCmd;
import com.nutrons.stronghold.commands.drivetrain.AimRobot;
import com.nutrons.stronghold.commands.drivetrain.DriveMotionProfileCmd;
import com.nutrons.stronghold.commands.intake.OpenJawCmd;
import com.nutrons.stronghold.commands.shooter.FireBallCmd;
import com.nutrons.stronghold.controllers.OverTerrainDefenceProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LowBarCameraAuto extends CommandGroup {
    
    public  LowBarCameraAuto() {
    	addSequential(new MoveArmToIntakePositionCmd());
    	addSequential(new DriveMotionProfileCmd(OverTerrainDefenceProfile.Points, OverTerrainDefenceProfile.kNumPoints));
    	addSequential(new WaitCommand(4));
    	addSequential(new AimRobot());
    	addSequential(new OpenJawCmd());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new FireBallCmd());
    }
}
