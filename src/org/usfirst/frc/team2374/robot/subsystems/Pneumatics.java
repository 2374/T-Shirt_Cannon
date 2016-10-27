package org.usfirst.frc.team2374.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2374.robot.commands.PressureControl;

public class Pneumatics extends Subsystem {
    
    Compressor compressor;
    
    public Pneumatics() {
        super("Pneumatics");
        compressor = new Compressor(2, 1);
        //compressor.start();
    }

    protected void initDefaultCommand() {
       setDefaultCommand(new PressureControl());
    }
    
    public boolean isPressurized() {
        return compressor.getPressureSwitchValue();
    }
    
    public void startCompressor() {
        compressor.start();
    }
    
    public void stopCompressor() {
        compressor.stop();
    }
    
    public boolean isEnabled() {
        return compressor.enabled();
    }
    
}
