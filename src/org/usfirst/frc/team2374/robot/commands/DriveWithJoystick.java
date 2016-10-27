package org.usfirst.frc.team2374.robot.commands;

public class DriveWithJoystick extends CommandBase {

    public DriveWithJoystick() {
        super("DriveWithJoystick");
        requires(CommandBase.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        CommandBase.drivetrain.tankDrive(CommandBase.oi.getJoystick());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    protected void end() {
        CommandBase.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
