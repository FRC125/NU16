package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.commands.shooter.MoveArmToIntakePositionCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeBall extends CommandGroup {
    
    public  IntakeBall() {
        addSequential(new MoveArmToIntakePositionCmd());
        addSequential(new RollersOutCmd());
        addSequential(new DeployJawCmd());
    }
}
