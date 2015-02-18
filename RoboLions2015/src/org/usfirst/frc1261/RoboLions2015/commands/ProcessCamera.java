package org.usfirst.frc1261.RoboLions2015.commands;

import org.usfirst.frc1261.RoboLions2015.Robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ProcessCamera extends Command {
	
	//private Image cameraFrame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
	
    public ProcessCamera() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.camera);
    	setRunWhenDisabled(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.camera.getCameraServer().startAutomaticCapture("cam0");
    	//NIVision.IMAQdxStartAcquisition(Robot.camera.getCameraSession());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//NIVision.IMAQdxGrab(Robot.camera.getCameraSession(), cameraFrame, 1);
        //Robot.camera.getCameraServer().setImage(cameraFrame);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//NIVision.IMAQdxStopAcquisition(Robot.camera.getCameraSession());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
