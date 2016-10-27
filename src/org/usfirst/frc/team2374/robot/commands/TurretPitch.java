package org.usfirst.frc.team2374.robot.commands;

public class TurretPitch extends CommandBase {
    
    public TurretPitch() {
        super("TurretPitch");
        requires(CommandBase.turret);
    }

    protected void initialize() {
    }

    protected void execute() {
        CommandBase.turret.updateMotor(CommandBase.oi.getJoystick());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        CommandBase.turret.updateMotor(0);
    }

    protected void interrupted() {
        end();
    }
    
    
}
