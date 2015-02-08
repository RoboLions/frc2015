package org.usfirst.frc1261.RoboLions2015.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1261.RoboLions2015.Robot;

/**
 *
 */
public class AutoGyroDriveForward extends Command {

	private double distanceTarget; 
	
	public AutoGyroDriveForward(double feet) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		distanceTarget = feet;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// DO NOT addParallel() with another command that reads encoders/gyro
		Robot.driveTrain.resetEncoders();
		Robot.driveTrain.resetGyro();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double angleError = Robot.driveTrain.getAngle();
		double curveOffset = angleError * Robot.driveTrain.gyro_kP;
		Robot.driveTrain.getRobotDrive().drive(Robot.driveTrain.forwardSpeed, -curveOffset);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (Robot.driveTrain.distanceTraveled() >= distanceTarget);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.getRobotDrive().stopMotor();
    	Robot.driveTrain.resetEncoders();
    	Robot.driveTrain.resetGyro();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
