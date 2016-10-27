/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team2374.robot.commands;

/**
 *
 * @author robotics
 */
public class CompressorOff extends CommandBase {
    
    public CompressorOff() {
        super("CompressorOff");
        requires(CommandBase.pneumatics);
        setTimeout(1);
    }

    protected void initialize() {
        CommandBase.pneumatics.stopCompressor();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
       return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
