package org.usfirst.frc1261.RoboLions2015.commands;

import org.usfirst.frc1261.RoboLions2015.Robot;
import org.usfirst.frc1261.RoboLions2015.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoContainerReady extends CommandGroup {
    
    public  AutoContainerReady() {
    	requires(Robot.manipulator);
        requires(Robot.driveTrain);
        requires(Robot.liftSystem);
        
        addSequential(new BringLiftDown());
    	addSequential(new PistonIn());
    	addSequential(new WaitCommand(0.9));
    	addParallel(new GoToLiftPosition(300.0, 1.0));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutoGyroDriveForward(DriveTrain.containerToWall + DriveTrain.forwardOffset, DriveTrain.forwardSpeed));
    }
}
