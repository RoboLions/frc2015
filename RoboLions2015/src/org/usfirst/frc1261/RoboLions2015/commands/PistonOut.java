package org.usfirst.frc1261.RoboLions2015.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc1261.RoboLions2015.Robot;

/**
 *
 */
public class PistonOut extends Command {
	Timer timer = new Timer();
	
	private static final double TIMER_THRESHOLD;
	
	static {
    	switch (Robot.ROBOT_ID) {
    	case 1:
    		TIMER_THRESHOLD = 0.2;
    		break;
    	case 2:
    	default:
    		TIMER_THRESHOLD = 0.2;
    		break;
    	}
	}
	
    public PistonOut() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.manipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.manipulator.pistonOff();    	
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.manipulator.pistonOut();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timer.get() >= TIMER_THRESHOLD);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.manipulator.pistonOff();
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
