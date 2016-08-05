/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

/**
 *
 * @author robotics
 */
public class Turret {

    Jaguar pitch; //The motor that controls the turret's pitch
    Relay trigger; //The relay that controls the solenoid
    public int charge; //More on this later
    public boolean working; //More on this later
    public boolean firing; //Whether or not the robot is firing
    boolean charging; //More on this later

    public Turret(int motorPort, int relayPort) {
        pitch = new Jaguar(motorPort); //Assigns the pitch jaguar to the correct port; 
        trigger = new Relay(relayPort); //Assigns the trigger relay to the correct port
        charge = 0; //Resets the robot's 'charge'
    }

    public void update(double pitchSpeed) {
        pitch.set(deadband(pitchSpeed) * -0.2); //Sets the pitch motor to the specified speed
    }

    public void charge(boolean Charging) {
        charging=Charging; //So I can access it in the runnable
        if (!working) { //Working keeps the robot from running this twice at the same time
            new Runnable() { //So I can use Thread.sleep

                public void run() {
                    working = true; //Makes it so I don't run this again at the same time
                    if (charging && !firing) {
                        charge++; //Increases the charge variable
                        if (charge > 20) { //if the robot's been charging for .2 seconds 
                            charge = 100; //Set the charge to about 1 second
                            fire(); //Fires the cannon
                        }
                    } else {
                        charge--; //Decreases the charge variable
                        if (charge < 0) { //If it's less than 0
                            stopFiring(); //Stop firing
                            charge=0; //Make sure it doesn't go below 0
                        }
                    }
                    try {
                        Thread.sleep(10); //Wait 10 ms
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }.run();
            working = false; //I'm all done now, feel free to do it again
        }
    }

    public void fire() {
        if (!firing) { //If it's already firing, why change anything?
            firing = true; //Indicates the robot's firing
            trigger.set(Value.kForward); //Sets the relay to its 'on' position
        }
    }

    public void stopFiring() {
        if (firing) { //If it isn't firing, don't bother turning it off again
            firing = false; //Indicates the robot isn't firing
            trigger.set(Value.kOff); //Sets the relay to its 'off' position
        }
    }

    public double deadband(double x) {
        //Adds a deadzone to the controller, i.e. how far you can move the joystick
        //without it recognizing the signal
        if (x > 0) {
            return Math.max(0, x - 0.05);
        } else {
            return Math.min(0, x + 0.05);
        }
    }
}
