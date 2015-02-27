package org.usfirst.frc1261.RoboLions2015.commands;

import org.usfirst.frc1261.RoboLions2015.Robot;
import org.usfirst.frc1261.RoboLions2015.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoContainer extends CommandGroup {
    
    public  AutoContainer() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	requires(Robot.driveTrain);
    	requires(Robot.liftSystem);
    	requires(Robot.manipulator);
    	
    	addSequential(new BringLiftDown());
    	addSequential(new GoToLiftPosition(40.0, 1.0));
    	addSequential(new WaitCommand(0.2));
    	addSequential(new PistonIn());
    	addSequential(new WaitCommand(0.2));
    	addSequential(new GoToLiftPosition(250.0, 1.0));
    	addSequential(new WaitCommand(0.2));
    	addSequential(new AutoGyroDriveReverse(6.5, DriveTrain.forwardSpeed));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutoGyroLeftTurn(80 + DriveTrain.turnLeftOffset));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new GoToLiftPosition(40.0, 0.9));
    	addSequential(new WaitCommand(0.2));
    	addSequential(new PistonOut());
    	addSequential(new WaitCommand(0.2));
    	addSequential(new GoToLiftPosition(0.0, 0.9));
    	// 79 to 184
    }
}
