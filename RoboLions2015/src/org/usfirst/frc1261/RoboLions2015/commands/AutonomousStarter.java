package org.usfirst.frc1261.RoboLions2015.commands;

import org.usfirst.frc1261.RoboLions2015.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousStarter extends Command {

	/**
	 * The key for the selected option
	 */
	private static final String SELECTED = "selected";
	
	private static final double TIMEOUT = 2.5;
	private static final Command DEFAULT_AUTONOMOUS = new AutoContainer();
	
	private Command selectedAuto = null;
	private Timer timer = new Timer();
	
    public AutonomousStarter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	String selected = Robot.autoChooser.getTable().getString(SELECTED, null);
    	if (selected == null) return;
    	selectedAuto = (Command) Robot.autoChooser.getSelected();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((selectedAuto != null) || (timer.get() >= TIMEOUT));
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	if (selectedAuto != null) selectedAuto.start();
    	else DEFAULT_AUTONOMOUS.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	timer.stop();
    }
}
