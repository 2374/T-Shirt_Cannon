/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2374.robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2374.robot.commands.CommandBase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
    //Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // Initialize all subsystems
        CommandBase.init();
        SmartDashboard.putData(Scheduler.getInstance());
    }

    public void autonomousInit() {
        //autonomousCommand.start(); // schedule the autonomous command (example)
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        double[] drivetrainValues = CommandBase.drivetrain.getMotorValues();
        SmartDashboard.putNumber("FrontLeftMotor", drivetrainValues[0]);
        SmartDashboard.putNumber("BackLeftMotor", drivetrainValues[1]);
        SmartDashboard.putNumber("FrontRighttMotor", drivetrainValues[2]);
        SmartDashboard.putNumber("BackRightMotor", drivetrainValues[3]);
        SmartDashboard.putNumber("TurretMotor", CommandBase.turret.getMotorValue());
        SmartDashboard.putString("Fire Valve State", CommandBase.turret.getFireValveString());
        SmartDashboard.putBoolean("Compressor State", CommandBase.pneumatics.isEnabled());
        SmartDashboard.putBoolean("Max Pressure", CommandBase.pneumatics.isPressurized());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}