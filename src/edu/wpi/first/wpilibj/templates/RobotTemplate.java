/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {

    private Drivetrain drivetrain;
    private Turret turret;
    private Joystick driveStick;
    private Compressor compressor;
    private DriverStationLCD lcd;
    boolean sentient = false;

    public RobotTemplate() {

        drivetrain = new Drivetrain();
        turret = new Turret(5, 2); //changed form 1,1  motor port relay port

        driveStick = new Joystick(1);
        
        compressor = new Compressor(2, 1);// pressure switch, relay input
        lcd = DriverStationLCD.getInstance();
    }

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        lcd.println(DriverStationLCD.Line.kUser2, 1, "You just lost the game");
        lcd.updateLCD();
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        compressor.start(); //Starts the air compressor
        while (isOperatorControl()&&!sentient) {
            turret.update(driveStick.getRawAxis(6)); //Sets the turret's motor speed
            //to the turret joystick's y-value
            drivetrain.update(driveStick.getY(), driveStick.getRawAxis(4));
            //Sets the drivetrain's motor speeds to the left and right thumbpads
            //on the game pad
            if (DriverStation.getInstance().getDigitalIn(3)){ //If the arming switch is down
                lcd.println(DriverStationLCD.Line.kUser3,1, "ARMED"); //Indicate to the user the turret is armed
                if (DriverStation.getInstance().getDigitalIn(6)) {
                    turret.charge(true); //Charge the turret if the red button's pressed
                    lcd.println(DriverStationLCD.Line.kUser4, 1, "FIRING"); //Indicate to the user the turret is charging
                } else {
                    lcd.println(DriverStationLCD.Line.kUser4, 1, "      "); //Clears 'FIRING' on the display
                    turret.charge(false); //Stops charging the turret
                }
                //This code displays the cool little bar on the GUI, don't worry about it
                for(int i=0; i<20; i++){
                    i++;
                    if(i<=turret.charge){
                        lcd.println(DriverStationLCD.Line.kUser5, i, ".");
                    }
                    else lcd.println(DriverStationLCD.Line.kUser5, i, " ");
                }
            
            }
            else {
                lcd.println(DriverStationLCD.Line.kUser3, 1, "       "); //Clears 'ARMED' and 'FIRING' off of the display
                lcd.println(DriverStationLCD.Line.kUser4, 1, "       ");
                turret.charge(false); //Stops charging the turret
            }
            if (compressor.getPressureSwitchValue()) { //Whether or not the pneumatic system has reached the limit of 120 psi
                    lcd.println(DriverStationLCD.Line.kUser6, 1, "CHARGED.   "); //Indicates the status to the user
                } else {
                    lcd.println(DriverStationLCD.Line.kUser6, 1, "Charging...");
                }
            lcd.println(DriverStationLCD.Line.kUser2,1," T-ShirtCannon2.SWAG "); // T-ShirtCannon2  !!!!!!!
            lcd.updateLCD(); //Updates the LCD to display all this cool text
            
            getWatchdog().feed(); //Feeds the watchdog
        }
    }
}
