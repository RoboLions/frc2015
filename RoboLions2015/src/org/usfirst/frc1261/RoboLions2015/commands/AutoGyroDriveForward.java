package org.usfirst.frc1261.RoboLions2015.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc1261.RoboLions2015.Robot;
import org.usfirst.frc1261.RoboLions2015.subsystems.DriveTrain;

/**
 *
 */
public class AutoGyroDriveForward extends Command {

	private double distanceTarget; 
	private double outputPower;
	
	public AutoGyroDriveForward(double feet, double power) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		requires(Robot.driveTrain);
		distanceTarget = feet;
		outputPower = power;
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
		double curveOffset = angleError * DriveTrain.gyro_kP;
		Robot.driveTrain.getRobotDrive().drive(-outputPower, -curveOffset);
		//Robot.driveTrain.getRobotDrive().drive(Math.max(-outputPower * timer.get() * 1, -outputPower), -curveOffset);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (Robot.driveTrain.distanceTraveled() >= distanceTarget);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.getRobotDrive().stopMotor();
    	// Robot.driveTrain.resetEncoders();
    	// Robot.driveTrain.resetGyro();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
