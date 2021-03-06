package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class FireBallCmd extends Command {

    public FireBallCmd() {
        requires(Robot.shooter);
        requires(Robot.intake);
    }

    protected void initialize() {
    	Robot.intake.closeJaw();
    	Robot.shooter.fireShooter();
    }

    protected void execute() {
    
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
   
    }

    protected void interrupted() {

    }
}