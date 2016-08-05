/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author robotics
 */
public class Drivetrain {
    private Jaguar[] wheels; //I'm storing the wheels in an array for easy access
    
    private static final byte LEFT=0; //The port of the first left wheel
    private static final byte RIGHT=2; //The port of the first right wheel
    
    //Specifies the default ports for the motors.  The first two are the left,
    //the second two are the right
    private static final int[] DEFAULT_PORTS = new int[]{1,2,3,4}; //changed from 5,6,3,4
    
    public Drivetrain(){
        wheels=new Jaguar[4]; //Initializes the array
        //For loop to set all the wheels to the specified default ports
        for(int i=0; i<wheels.length; i++){
            wheels[i]=new Jaguar(DEFAULT_PORTS[i]);
        }
    }
    public void update(double leftSpeed, double rightSpeed){
        //Updates the wheels' speed
        setWheel(LEFT,leftSpeed);
        setWheel(RIGHT,-rightSpeed);
    }
    public void setWheel(int wheel, double speed){
        //Sets the wheel in the specified place on the array to the speed,
        //and also the specified place plus one.
        wheels[wheel].set(deadband(speed));
        wheels[wheel+1].set(deadband(speed));
    }
    public double deadband(double x){
        //Adds a slight deadband to the controls
        if (x>0) return Math.max(0,x-0.05);
        else return Math.min(0, x+0.05);
    }
            
}
