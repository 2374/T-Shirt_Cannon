package org.usfirst.frc.team2374.robot.commands;

public class PressureControl extends CommandBase {

    public PressureControl() {
        super("PressureControl");
        requires(CommandBase.pneumatics);
    }
    
    protected void initialize() {
        CommandBase.pneumatics.startCompressor();
    } 

    protected void execute() {
        /*
        if (CommandBase.pneumatics.isPressurized())
        {
            if (CommandBase.pneumatics.isEnabled())
                CommandBase.pneumatics.stopCompressor();
        }
        else
        {
            if(!CommandBase.pneumatics.isEnabled())
                CommandBase.pneumatics.startCompressor();
        }
        */
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        CommandBase.pneumatics.stopCompressor();
    }

    protected void interrupted() {
        end();
    }
    
}
