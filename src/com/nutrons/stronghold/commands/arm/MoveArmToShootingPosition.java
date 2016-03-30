package com.nutrons.stronghold.commands.arm;

import com.nutrons.stronghold.commands.drivetrain.auto.Aim;
import com.nutrons.stronghold.commands.intake.OpenJawCmd;
import com.nutrons.stronghold.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class MoveArmToShootingPosition extends CommandGroup {
    
    public  MoveArmToShootingPosition() {
    	addParallel(new OpenJawAfterDelay());
    	addSequential(new MoveArmToPositionWithTimeoutCmd(Arm.ARM_UP));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new Aim());
    }
}
