package com.nutrons.stronghold.commands.shooter;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class MoveArmToPositionCmd extends Command {

	private double position;
	
    public MoveArmToPositionCmd(double positon) {
        requires(Robot.dt);
        this.position = positon;
    }

    protected void initialize() {
    	Robot.shooter.startArm();
    	Robot.shooter.moveArmToPosition(position);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.shooter.isArmAtTarget();
    }

    protected void end() {
    	Robot.shooter.stopArm();
    }

    protected void interrupted() {
    	this.end();
    }
}