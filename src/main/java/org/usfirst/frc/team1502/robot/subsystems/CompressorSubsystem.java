/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import org.usfirst.frc.team1502.robot.Robot;

import edu.wpi.first.wpilibj.Compressor;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class CompressorSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public int id;
  public Compressor compressor;
  public boolean on = true;

  public CompressorSubsystem(int id) {
    compressor = new Compressor(id);
  }

  public void run() {
    compressor.start();
    Robot.solenoid.close();
    Robot.r.setLeft(1);
  }

  public void stop() {
    compressor.stop();
    Robot.solenoid.open();
    Robot.r.setLeft(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
