package org.usfirst.frc1261.RoboLions2015.commands;

import org.usfirst.frc1261.RoboLions2015.Robot;
import org.usfirst.frc1261.RoboLions2015.subsystems.LiftSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BringLiftDown extends Command {

	private static final double LIFT_SPEED = -0.5;
	
    public BringLiftDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.liftSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.liftSystem.stopLift();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.liftSystem.setLiftSpeed(LIFT_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.liftSystem.hitLowerLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.liftSystem.stopLift();
    	Robot.liftSystem.calibrateLiftHeight(LiftSystem.CALIBRATION_LIFT_ENCODER_MIN);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
