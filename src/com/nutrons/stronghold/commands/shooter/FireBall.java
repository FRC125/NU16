package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.commands.intake.OpenJawCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class FireBall extends CommandGroup {
	public FireBall(){
		addSequential(new OpenJawCmd());
		addSequential(new WaitCommand(0.025));
		addSequential(new FireBallCmd());
	}
}
