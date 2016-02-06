package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.commands.shooter.MoveArmToIntakePositionCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * 
 * @author Camilo Gonzalez, Abdul Mezrouh
 *
 */
public class IntakeBall extends CommandGroup {
    
    public  IntakeBall() {
        addSequential(new MoveArmToIntakePositionCmd());
        addSequential(new CloseJawCmd());
        addSequential(new RollersInCmd());
        addSequential(new WaitCommand(1.0));
        addSequential(new StopIntakeMotorCmd());
    }
}