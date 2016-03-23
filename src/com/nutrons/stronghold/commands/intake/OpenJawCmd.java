package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class OpenJawCmd extends Command {

    public OpenJawCmd() {
        requires(Robot.intake);
    }

    protected void initialize() {
    	Robot.intake.openJaw();
    }

    protected void execute() {
    	System.out.println("RUNNING OPEN JAW!!!!");
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    
    }

    protected void interrupted() {

    }
}