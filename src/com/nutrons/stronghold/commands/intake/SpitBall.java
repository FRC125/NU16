package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.commands.shooter.MoveArmToIntakePositionCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * 
 * @author Abdul Mezrouh, Camilo Gonzalez, Andreas Leonard
 *
 */
public class SpitBall extends CommandGroup {
    
    public  SpitBall() {
    	addSequential(new MoveArmToIntakePositionCmd());
    	addSequential(new RollersOutCmd());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new OpenJawCmd());
    	addSequential(new StopIntakeMotorCmd());
    }
}