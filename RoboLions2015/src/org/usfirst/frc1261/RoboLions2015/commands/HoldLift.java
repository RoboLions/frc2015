package org.usfirst.frc1261.RoboLions2015.commands;

import org.usfirst.frc1261.RoboLions2015.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class HoldLift extends Command {

	private Timer timer = new Timer();
	private boolean isHolding = false;
	
	private static final double TIMER_THRESHOLD = 0.2;
	
    public HoldLift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.liftSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!isHolding && timer.get() >= TIMER_THRESHOLD) {
    		Robot.liftSystem.holdLift();
    		timer.stop();
    		isHolding = true;
    		SmartDashboard.putBoolean(" Holding Lift", true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	SmartDashboard.putBoolean(" Holding Lift", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
