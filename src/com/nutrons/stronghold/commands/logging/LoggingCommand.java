package com.nutrons.stronghold.commands.logging;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import java.net.InetAddress;
import java.net.Socket;
import org.json.simple.JSONObject;
import com.nutrons.stronghold.Robot;
import java.nio.charset.StandardCharsets;
import java.io.IOException; 
import java.io.OutputStreamWriter;
import org.json.simple.JSONObject;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 *
 */
public class LoggingCommand extends Command {
	//objects are delcared as global so values are saved after methods end
	private JSONObject obj;
	private PrintWriter out;
	private Socket s;
	private InetAddress address;
	private Timer t;
	
     
	//the constructor is dependent on  a subsystem (log) from the Robot.java
    public LoggingCommand() {
        // Use requires() here to declare subsystem dependencies  
         requires(Robot.log);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    	obj = new JSONObject();

        try {
        	out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8)), true);
            InetAddress address= InetAddress.getByName("10.103.56.254");
            s = new Socket(address, 4444);
        } catch (IOException i) {
        	i.printStackTrace();
        }
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       obj.put("DriveTrain Angle",  Robot.dt.getAngleInDegrees());
       obj.put("LeftJoystickY", Robot.oi.getLeftJoystickY());
       obj.put("RightJoysticky", Robot.oi.getRightJoystickY());
       obj.put("SlowDrivingMode", Robot.oi.getSlowDrivingMode());
       obj.put("HoldHeadingMode", Robot.oi.getHoldHeadingMode());
  	   obj.put("Timestamp", t.getFPGATimestamp());
  	   out.write(obj.toString()); 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    
    // Called once after isFinished returns true
    protected void end() {
			out.close();
		
    	try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    } 

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
