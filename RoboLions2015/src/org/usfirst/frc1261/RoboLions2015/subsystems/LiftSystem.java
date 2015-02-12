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

import org.usfirst.frc1261.RoboLions2015.RobotMap;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


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
    
    private static final double LIFT_ENCODER_MIN = -7.5;
    private static final double LIFT_ENCODER_MAX = 680.0;
    
    // These values should be safe values, i.e. going to these values will never break the robot.
    private static final double OVERRIDE_LIFT_ENCODER_MIN = 2.5;
    private static final double OVERRIDE_LIFT_ENCODER_MAX = 660.0;
    
    // These values are used for calibrating the encoder in the commands BringLiftDown/Up.
    public static final double CALIBRATION_LIFT_ENCODER_MIN = 0.0;
    public static final double CALIBRATION_LIFT_ENCODER_MAX = 675.0;
    
    public boolean override = false;
    
//    private static final double LIFT_ENCODER_MIN = -5000.0;
//    private static final double LIFT_ENCODER_MAX = 5000.0;
    
    private static double[] SETPOINTS = {57.0, 203.0, 361.0, 495.0, 645.0};
    
    // PID constants
    private static final double kP = 0.015;
    private static final double kI = 0.00005;
    private static final double kD = 0.005;
    private static final double TOLERANCE = 5.0;
    
    private double liftEncoderResetValue = 0.0;
    
    // Initialize your subsystem here
    public LiftSystem() {
        super("LiftSystem", kP, kI, kD);
        setAbsoluteTolerance(TOLERANCE);
        getPIDController().setContinuous(false);
        LiveWindow.addActuator("LiftSystem", "PIDSubsystem Controller", getPIDController());
        
        Arrays.sort(SETPOINTS);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
        
        stopLift();
        
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return getLiftHeight();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	backLiftMotor.pidWrite(output);
    	frontLiftMotor.pidWrite(output);
    }
    
    public boolean hitLowerLimit() {
    	return (override && getLiftHeight() > OVERRIDE_LIFT_ENCODER_MIN) ? false : lowerLimit.get();
    }
    
    public boolean hitUpperLimit() {
    	return (override && getLiftHeight() < OVERRIDE_LIFT_ENCODER_MAX) ? false : upperLimit.get();
    }

    public double getLiftHeight() {
    	return -liftEncoder.get() - liftEncoderResetValue;
    }
    
    public void raiseLift() {
    	if (hitUpperLimit()) {
    		stopLift();
    		return;
    	}
    	if (getPIDController().isEnable()) disable();
    	setSetpoint(LIFT_ENCODER_MAX);
    	enable();
    }
    
    public void lowerLift() {
    	if (hitLowerLimit()) {
    		stopLift();
    		return;
    	}
    	if (getPIDController().isEnable()) disable();
    	setSetpoint(LIFT_ENCODER_MIN);
    	enable();
    }
    
    public void raiseLiftOneLevel() {
    	if (hitUpperLimit()) {
    		stopLift();
    		return;
    	}
    	double currentValue = returnPIDInput();
    	double setpoint;
    	int arrayIndex = 0;
    	while (arrayIndex < SETPOINTS.length && SETPOINTS[arrayIndex] <= currentValue + TOLERANCE) {
    		arrayIndex++;
    	}
    	setpoint = (arrayIndex >= SETPOINTS.length) ? LIFT_ENCODER_MAX : SETPOINTS[arrayIndex];
    	// Assumes the array is sorted, which is why I call Array.sort in the constructor.
    	if (getPIDController().isEnable()) disable();
    	setSetpoint(setpoint);
    	enable();
    }
    
    public void lowerLiftOneLevel() {
    	if (hitLowerLimit()) {
    		stopLift();
    		return;
    	}
    	double currentValue = returnPIDInput();
    	double setpoint;
    	int arrayIndex = SETPOINTS.length - 1;
    	while (arrayIndex >= 0 && SETPOINTS[arrayIndex] >= currentValue - TOLERANCE) {
    		arrayIndex--;
    	}
    	setpoint = (arrayIndex < 0) ? LIFT_ENCODER_MIN : SETPOINTS[arrayIndex];
    	// Assumes the array is sorted, which is why I call Array.sort in the constructor.
    	if (getPIDController().isEnable()) disable();
    	setSetpoint(setpoint);
    	enable();
    }
    
    public void resetLiftHeight() {
    	calibrateLiftHeight(0.0);
    }
    
    public void calibrateLiftHeight(double liftHeight) {
    	if (getPIDController().isEnable()) disable();
    	liftEncoderResetValue += getLiftHeight() - liftHeight;
    	stopLift();
    }
    
    public void stopLift() {
    	if (getPIDController().isEnable()) disable();
    	setSetpoint(returnPIDInput());
    	enable();
    }
    
    public void setLiftSpeed(double liftSpeed) {
    	if ((liftSpeed > 0.0 && hitUpperLimit()) || (liftSpeed < 0.0 && hitLowerLimit())) {
    		setLiftSpeed(0.0);
    		return;
    	}
    	if (getPIDController().isEnable()) disable();
    	backLiftMotor.set(liftSpeed);
    	frontLiftMotor.set(liftSpeed);
    }
//    
//    public void raiseLift()
//    {
//    	backLiftMotor.set(1.0);
//    	frontLiftMotor.set(1.0);
//    }
//        
//    public void lowerLift()
//    {
//    	backLiftMotor.set(-1.0);
//    	frontLiftMotor.set(-1.0);
//    }
//    
//    public void stopLift()
//    {
//    	backLiftMotor.set(0.0);
//    	frontLiftMotor.set(0.0);
//    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
}

