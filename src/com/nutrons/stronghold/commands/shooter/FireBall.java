package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.commands.intake.CloseJawCmd;
import com.nutrons.stronghold.commands.intake.OpenJawCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class FireBall extends CommandGroup {
	public FireBall(){
		addSequential(new CloseJawCmd());
		addSequential(new WaitCommand(0.1));
		addSequential(new FireBallCmd());
		addSequential(new WaitCommand(1));
		addSequential(new RetractShooterCmd());
	}
}
