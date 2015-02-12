package org.usfirst.frc1261.RoboLions2015.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc1261.RoboLions2015.Robot;
import org.usfirst.frc1261.RoboLions2015.commands.*;
/**
 *
 */
public class TestAutonomous extends CommandGroup {
    
    public  TestAutonomous() {
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
    	addSequential(new AutoGyroDriveForward(5));
    	addSequential(new AutoGyroRightTurn(90 + Robot.driveTrain.turnRightOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroRightTurn(90 + Robot.driveTrain.turnRightOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroRightTurn(90 + Robot.driveTrain.turnRightOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroRightTurn(90 + Robot.driveTrain.turnRightOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroRightTurn(90 + Robot.driveTrain.turnRightOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroRightTurn(90 + Robot.driveTrain.turnRightOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroRightTurn(90 + Robot.driveTrain.turnRightOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroRightTurn(90 + Robot.driveTrain.turnRightOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroLeftTurn(90 + Robot.driveTrain.turnLeftOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroLeftTurn(90 + Robot.driveTrain.turnLeftOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroLeftTurn(90 + Robot.driveTrain.turnLeftOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroLeftTurn(90 + Robot.driveTrain.turnLeftOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroLeftTurn(90 + Robot.driveTrain.turnLeftOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroLeftTurn(90 + Robot.driveTrain.turnLeftOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroLeftTurn(90 + Robot.driveTrain.turnLeftOffset));
    	addSequential(new AutoGyroDriveForward(6 + Robot.driveTrain.forwardOffset));
    	addSequential(new AutoGyroLeftTurn(90 + Robot.driveTrain.turnLeftOffset));
    	
    }
}
