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

import java.util.Arrays;

import org.usfirst.frc1261.RoboLions2015.Robot;
import org.usfirst.frc1261.RoboLions2015.RobotMap;
import org.usfirst.frc1261.RoboLions2015.commands.HoldLift;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class LiftSystem extends PIDSubsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Encoder liftEncoder = RobotMap.liftSystemLiftEncoder;
    DigitalInput upperLimit = RobotMap.liftSystemupperLimit;
    DigitalInput lowerLimit = RobotMap.liftSystemlowerLimit;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    CANTalon backLiftMotor = RobotMap.liftSystembackLiftMotor;
    CANTalon frontLiftMotor = RobotMap.liftSystemfrontLiftMotor;
    
    private static final double LIFT_ENCODER_MIN;
    private static final double LIFT_ENCODER_MAX;
    
    // These values should be safe values, i.e. going to these values will never break the robot.
    private static final double OVERRIDE_LIFT_ENCODER_MIN;
    private static final double OVERRIDE_LIFT_ENCODER_MAX;
    
    // These values are used for calibrating the encoder in the commands BringLiftDown/Up.
    public static final double CALIBRATION_LIFT_ENCODER_MIN;
    public static final double CALIBRATION_LIFT_ENCODER_MAX;
    
    public static final double LIFT_INCHES_MIN;
    public static final double LIFT_INCHES_MAX;
    
    // Max lift motor speed
    private static final double MAX_LIFT_SPEED = 0.65;
    // Max lift motor speed in turbo mode
    private static final double MAX_TURBO_LIFT_SPEED = 1.0;
    
    public boolean override = false;
    
    // PID constants
    private static final double kP = 0.05;
    private static final double kI = 0.0;
    private static final double kD = 0.01;
    private static final double TOLERANCE = 5.0;
    
    private static double[] SETPOINTS = {62.0, 208.0, 366.0, 500.0, 650.0};
    // private static double[] SETPOINTS = {467.0};
    private static double ENCODER_LEVEL_INCREMENT = 150.0;
    
    private static final double SETPOINT_TOLERANCE = 1.5 * TOLERANCE;
    
    static {
    	switch (Robot.getRobotId()) {
    	case 1:
    		LIFT_ENCODER_MIN = -7.5;
    		LIFT_ENCODER_MAX = 675.0;
    		OVERRIDE_LIFT_ENCODER_MIN = 2.5;
    		OVERRIDE_LIFT_ENCODER_MAX = 665.0;
    		CALIBRATION_LIFT_ENCODER_MIN = 0.0;
    		CALIBRATION_LIFT_ENCODER_MAX = 675.0;
    		LIFT_INCHES_MIN = 6; // Untested
    		LIFT_INCHES_MAX = 63; // Untested
    		break;
    	case 2:
		default:
			LIFT_ENCODER_MIN = -2.5;
			LIFT_ENCODER_MAX = 685.0;
			OVERRIDE_LIFT_ENCODER_MIN = 2.5;
			OVERRIDE_LIFT_ENCODER_MAX = 680.0;
			CALIBRATION_LIFT_ENCODER_MIN = 0.0;
			CALIBRATION_LIFT_ENCODER_MAX = 683.0;
			LIFT_INCHES_MIN = 6;
			LIFT_INCHES_MAX = 63;
			break;
    	}
    }
    
    private double liftEncoderResetValue = 0.0;
    
    private boolean calibrated = false;
    
    public class LiftNotCalibratedException extends Exception {

		private static final long serialVersionUID = 2653145509710812617L;
    	
    }
    
    // Initialize your subsystem here
    public LiftSystem() {
        super("LiftSystem", kP, kI, kD);
        setAbsoluteTolerance(TOLERANCE);
        getPIDController().setContinuous(false);
        setOutputRange(-MAX_LIFT_SPEED, MAX_LIFT_SPEED);
        LiveWindow.addActuator("LiftSystem", "PIDSubsystem Controller", getPIDController());
        
        Arrays.sort(SETPOINTS);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
        
        SmartDashboard.putNumber("Current Setpoint: ", 0.0);
        
        hitLowerLimit();
        hitUpperLimit();
    }
    
    public boolean isCalibrated() {
    	return calibrated;
    }
    
    public void uncalibrate() {
    	if (getPIDController().isEnable()) disable();
    	calibrated = false;
    	liftEncoderResetValue = 0.0;
    	getPIDController().reset();
    	stopLift();
    }
    
    public void setSetpoint(double setpoint) {
    	SmartDashboard.putNumber("Current Setpoint: ", setpoint);
    	super.setSetpoint(setpoint);
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new HoldLift());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	try {
			return getLiftHeight();
		} catch (LiftNotCalibratedException e) {
			return getRawLiftHeight();
		}
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	if ((output < 0.0 && hitLowerLimit()) || (output > 0.0 && hitUpperLimit())) {
    		usePIDOutput(0.0);
    		return;
    	}
    	backLiftMotor.pidWrite(output);
    	frontLiftMotor.pidWrite(output);
    }
    
    public boolean hitLowerLimit() {
    	boolean value = lowerLimit.get();
    	if (!calibrated && value) calibrateLiftHeight(CALIBRATION_LIFT_ENCODER_MIN);
    	if (override) {
    		try {
				return getLiftHeight() <= OVERRIDE_LIFT_ENCODER_MIN;
			} catch (LiftNotCalibratedException e) {
				override = false;
				return value;
			}
    	} else {
    		return value;
    	}
    }
    
    public boolean hitUpperLimit() {
    	boolean value = upperLimit.get();
    	if (!calibrated && value) calibrateLiftHeight(CALIBRATION_LIFT_ENCODER_MAX);
    	if (override) {
    		try {
				return getLiftHeight() >= OVERRIDE_LIFT_ENCODER_MAX;
			} catch (LiftNotCalibratedException e) {
				override = false;
				return value;
			}
    	} else {
    		return value;
    	}
    }

    public double getLiftHeight() throws LiftNotCalibratedException {
    	if (!calibrated) throw new LiftNotCalibratedException();
    	return -liftEncoder.get() - liftEncoderResetValue;
    }
    
    public double getRawLiftHeight() {
    	return -liftEncoder.get();
    }
    
    public void raiseLift() {
    	if (hitUpperLimit()) {
    		stopLift();
    		return;
    	}
    	if (getPIDController().isEnable()) disable();
    	if (calibrated) {
    		setSetpoint(LIFT_ENCODER_MAX);
    		getPIDController().reset();
        	enable();
    	} else {
    		setLiftSpeed(MAX_LIFT_SPEED);
    	}
    }
    
    public void lowerLift() {
    	if (hitLowerLimit()) {
    		stopLift();
    		return;
    	}
    	if (getPIDController().isEnable()) disable();
    	if (calibrated) {
	    	setSetpoint(LIFT_ENCODER_MIN);
	    	getPIDController().reset();
	    	enable();
    	} else {
    		setLiftSpeed(-MAX_LIFT_SPEED);
    	}
    }
    
    public void raiseLiftOneLevel() {
    	if (hitUpperLimit()) {
    		stopLift();
    		return;
    	}
    	if (calibrated) {
	    	double currentValue = returnPIDInput();
	    	double setpoint;
	    	int arrayIndex = 0;
	    	while (arrayIndex < SETPOINTS.length && SETPOINTS[arrayIndex] <= currentValue + SETPOINT_TOLERANCE) {
	    		arrayIndex++;
	    	}
	    	setpoint = (arrayIndex >= SETPOINTS.length) ? LIFT_ENCODER_MAX : SETPOINTS[arrayIndex];
	    	// Assumes the array is sorted, which is why I call Array.sort in the constructor.
	    	if (getPIDController().isEnable()) disable();
	    	setSetpoint(setpoint);
	    	getPIDController().reset();
	    	enable();
    	} else {
    		setSetpoint(returnPIDInput() + ENCODER_LEVEL_INCREMENT);
    	}
    }
    
    public void lowerLiftOneLevel() {
    	if (hitLowerLimit()) {
    		stopLift();
    		return;
    	}
    	if (calibrated) {
	    	double currentValue = returnPIDInput();
	    	double setpoint;
	    	int arrayIndex = SETPOINTS.length - 1;
	    	while (arrayIndex >= 0 && SETPOINTS[arrayIndex] >= currentValue - SETPOINT_TOLERANCE) {
	    		arrayIndex--;
	    	}
	    	setpoint = (arrayIndex < 0) ? LIFT_ENCODER_MIN : SETPOINTS[arrayIndex];
	    	// Assumes the array is sorted, which is why I call Array.sort in the constructor.
	    	if (getPIDController().isEnable()) disable();
	    	setSetpoint(setpoint);
	    	getPIDController().reset();
	    	enable();
    	} else {
    		setSetpoint(returnPIDInput() - ENCODER_LEVEL_INCREMENT);
    	}
    }
    
    public void moveLiftTo(double setpoint) {
    	double currentValue = returnPIDInput();
    	if ((setpoint <= currentValue && hitLowerLimit()) || (setpoint >= currentValue && hitUpperLimit())) {
    		stopLift();
    		return;
    	}
    	if (getPIDController().isEnable()) disable();
    	if (calibrated) {
	    	setSetpoint(setpoint);
	    	getPIDController().reset();
	    	enable();
    	} else {
    		setSetpoint(returnPIDInput() + setpoint);
    	}
    }
    
    public void moveLiftTo(double setpoint, double power)
    {
    	setLiftPower(power);
    	moveLiftTo(setpoint);
    }
    
    public void resetLiftHeight() {
    	calibrateLiftHeight(0.0);
    }
    
    public void calibrateLiftHeight(double liftHeight) {
    	if (getPIDController().isEnable()) disable();
    	calibrated = true;
    	try {
			liftEncoderResetValue += getLiftHeight() - liftHeight;
		} catch (LiftNotCalibratedException e) {
			liftEncoderResetValue = -liftHeight;
		}
    	getPIDController().reset();
    	stopLift();
    }
    
    public void stopLift() {
    	setLiftSpeed(0.0);
    	resetLiftPower();
    }
    
    public void setLiftPower(double power) {
    	setOutputRange(-power, power);
    }
    
    public void enableLiftTurboMode() {
    	setLiftPower(MAX_TURBO_LIFT_SPEED);
    }
    
    public void resetLiftPower() {
    	setLiftPower(MAX_LIFT_SPEED);
	}

	public void holdLift() {
    	if (getPIDController().isEnable()) disable();
    	setSetpoint(returnPIDInput());
    	getPIDController().reset();
    	enable();
    }
    
    public void setLiftSpeed(double liftSpeed) {
    	if ((liftSpeed < 0.0 && hitLowerLimit()) || (liftSpeed > 0.0 && hitUpperLimit())) {
    		setLiftSpeed(0.0);
    		return;
    	}
    	if (getPIDController().isEnable()) disable();
    	backLiftMotor.set(Math.max(Math.min(liftSpeed, MAX_LIFT_SPEED), -MAX_LIFT_SPEED));
    	frontLiftMotor.set(Math.max(Math.min(liftSpeed, MAX_LIFT_SPEED), -MAX_LIFT_SPEED));
    }
    
    public static double convertInchesToLiftHeight(double inches) {
    	return (inches - LIFT_INCHES_MIN) / ((LIFT_INCHES_MAX - LIFT_INCHES_MIN) / (CALIBRATION_LIFT_ENCODER_MAX - CALIBRATION_LIFT_ENCODER_MIN)) + CALIBRATION_LIFT_ENCODER_MIN;
    }
    
    
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
}

