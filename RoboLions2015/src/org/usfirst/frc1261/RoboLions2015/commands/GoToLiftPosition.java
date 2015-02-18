package org.usfirst.frc1261.RoboLions2015.commands;

import org.usfirst.frc1261.RoboLions2015.Robot;
import org.usfirst.frc1261.RoboLions2015.subsystems.LiftSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoToLiftPosition extends Command {

	private double liftPosition;
	private double liftPower;
	
    public GoToLiftPosition(double liftPosition, double liftPower) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.liftSystem);
    	this.liftPosition = liftPosition;
    	this.liftPower = liftPower;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.liftSystem.stopLift();
    	Robot.liftSystem.moveLiftTo(liftPosition, liftPower);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.liftSystem.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.liftSystem.stopLift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
