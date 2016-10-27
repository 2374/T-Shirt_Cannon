package org.usfirst.frc.team2374.robot.commands;

public class FireTurret extends CommandBase {
    
    public FireTurret() {
        super("FireTurret");
        requires(CommandBase.turret);
        setTimeout(1);
    }

    protected void initialize() {
        CommandBase.turret.openValve();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        CommandBase.turret.closeValve();
    }

    protected void interrupted() {
        end();
    }
    
}
