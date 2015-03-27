package org.usfirst.frc1261.RoboLions2015.commands;

import org.usfirst.frc1261.RoboLions2015.Robot;
import org.usfirst.frc1261.RoboLions2015.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoContainerReadyWithScoringPlatformWithTurn extends CommandGroup {
    
    public  AutoContainerReadyWithScoringPlatformWithTurn() {
    	/*
    	requires(Robot.manipulator);
        requires(Robot.driveTrain);
        requires(Robot.liftSystem);
        
        addSequential(new BringLiftDown());
    	addSequential(new PistonIn());
    	addSequential(new WaitCommand(0.9));
    	addParallel(new GoToLiftPosition(300.0, 1.0));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutoGyroDriveForward(DriveTrain.containerToCrate + DriveTrain.forwardOffset, DriveTrain.forwardSpeed));
    	addSequential(new GoToLiftPosition(100.0, 0.9));
    	addSequential(new WaitCommand(.2));
    	addSequential(new PistonOut());
    	addSequential(new WaitCommand(.2));
    	addSequential(new GoToLiftPosition(0.0, 0.9));
    	addSequential(new WaitCommand(.2));
    	addSequential(new PistonIn());
    	addSequential(new WaitCommand(0.9));
    	addParallel(new GoToLiftPosition(300.0, 1.0));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutoGyroDriveForward(DriveTrain.containerToWall + DriveTrain.forwardOffset, DriveTrain.forwardSpeed));
    	*/
        requires(Robot.manipulator);
        requires(Robot.driveTrain);
        requires(Robot.liftSystem);
        
        addSequential(new BringLiftDown());
    	addSequential(new PistonIn());
    	addSequential(new WaitCommand(0.9));
    	addSequential(new GoToLiftPosition(470.0, 1.0));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutoGyroDriveForward(DriveTrain.sevenTenthsInch, DriveTrain.forwardSpeed));
    	addSequential(new WaitCommand(0.5));
//    	addSequential(new AutoGyroDriveForward(DriveTrain.containerToWallScoringPlatform + DriveTrain.forwardOffset, DriveTrain.forwardSpeed));
//    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutoGyroLeftTurn((80.0 + DriveTrain.turnLeftOffset) * (27.0 / 90.0)));
    	// 80 + DriveTrain.turnLeftOffset = (approx.) 90 degrees
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutoGyroDriveForward(DriveTrain.containerToWallScoringPlatform - DriveTrain.sevenTenthsInch + DriveTrain.forwardOffset, DriveTrain.forwardSpeed));
    }
}
