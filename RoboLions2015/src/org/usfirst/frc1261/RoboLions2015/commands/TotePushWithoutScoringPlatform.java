package org.usfirst.frc1261.RoboLions2015.commands;

import org.usfirst.frc1261.RoboLions2015.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TotePushWithoutScoringPlatform extends CommandGroup {
    
    public  TotePushWithoutScoringPlatform() {
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
    	addSequential(new BringLiftDown());
    	addSequential(new PistonIn());
    	addSequential(new WaitCommand(1.0));
    	addSequential(new GoToLiftPosition(208.0, 1.0));
    	addSequential(new AutoGyroRightTurn(80 + DriveTrain.turnRightOffset));
    	addSequential(new AutoGyroDriveForward(DriveTrain.cratesToAutoZoneWithoutScoringPlatform, DriveTrain.forwardSpeed - DriveTrain.forwardIncrementSpeed));
    	addSequential(new AutoLowerLift());
    	addSequential(new PistonOut());
    	
    }
}
