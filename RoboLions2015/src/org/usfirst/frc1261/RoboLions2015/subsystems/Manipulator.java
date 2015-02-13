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
	AnalogInput transducer = new AnalogInput(0);

	double leastAmountOfPressureNeeded = 100;

	private static final double VOLTS_TO_PRESSURE_FACTOR = 30.0;
	private static final double PRESSURE_THRESHOLD = 40.0; // When the pressure is above this level, the pressure light on SmartDashboard is green.
	
	public double getPressure(){
		return transducer.getVoltage() * VOLTS_TO_PRESSURE_FACTOR;
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

	public boolean getPressureLight() {
		return getPressure() >= PRESSURE_THRESHOLD;
	}
}
