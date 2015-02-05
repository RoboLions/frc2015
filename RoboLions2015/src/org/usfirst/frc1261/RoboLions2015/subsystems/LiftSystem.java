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
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class LiftSystem extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Encoder liftEncoder = RobotMap.liftSystemLiftEncoder;
    DigitalInput upperLimit = RobotMap.liftSystemupperLimit;
    DigitalInput lowerLimit = RobotMap.liftSystemlowerLimit;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    CANTalon backLiftMotor = RobotMap.liftSystembackLiftMotor;
    CANTalon frontLiftMotor = RobotMap.liftSystemfrontLiftMotor;
    
    public class LiftEncoderNotReadyException extends Exception {
    	
		private static final long serialVersionUID = 4622929021347497150L;
    	
    }
    
    private boolean liftEncoderReady = false;
    
    public boolean hitLowerLimit() {
    	return lowerLimit.get();
    }
    
    public boolean hitUpperLimit() {
    	return upperLimit.get();
    }

    public double getLiftHeight() throws LiftEncoderNotReadyException {
    	if (liftEncoderReady) return -liftEncoder.getDistance();
    	else throw new LiftEncoderNotReadyException();
    }
    
    public void raiseLift()
    {
    	backLiftMotor.set(1.0);
    	frontLiftMotor.set(1.0);
    }
        
    public void lowerLift()
    {
    	backLiftMotor.set(-1.0);
    	frontLiftMotor.set(-1.0);
    }
    
    public void stopLift()
    {
    	backLiftMotor.set(0.0);
    	frontLiftMotor.set(0.0);
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

