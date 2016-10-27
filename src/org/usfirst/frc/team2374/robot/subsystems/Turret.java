package org.usfirst.frc.team2374.robot.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2374.robot.commands.TurretPitch;

public class Turret extends Subsystem {
    
    SpeedController motor;
    Relay fireValve;
    
    public Turret() {
        super("Turret");
        motor = new Jaguar(5);
        fireValve = new Relay(2);
        if (!fireValve.get().equals(Value.kOff))
            closeValve();
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new TurretPitch());
    }
    
    public void updateMotor(Joystick joy) {
        motor.set(-joy.getRawAxis(6) / 5); //multiplied by a fifth to fine tune the turret
                                           //negative to have D-Pad up make the turret go up
    }
    
    public void updateMotor(double value) {
        motor.set(value);
    }
    
    public double getMotorValue() {
        return motor.get();
    }
  
    public String getFireValveString() {
        if (fireValve.get().equals(Value.kForward))
            return "open";
        else if (fireValve.get().equals(Value.kOff))
            return "closed";
        else
            return "null";
    }
    
    public void openValve() {
        fireValve.set(Value.kForward);
    }
    
    public void closeValve() {
        fireValve.set(Value.kOff);
    }
    
}
