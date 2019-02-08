/*----------------------------------------------------------------------------*/
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
 * Add your docs here.
 */
public class Led extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  Spark LEDSpark;

  public enum Color {
    Red, Green, Blue, Strobe
  };

  Color target = Color.Red;

  Map<Color, Double> ledMap = new EnumMap<Color, Double> (Color.class){{
    put(Color.Red, .61);
    put(Color.Green, .77);
    put(Color.Blue, .87);
    put(Color.Strobe, .35);
  }};

  public Led(Spark LEDSpark) {
    this.LEDSpark = LEDSpark;
  }

  public void setColor() {
    LEDSpark.set(ledMap.get(target));
  }

  public void colorChange() {
    if (target == Color.Red){
      target = Color.Green;
    }
    else if (target == Color.Green) {
      target = Color.Blue;
    }

    else if (target == Color.Blue) {
      target = Color.Strobe;
    }
    else if (target == Color.Strobe) {
      target = Color.Red;
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
