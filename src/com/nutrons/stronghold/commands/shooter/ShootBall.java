package com.nutrons.stronghold.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class ShootBall extends CommandGroup {
    
    public  ShootBall() {
    	addSequential(new DeployHoodCmd());
    	addSequential(new WaitCommand(0.1));
    	addSequential(new DeployShooterCmd());
    	addSequential(new WaitCommand(1.0));
    }
}