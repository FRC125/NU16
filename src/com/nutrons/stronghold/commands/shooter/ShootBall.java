package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.commands.intake.CloseJawCmd;
import com.nutrons.stronghold.commands.intake.OpenJawCmd;
import com.nutrons.stronghold.commands.intake.StopIntakeMotorCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class ShootBall extends CommandGroup {
    
    public  ShootBall() {
    	addSequential(new StopIntakeMotorCmd());
    	addSequential(new CloseJawCmd());
    	addSequential(new MoveArmToShootingPositionCmd());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new OpenJawCmd());
    	addSequential(new DeployShooterCmd());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new RetractShooterCmd());
    }
}