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

import org.usfirst.frc1261.RoboLions2015.Robot;
import org.usfirst.frc1261.RoboLions2015.RobotMap;
import org.usfirst.frc1261.RoboLions2015.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class LiftSystem extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Encoder liftEncoder = RobotMap.liftSystemLiftEncoder;
    SpeedController liftMotor = RobotMap.liftSystemliftMotor;
    DigitalInput upperLimit = RobotMap.liftSystemupperLimit;
    DigitalInput lowerLimit = RobotMap.liftSystemlowerLimit;
    DigitalInput liftPhotoSensor = RobotMap.liftSystemliftPhotoSensor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public boolean getLowerLimit() {
    	return Robot.liftSystem.lowerLimit.get();
    }
    
    public boolean getUpperLimit() {
    	return Robot.liftSystem.upperLimit.get();
    }
    
    public boolean getPhotoSensor() {
    	return Robot.liftSystem.liftPhotoSensor.get();
    }

    public double getLiftHeight() {
    	return liftEncoder.getDistance();
    }
    
    public void raiseLift()
    {
    	Robot.liftSystem.liftMotor.set(1);
    }
        
    public void lowerLift()
    {
    	Robot.liftSystem.liftMotor.set(-1);
    }
    
    public void stopLift()
    {
    	Robot.liftSystem.liftMotor.set(0);
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

