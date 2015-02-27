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

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1261.RoboLions2015.commands.*;
import org.usfirst.frc1261.RoboLions2015.subsystems.*;
import org.usfirst.frc1261.RoboLions2015.subsystems.LiftSystem.LiftNotCalibratedException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
    Command autonomousCommand;

    public static OI oi;

    public static DriveTrain driveTrain;
    public static LiftSystem liftSystem;
    public static Manipulator manipulator;
    
    private static int ROBOT_ID = 2;
//    private CameraServer cameraServer;
//    private static final int HIGH_QUALITY = 50;
//    private int cameraSession;
//    private Image cameraFrame;
    
    private SendableChooser autoChooser;
    
    public static int getRobotId() {
    	ROBOT_ID = Preferences.getInstance().getInt("RobotID", 2);
    	SmartDashboard.putNumber("Robot ID: ", ROBOT_ID);
    	return ROBOT_ID;
    }
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        liftSystem = new LiftSystem();
        manipulator = new Manipulator();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    	
        // OI must be constructed after subsystems. If the OI creates Commands 
        //(which it very likely will), subsystems are not guaranteed to be 
        // constructed yet. Thus, their requires() statements may grab null 
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        //autonomousCommand = new AutoGyroDriveForward(8 + Robot.driveTrain.forwardOffset, Robot.driveTrain.forwardSpeed);
        //autonomousCommand = new Auto3ToteStackWithScoringPlatform();
        
        SmartDashboard.putBoolean(" Holding Lift", false);
        
        // Autonomous chooser
        autoChooser = new SendableChooser();
        autoChooser.addDefault("3 Tote Stack (with scoring platform)", new Auto3ToteStackWithScoringPlatform());
        autoChooser.addObject("1 Tote Push (with scoring platform)", new TotePushWithScoringPlatform());
        autoChooser.addObject("3 Tote Stack (without scoring platform)", new Auto3ToteStackWithoutScoringPlatform());
        autoChooser.addObject("1 Tote Push (without scoring platform)", new TotePushWithoutScoringPlatform());
        autoChooser.addObject("Container Pull (without scoring platform)", new AutoContainer());
        autoChooser.addObject("None", new DummyCommand());
        SmartDashboard.putData("Autonomous", autoChooser);
        
        // Camera
//        cameraServer = CameraServer.getInstance();
//        cameraServer.setQuality(HIGH_QUALITY);
//        cameraFrame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
//        cameraSession = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
//        NIVision.IMAQdxConfigureGrab(cameraSession);
//        NIVision.IMAQdxStartAcquisition(cameraSession);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	Robot.liftSystem.override = false;
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        allPeriodic();
    }

    public void autonomousInit() {
    	autonomousCommand = (Command) autoChooser.getSelected();
    	
    	driveTrain.setSlowRampRate();
    	
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        allPeriodic();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        driveTrain.setFastRampRate();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        allPeriodic();
    }
    
    public void allPeriodic() {
    	SmartDashboard.putBoolean(" Upper Limit Switch", liftSystem.hitUpperLimit());
        SmartDashboard.putBoolean(" Lower Limit Switch", liftSystem.hitLowerLimit());
        try {
			SmartDashboard.putNumber("Lift Encoder: ", liftSystem.getLiftHeight());
		} catch (LiftNotCalibratedException e) {
			SmartDashboard.putNumber("Lift Encoder: ", liftSystem.getRawLiftHeight());
		}
        SmartDashboard.putNumber("Left Encoder: ", driveTrain.getLeftEncoder().get());
        SmartDashboard.putNumber("Left Encoder Distance: ", driveTrain.getLeftEncoder().getDistance());
        SmartDashboard.putNumber("Right Encoder: ", driveTrain.getRightEncoder().get());
        SmartDashboard.putNumber("Right Encoder Distance: ", driveTrain.getRightEncoder().getDistance());
        SmartDashboard.putNumber("Pulses Traveled: ", driveTrain.pulsesTraveled());
        SmartDashboard.putNumber("Distance Traveled: ", driveTrain.distanceTraveled());
        SmartDashboard.putNumber("Pressure: ", manipulator.getPressure());
        SmartDashboard.putBoolean(" Override Enabled", liftSystem.override);
        SmartDashboard.putBoolean(" Pressure Light", manipulator.getPressureLight());
        SmartDashboard.putBoolean(" Lift Calibrated", liftSystem.isCalibrated());
        SmartDashboard.putNumber("Gyro: ", driveTrain.getAngle());
        SmartDashboard.putString("Current Autonomous: ", ((Command) autoChooser.getSelected()).getName());
        SmartDashboard.putData(Scheduler.getInstance());
        SmartDashboard.putData("Autonomous", autoChooser);
        
        // Camera
//        NIVision.IMAQdxGrab(cameraSession, cameraFrame, 1);
//        cameraServer.setImage(cameraFrame);
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        allPeriodic();
    }
}
