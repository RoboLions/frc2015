package org.usfirst.frc1261.RoboLions2015.subsystems;

import org.usfirst.frc1261.RoboLions2015.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Manipulator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	DoubleSolenoid forkPiston = RobotMap.forkSolenoid;
	AnalogInput transducer = new AnalogInput(1);
	
	public double getPressure(){
		//transducer.
		return transducer.getVoltage()*15;
		//TODO Change 15 to a constant named after what it represents
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void pistonOut(){
    	forkPiston.set(DoubleSolenoid.Value.kForward);
    }
    
    public void pistonIn(){
    	forkPiston.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void pistonOff(){
    	forkPiston.set(DoubleSolenoid.Value.kOff);
    }
}

