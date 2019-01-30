/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import java.util.HashMap;
import java.util.Map;

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
  }; //this is the way to differentiate between what the boundaries are for. might be unneeded, but its here

  AnalogInput analogSonar;

  double analogVolts; // raw output
  double cm; 

  public Sonar(AnalogInput analogSonar) {
    this.analogSonar = analogSonar;
  }

  Map<Distance, Double> SonarDistance = new HashMap<Distance, Double>();

  public double getBoundary(Type value, Distance boundary) {
    switch(value) {
      case LinearSlide:
        SonarDistance.put(Distance.high, 0.0);
        SonarDistance.put(Distance.low, 0.0);
      case PlatForm:
        SonarDistance.put(Distance.high, 0.0);
        SonarDistance.put(Distance.low, 0.0);
    }
    return (double) SonarDistance.get(boundary);
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
