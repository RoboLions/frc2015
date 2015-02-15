package org.usfirst.frc1261.RoboLions2015.commands;

import org.usfirst.frc1261.RoboLions2015.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {
	
	double deadzone = 0.1;
	double slowDeadzone = 0.02;
	
	private double handleDeadzone(double power, double deadzone){
		return (Math.abs(power) > deadzone ? power:0);
	}

    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double throttle = Robot.oi.driverJoystick.getRawAxis(1);
    	double turn = Robot.oi.driverJoystick.getRawAxis(4);
    	boolean isSlowEnabled = Robot.oi.driverRightBumper.get();
    	if(isSlowEnabled){
    		throttle = handleDeadzone(throttle, slowDeadzone);
    		turn = handleDeadzone(throttle, slowDeadzone);
    	}else{
    		throttle = handleDeadzone(throttle, deadzone);
    		turn = handleDeadzone(turn, deadzone);
    	}
    	if(isSlowEnabled){
    		throttle = throttle / 3;
    		turn = turn / 3;
    	}else{
    		throttle = throttle * 3 / 4;
    		turn = turn * 3 / 4;
    	}
    	Robot.driveTrain.getRobotDrive().arcadeDrive(throttle, turn);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
