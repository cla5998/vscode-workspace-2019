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
public class Vacuum extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  Spark vacuumLeft = null;
  Spark vacuumRight = null;
  double speed = 0;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public Vacuum(Spark vacuumLeft, Spark vacuumRight) { 
    this.vacuumLeft = vacuumLeft;
    this.vacuumRight = vacuumRight;
  }
    
  public void setSpeed(double speed) {
     this.speed = speed;
     vacuumLeft.set(speed);
     vacuumRight.set(speed);
  }

}
