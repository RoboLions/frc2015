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

	double leastAmountOfPressureNeeded = 100;

	private static final double VOLTS_TO_PRESSURE_FACTOR = 15.0;

	public double getPressure() {
		return transducer.getVoltage() * VOLTS_TO_PRESSURE_FACTOR;
	}

	public boolean getQuickkStatus() {
		if ((transducer.getVoltage() * VOLTS_TO_PRESSURE_FACTOR) >= leastAmountOfPressureNeeded) {
			return true;
		} else {
			return false;
		}
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void pistonOut() {
		forkPiston.set(DoubleSolenoid.Value.kForward);
	}

	public void pistonIn() {
		forkPiston.set(DoubleSolenoid.Value.kReverse);
	}

	public void pistonOff() {
		forkPiston.set(DoubleSolenoid.Value.kOff);
	}
}
