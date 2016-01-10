package com.nutrons.stronghold.commands.drivetrain;

import com.team254.lib.trajectory.Path;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DrivePath extends Command {

	private Path path;
	private double heading;
	private double timeout;
	
    public DrivePath(Path path, double timeout) {
    	this.path = path;
    	this.timeout = timeout;
    }

    protected void initialize() {
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
