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

import org.usfirst.frc1261.RoboLions2015.commands.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton driverA;
    public JoystickButton driverB;
    public JoystickButton driverX;
    public JoystickButton driverY;
    public JoystickButton driverLeftBumper;
    public JoystickButton driverRightBumper;
    public JoystickButton driverBack;
    public JoystickButton driverStart;
    public JoystickButton driverLeftStick;
    public JoystickButton driverRightStick;
    public Joystick driverJoystick;
    public JoystickButton manipA;
    public JoystickButton manipB;
    public JoystickButton manipX;
    public JoystickButton manipY;
    public JoystickButton manipLeftBumper;
    public JoystickButton manipRightBumper;
    public JoystickButton manipBack;
    public JoystickButton manipStart;
    public JoystickButton manipLeftStick;
    public JoystickButton manipRightStick;
    public Joystick manipulatorJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        manipulatorJoystick = new Joystick(1);
        
        manipRightStick = new JoystickButton(manipulatorJoystick, 10);
        manipRightStick.whenPressed(new PistonIn());
        manipLeftStick = new JoystickButton(manipulatorJoystick, 9);
        manipLeftStick.whenPressed(new PistonOut());
        manipStart = new JoystickButton(manipulatorJoystick, 8);
        manipStart.whenPressed(new DummyCommand());
        manipBack = new JoystickButton(manipulatorJoystick, 7);
        manipBack.whenPressed(new DummyCommand());
        manipRightBumper = new JoystickButton(manipulatorJoystick, 6);
        manipRightBumper.whileHeld(new ManualRaiseLift());
        manipLeftBumper = new JoystickButton(manipulatorJoystick, 5);
        manipLeftBumper.whileHeld(new ManualLowerLift());
        manipY = new JoystickButton(manipulatorJoystick, 4);
        manipY.whenPressed(new Raise1Unit());
        manipX = new JoystickButton(manipulatorJoystick, 3);
        manipX.whenPressed(new Lower1Unit());
        manipB = new JoystickButton(manipulatorJoystick, 2);
        manipB.whenPressed(new AutoRaiseLift());
        manipA = new JoystickButton(manipulatorJoystick, 1);
        manipA.whenPressed(new AutoLowerLift());
        driverJoystick = new Joystick(0);
        
        driverRightStick = new JoystickButton(driverJoystick, 10);
        driverRightStick.whenPressed(new DummyCommand());
        driverLeftStick = new JoystickButton(driverJoystick, 9);
        driverLeftStick.whenPressed(new DummyCommand());
        driverStart = new JoystickButton(driverJoystick, 8);
        driverStart.whenPressed(new DummyCommand());
        driverBack = new JoystickButton(driverJoystick, 7);
        driverBack.whenPressed(new DummyCommand());
        driverRightBumper = new JoystickButton(driverJoystick, 6);
        driverRightBumper.whenPressed(new DummyCommand());
        driverLeftBumper = new JoystickButton(driverJoystick, 5);
        driverLeftBumper.whenPressed(new DummyCommand());
        driverY = new JoystickButton(driverJoystick, 4);
        driverY.whenPressed(new DummyCommand());
        driverX = new JoystickButton(driverJoystick, 3);
        driverX.whenPressed(new DummyCommand());
        driverB = new JoystickButton(driverJoystick, 2);
        driverB.whenPressed(new DummyCommand());
        driverA = new JoystickButton(driverJoystick, 1);
        driverA.whenPressed(new DummyCommand());

	    
        // SmartDashboard Buttons
        SmartDashboard.putData("Raise1Unit", new Raise1Unit());

        SmartDashboard.putData("AutoLowerLift", new AutoLowerLift());

        SmartDashboard.putData("AutoRaiseLift", new AutoRaiseLift());

        SmartDashboard.putData("ManualLowerLift", new ManualLowerLift());

        SmartDashboard.putData("ManualRaiseLift", new ManualRaiseLift());

        SmartDashboard.putData("AutoDriveForward", new AutoDriveForward());

        SmartDashboard.putData("Lower1Unit", new Lower1Unit());

        SmartDashboard.putData("Reset Lift Encoder", new ResetLiftEncoder());

        SmartDashboard.putData("PistonIn", new PistonIn());

        SmartDashboard.putData("PistonOut", new PistonOut());


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        SmartDashboard.putData("Toggle Limit Switch Override", new ToggleLiftOverride());
        SmartDashboard.putData("Bring Lift Down", new BringLiftDown());
        SmartDashboard.putData("Bring Lift Up", new BringLiftUp());
    }
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriverJoystick() {
        return driverJoystick;
    }

    public Joystick getManipulatorJoystick() {
        return manipulatorJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

