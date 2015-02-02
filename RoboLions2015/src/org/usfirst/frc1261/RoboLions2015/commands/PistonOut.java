package org.usfirst.frc1261.RoboLions2015.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1261.RoboLions2015.Robot;

/**
 *
 */
public class PistonOut extends Command {
	
    public PistonOut() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.manipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.manipulator.pistonOff();    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.manipulator.pistonOut();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.manipulator.pistonOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.manipulator.pistonOff();
    }
}
