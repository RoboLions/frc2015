package org.usfirst.frc1261.RoboLions2015.commands;

import org.usfirst.frc1261.RoboLions2015.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TotePush extends CommandGroup {
    
    public  TotePush() {
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
    	addSequential(new Raise1Unit());
    	addSequential(new Raise1Unit());
    	addSequential(new AutoGyroRightTurn(80 + DriveTrain.turnRightOffset));
    	addSequential(new AutoGyroDriveForward(DriveTrain.cratesToAutoZone, DriveTrain.forwardSpeed - DriveTrain.forwardIncrementSpeed));
    	addSequential(new AutoLowerLift());
    	addSequential(new PistonOut());
    	
    }
}
