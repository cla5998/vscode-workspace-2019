*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import java.util.EnumMap;
import java.util.Map;

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
    Red, Green, Fire
  };
  Color current = Color.Red;

  Map<Color, Double> ledMap = new EnumMap<Color, Double> (Color.class){{
    put(Color.Red, .61);
    put(Color.Green, .77);
    put(Color.Fire, -.39);
  }};

  public Led(Spark LEDSpark) {
    this.LEDSpark = LEDSpark;
  }

  public void setColor(Color target) {
    LEDSpark.set(ledMap.get(target));
  }

  public void colorChange() {
    current = current == Color.Red ? Color.Green : Color.Fire;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}