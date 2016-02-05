package com.nutrons.stronghold.commands.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.nutrons.stronghold.commands.shooter.*;

/**
 *
 */
public class SpitBall extends CommandGroup {

	public SpitBall() {
		addSequential(new MoveArmToPositionCmd(0));
		addSequential(new DeployJawCmd());
		addSequential(new RollersOutCmd());
		addSequential(new RetractJawCmd());
		addSequential(new StopIntakeMotorCmd());
	}
}
