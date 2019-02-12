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
 * youre gonna have a good time
 */
public class Led extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Spark LEDSpark;

  public enum Color {
    Red(.99), Green(.77), Blue(.87), Yellow(.69), Magenta(0.57), White(0.93), Off(0.99), Strobe(.15);
    private double value;

    Color(double value) {
      this.value = value;
    }

    public double get() {
      return value;
    }
  };

  Color current = Color.Red;

  // Map<Color, Double> ledMap = new EnumMap<Color, Double> (Color.class){{
  // put(Color.Red, .61);
  // put(Color.Green, .77);
  // }};

  public Led(Spark LEDSpark) {
    this.LEDSpark = LEDSpark;
  }

  public void set(Color color) {
    LEDSpark.set(color.get());
  }
  
  public void set(double color) {
    LEDSpark.set(color);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}