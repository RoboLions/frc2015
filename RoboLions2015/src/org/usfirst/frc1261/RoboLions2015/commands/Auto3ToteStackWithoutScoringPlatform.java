package org.usfirst.frc1261.RoboLions2015.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc1261.RoboLions2015.Robot;
import org.usfirst.frc1261.RoboLions2015.subsystems.DriveTrain;
/**
 *
 */
public class Auto3ToteStackWithoutScoringPlatform extends CommandGroup {
    
    public  Auto3ToteStackWithoutScoringPlatform() {
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
    	addSequential(new PistonIn());
    	addSequential(new WaitCommand(0.9)); // That's a lot.
    	// addSequential(new Raise1Unit()); * 2
    	addParallel(new GoToLiftPosition(208.0, 1.0));
    	addSequential(new AutoGyroDriveForward(DriveTrain.crateToCrate + DriveTrain.forwardOffset, DriveTrain.forwardSpeed));
    	addSequential(new GoToLiftPosition(100.0, 0.9));
    	addSequential(new WaitCommand(.2));
    	addSequential(new PistonOut());
    	addSequential(new WaitCommand(.2));
    	addSequential(new GoToLiftPosition(0.0, 0.9));
    	addSequential(new WaitCommand(.2));
    	addSequential(new PistonIn());
    	addSequential(new WaitCommand(.5));
    	addParallel(new GoToLiftPosition(208.0, 1.0));
    	addSequential(new AutoGyroDriveForward(DriveTrain.crateToCrate + DriveTrain.forwardOffset, DriveTrain.forwardSpeed)); // + DriveTrain.forwardIncrementSpeed));
    	addSequential(new GoToLiftPosition(100.0, 0.9));
    	addSequential(new WaitCommand(.2));
    	addSequential(new PistonOut());
    	addSequential(new WaitCommand(.2));
    	addSequential(new GoToLiftPosition(0.0, 0.9));
    	addSequential(new WaitCommand(.2));
    	addSequential(new PistonIn());
    	addSequential(new AutoGyroRightTurn(80 + DriveTrain.turnRightOffset));
    	addSequential(new AutoGyroDriveForward(DriveTrain.cratesToAutoZoneWithoutScoringPlatform, DriveTrain.forwardSpeed)); // + DriveTrain.forwardIncrementSpeed * 2));
    	addSequential(new PistonOut());
    	
    }
}
