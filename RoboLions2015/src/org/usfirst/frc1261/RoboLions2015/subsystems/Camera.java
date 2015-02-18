package org.usfirst.frc1261.RoboLions2015.subsystems;

import org.usfirst.frc1261.RoboLions2015.commands.ProcessCamera;

import com.ni.vision.NIVision;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private int cameraSession;
	private CameraServer cameraServer;
	
	private boolean enabled = false;
	
	private static final int HIGH_QUALITY = 30;

	public Camera() {
		cameraServer = CameraServer.getInstance();
		cameraServer.setQuality(HIGH_QUALITY);
		try {
			cameraSession = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			NIVision.IMAQdxConfigureGrab(cameraSession);
			enabled = true;
		} catch (VisionException e) {
			enabled = false;
		}
	}
	
	public CameraServer getCameraServer() {
		return cameraServer;
	}
	
	public boolean isCameraEnabled() {
		return enabled;
	}
	
	public int getCameraSession() {
		return cameraSession;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	if (enabled) setDefaultCommand(new ProcessCamera());
    }
}

