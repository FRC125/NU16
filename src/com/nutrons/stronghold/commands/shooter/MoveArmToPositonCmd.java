package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class MoveArmToPositonCmd extends Command {

	private double position;
	
    public MoveArmToPositonCmd(double positon) {
        requires(Robot.dt);
        this.position = positon;
    }

    protected void initialize() {
    	Robot.shooter.moveArmToPosition(position);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
