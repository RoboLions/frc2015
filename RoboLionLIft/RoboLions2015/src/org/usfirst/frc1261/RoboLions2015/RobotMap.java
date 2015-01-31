// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1261.RoboLions2015;
    
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.util.Vector;

import org.usfirst.frc1261.RoboLions2015.subsystems.DriveTrain;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Encoder driveTrainLeftEncoder;
    public static Gyro driveTrainGyro;
    public static Encoder driveTrainRightEncoder;
    public static Ultrasonic driveTrainUltrasonic;
    public static Encoder liftSystemLiftEncoder;
    public static DigitalInput liftSystemupperLimit;
    public static DigitalInput liftSystemlowerLimit;
    public static DigitalInput liftSystemliftPhotoSensor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static CANTalon liftSystembackLiftMotor;
    public static CANTalon liftSystemfrontLiftMotor;
    public static CANTalon driveTrainrightFrontDrive;
    public static CANTalon driveTrainrightBackDrive;
    public static CANTalon driveTrainleftBackDrive;
    public static CANTalon driveTrainleftFrontDrive;
    public static RobotDrive driveTrainrobotDrive;

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainLeftEncoder = new Encoder(8, 9, true, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "LeftEncoder", driveTrainLeftEncoder);
        driveTrainLeftEncoder.setDistancePerPulse(1.0);
        driveTrainLeftEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        driveTrainGyro = new Gyro(0);
        LiveWindow.addSensor("DriveTrain", "Gyro", driveTrainGyro);
        driveTrainGyro.setSensitivity(0.007);
        driveTrainRightEncoder = new Encoder(6, 7, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "RightEncoder", driveTrainRightEncoder);
        driveTrainRightEncoder.setDistancePerPulse(1.0);
        driveTrainRightEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        driveTrainUltrasonic = new Ultrasonic(13, 14);
        LiveWindow.addSensor("DriveTrain", "Ultrasonic", driveTrainUltrasonic);
        
        liftSystemLiftEncoder = new Encoder(4, 5, true, EncodingType.k4X);
        LiveWindow.addSensor("LiftSystem", "LiftEncoder", liftSystemLiftEncoder);
        liftSystemLiftEncoder.setDistancePerPulse(1.0);
        liftSystemLiftEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        liftSystemupperLimit = new DigitalInput(2);
        LiveWindow.addSensor("LiftSystem", "upperLimit", liftSystemupperLimit);
        
        liftSystemlowerLimit = new DigitalInput(1);
        LiveWindow.addSensor("LiftSystem", "lowerLimit", liftSystemlowerLimit);
        
        liftSystemliftPhotoSensor = new DigitalInput(10);
        LiveWindow.addSensor("LiftSystem", "liftPhotoSensor", liftSystemliftPhotoSensor);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        liftSystembackLiftMotor = new CANTalon(7);
        
        liftSystemfrontLiftMotor = new CANTalon(8);
        
        driveTrainrightFrontDrive = new CANTalon(3);
        driveTrainrightBackDrive = new CANTalon(4);
        driveTrainleftBackDrive = new CANTalon(5);
        driveTrainleftFrontDrive = new CANTalon(6);
        driveTrainrobotDrive = new RobotDrive(driveTrainleftFrontDrive, driveTrainleftBackDrive, driveTrainrightFrontDrive, driveTrainrightBackDrive);
        driveTrainrobotDrive.setInvertedMotor(MotorType.kFrontRight, true);
        driveTrainrobotDrive.setInvertedMotor(MotorType.kRearRight, true);
        driveTrainrobotDrive.setInvertedMotor(MotorType.kRearLeft, true);
        driveTrainrobotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
    }
}

	
