// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc1261.RoboLions2015.subsystems;

import org.usfirst.frc1261.RoboLions2015.RobotMap;
import org.usfirst.frc1261.RoboLions2015.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends PIDSubsystem {

	public static final double gyro_kP = 0.04;
	public static final double forwardSpeed = 0.43;
	public static final double turnSpeed = 0.27;
	public static final double turnRightOffset = -5.3;
	public static final double turnLeftOffset = -4;
	public static final double forwardOffset = -1.5;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Encoder leftEncoder = RobotMap.driveTrainLeftEncoder;
    Encoder rightEncoder = RobotMap.driveTrainRightEncoder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	CANTalon rightFrontDrive = RobotMap.driveTrainrightFrontDrive;
	CANTalon rightBackDrive = RobotMap.driveTrainrightBackDrive;
	CANTalon leftBackDrive = RobotMap.driveTrainleftBackDrive;
	CANTalon leftFrontDrive = RobotMap.driveTrainleftFrontDrive;
	RobotDrive robotDrive = RobotMap.driveTrainrobotDrive;

	Gyro gyro = RobotMap.gyro;

	boolean straightController = true;

	private static final double straight_kP = 0.045;
	private static final double straight_kI = 0.0;
	private static final double straight_kD = 0.012;

	private static final double tolerance = 10.0;

	private static final double turn_kP = 0.036;
	private static final double turn_kI = 0;
	private static final double turn_kD = 0.010;

	Timer t = new Timer();

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	int pulsesPerRotation = 360;
	double diameterInches = 6;

	public DriveTrain() {
		super("Straight controller", straight_kP, straight_kI, straight_kD);
		setAbsoluteTolerance(10.0);
		getPIDController().setContinuous(false);

	}

	public RobotDrive getRobotDrive() {
		return robotDrive;
	}

	public double pulsesTraveled() {
		return (leftEncoder.getRaw() + rightEncoder.getRaw()) / 2;
	}

	public double distanceTraveled() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
	}

	public void resetDistance() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public SpeedController getLeftDrive() {
		return leftFrontDrive;
	}

	public SpeedController getRightDrive() {
		return rightFrontDrive;
	}

	public Encoder getLeftEncoder() {
		return leftEncoder;
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
	}

	public Gyro getGyro() {
		return gyro;
	}

	public void resetGyro() {
		gyro.reset();
	}

	public double getAngle() {
		return gyro.getAngle();
	}

	public double distanceTravelled() {
		return ((leftEncoder.get() / 360 * Math.PI * 6) + (rightEncoder.get()
				/ 360 * Math.PI * 6)) / 2;
	}

	public void setStraightController() {
		straightController = true;
		getPIDController().setPID(straight_kP, straight_kI, straight_kD);
	}

	public void setTurnController() {
		straightController = false;
		getPIDController().setPID(turn_kP, turn_kI, turn_kD);
	}

	public void startTimer() {
		t.start();
	}

	public void stopTimer() {
		t.stop();
	}

	public double getTime() {
		return t.get();
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new DriveWithJoysticks());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return (straightController ? distanceTravelled() : gyro.getAngle());
	}

	protected void usePIDOutput(double output) {
		if (straightController) {
			rightFrontDrive.pidWrite(output);
			rightBackDrive.pidWrite(output);
			leftFrontDrive.pidWrite(output);
			leftBackDrive.pidWrite(output);
		} else {
			rightFrontDrive.pidWrite(-output);
			rightBackDrive.pidWrite(-output);
			leftFrontDrive.pidWrite(output);
			leftBackDrive.pidWrite(output);
		}
	}

	public void moveForward(double distance) {
		setStraightController();
		setSetpoint(distance);
		enable();
	}

	public void turn(double angle) {
		setTurnController();
		setSetpoint(angle);
		enable();
	}

	public void stopDriveTrain() {
		setStraightController();
		setSetpoint(distanceTravelled());
		setTurnController();
		setSetpoint(getAngle());
		disable();
	}

}
