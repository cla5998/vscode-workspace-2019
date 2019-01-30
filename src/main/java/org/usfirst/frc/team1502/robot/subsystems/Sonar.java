/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Sonar extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public enum Distance {
    high, low;
  }; //these are the boundaries for the sonar to detect

  public enum Type {
    LinearSlide, PlatForm;
  }

  private static final double LINEAR_SLIDE_HIGH = 0;
  private static final double LINEAR_SLIDE_LOW = 0;
  private static final double PLATFORM_HIGH = 0;
  private static final double PLATFORM_LOW = 0;; // this is the way to differentiate between what the boundaries
                                                      // are for. might be unneeded, but its here

  AnalogInput analogSonar;

  double analogVolts; // raw output
  double cm; 

  public Sonar(AnalogInput analogSonar) {
    this.analogSonar = analogSonar;
  }

  public double getBoundary(Type type, Distance distance) {
    switch (type) {
      case LinearSlide:
      if (distance == Distance.high) return LINEAR_SLIDE_HIGH;
      if(distance == Distance.low) return LINEAR_SLIDE_LOW;
      case PlatForm:
      if (distance == Distance.high) return PLATFORM_HIGH;
      if (distance == Distance.low) return PLATFORM_LOW;
    default:
      return 0.0; // This won't happen
    }
  }

  public double readSensor() {
    analogVolts = analogSonar.getVoltage(); 
    cm = analogVolts / 2;
    //printRange();
    return cm;
  }

  public void printRange(){
    System.out.println(cm);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    readSensor();
  }
}
