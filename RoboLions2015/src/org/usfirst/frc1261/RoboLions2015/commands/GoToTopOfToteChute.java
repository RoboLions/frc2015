package org.usfirst.frc1261.RoboLions2015.commands;

import org.usfirst.frc1261.RoboLions2015.Robot;
import org.usfirst.frc1261.RoboLions2015.subsystems.LiftSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoToTopOfToteChute extends Command {

	public static final double TOP_OF_TOTE_CHUTE = 54;
	
    public GoToTopOfToteChute() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.liftSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.liftSystem.stopLift();
    	Robot.liftSystem.moveLiftTo(LiftSystem.convertInchesToLiftHeight(TOP_OF_TOTE_CHUTE));
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
