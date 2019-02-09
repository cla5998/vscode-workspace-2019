/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import org.usfirst.frc.team1502.robot.Robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Sonar extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  boolean enabled = false;

  public enum Boundaries {
    high, low;
  }; // these are the boundaries for the sonar to detect

  AnalogInput analogSonar;
  AnalogInput analogSonar1;
  AnalogInput analogSonar2;

  double analogVolts; // raw output
  double cm;

  public Sonar(AnalogInput analogSonar) {
    this.analogSonar = analogSonar;
  }

  public boolean isCloseToWall() {
    double place = readSensor();
    return enabled && place < .5;
    // returns true if the stop is on, and it needs to stop
  }

  public boolean isOutBound() {
    double place = readSensor();
    return place < .5 || place > 1.5;
  }

  public void ledOn() {
    if (isOutBound()) {
      Robot.led.setRed();
    }
    else { 
      Robot.led.setGreen();
    }
  }

  //checks both sonar distances.
  //if one doesnt equal the other, then it takes the smaller one and makes that its bound
  //moves longer one to the smaller bound by shutting off the smaller ones wheels.

  public void softStopToggle() {
    enabled = !enabled;
  }

  // public double getBoundary(Type type, Distance distance) {
  // switch (type) {
  // case LinearSlide:
  // if (distance == Distance.high) return LINEAR_SLIDE_HIGH;
  // if(distance == Distance.low) return LINEAR_SLIDE_LOW;
  // case PlatForm:
  // if (distance == Distance.high) return PLATFORM_HIGH;
  // if (distance == Distance.low) return PLATFORM_LOW;
  // default:
  // return 0.0; // This won't happen
  // }
  // }

  public double readSensor() {
    return analogSonar.getVoltage();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}