package com.nutrons.stronghold.commands.intake;

import com.nutrons.stronghold.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Camilo Gonzalez
 */
public class CloseJawCmd extends Command {

    public CloseJawCmd() {
        requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intake.retractJaw();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    
    }

    protected void interrupted() {
    	
    }
}
