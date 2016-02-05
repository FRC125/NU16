package com.nutrons.stronghold.commands.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.nutrons.stronghold.commands.shooter.*;

/**
 *
 */
public class IntakeBall extends CommandGroup {
    
    public  IntakeBall() {
    	addSequential(new MoveArmToPositionCmd(0));
    	addSequential(new RetractJawCmd());
    	addSequential(new RollersInCmd());
    	addSequential(new DeployJawCmd());
    	addSequential(new StopIntakeMotorCmd());
    	
    }
}
