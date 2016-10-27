package org.usfirst.frc.team2374.robot.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team2374.robot.commands.DriveWithJoystick;

public class DriveTrain extends Subsystem {
    
    private SpeedController frontLeftMotor, backLeftMotor,
		frontRightMotor, backRightMotor;
    private RobotDrive drive;
    
    public DriveTrain() {
        super("DriveTrain");
        
        //configures drive motors
        frontLeftMotor = new Jaguar(1);
        backLeftMotor = new Jaguar(2);
        frontRightMotor = new Jaguar(3);
        backRightMotor = new Jaguar(4);
        LiveWindow.addActuator("DriveTrain", "Front Left Motor", (Jaguar) frontLeftMotor);
        LiveWindow.addActuator("DriveTrain", "Back Left Motor", (Jaguar) backLeftMotor);
        LiveWindow.addActuator("DriveTrain", "Front Right Motor", (Jaguar) frontRightMotor);
        LiveWindow.addActuator("DriveTrain", "Back Right Motor", (Jaguar) backRightMotor);
        
        // configures TankDrive
        drive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
        //drive.setSafetyEnabled(true);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
    
    public void tankDrive(Joystick joy) {
        drive.tankDrive(joy.getY(), joy.getRawAxis(4));
    }
	
    public void tankDrive(double leftValue, double rightValue) {
        drive.tankDrive(leftValue, rightValue);
    }
    
    public double[] getMotorValues() {
        double[] values = {frontLeftMotor.get(),backLeftMotor.get(),frontRightMotor.get(), backRightMotor.get()};
        return values; 
    }
    
}
