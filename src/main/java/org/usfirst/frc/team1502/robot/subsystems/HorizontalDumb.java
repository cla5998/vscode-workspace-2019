/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class HorizontalDumb extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Spark dumbThing = null;

  public HorizontalDumb(Spark dumb){
    dumbThing = dumb;
  }

  public void setSpeed(double speedInput) { 
    double speed = speedInput;
    dumbThing.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
