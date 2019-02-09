/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import java.util.EnumMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.usfirst.frc.team1502.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class LinearSlide extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonSRX left;
  TalonSRX right;
  public LoadType load = LoadType.Hatch; // Default linear slide position is hatch, because of this

  public static final double GROUND = 0; // Constants for getDistance. These are how far it moves
  public static final double HATCH_LOW = 0;
  public static final double HATCH_MIDDLE = 0;
  public static final double HATCH_HIGH = 0;
  public static final double CARGO_LOW = 0;
  public static final double CARGO_MIDDLE = 0;
  public static final double CARGO_HIGH = 0;

  public enum Level {
    Ground, Low, Middle, High
  };

  public enum LoadType {
    Hatch, Cargo
  };

  public LinearSlide(TalonSRX left, TalonSRX right) {
    this.left = left;
    this.right = right;
  }

  public void move(double input) {
    Robot.enc.setDistancePerPulse(1); // this needs to be tested, but obviously cant
    while (Robot.enc.getDistance() < input) {
      left.set(ControlMode.PercentOutput, 1); // these two moves could be wrong, will follow up with keppler to get the
                                              // answer soon
      right.set(ControlMode.PercentOutput, -1);
    }
    while (Robot.enc.getDistance() > input) {
      left.set(ControlMode.PercentOutput, -1);
      right.set(ControlMode.PercentOutput, 1);
    }
  }
  
  public double getDistance(Level level, LoadType load) {
    if (level == Level.Ground) {
      return GROUND;
    }
    switch (level) {
    case Low:
      if (load == LoadType.Hatch)
        return HATCH_LOW;
      if (load == LoadType.Cargo)
        return CARGO_LOW;
    case Middle:
      if (load == LoadType.Hatch)
        return HATCH_MIDDLE;
      if (load == LoadType.Cargo)
        return CARGO_MIDDLE;
    case High:
      if (load == LoadType.Hatch)
        return HATCH_HIGH;
      if (load == LoadType.Cargo)
        return CARGO_HIGH;
    default:
      return 0.0; // Needed because there are return statements inside the cases. wont happen
    }
  } 

  public void toggleChange() {
    load = load == LoadType.Hatch ? LoadType.Cargo : LoadType.Hatch;
    // load2 = load2 == Hatch ? Cargo : Hatch;
  } //if load type is hatch, then its cargo, else its changes to cargo
  
  // public static Map<Level, Double> Cargo = new EnumMap<Level, Double>(Level.class) {
  //   { // the "error" is saying that these arent set as a constant, if you wanted to
  //     // make a serial number, then if its changed so is the map value.
  //     put(Level.Ground, 0.0); // goes without saying that you cant change these numbers anywhere but in this
  //     put(Level.Low, 1.0);
  //     put(Level.Middle, 2.0);
  //     put(Level.High, 3.0);
  //   }
  // };
  // public static Map<Level, Double> Hatch = new EnumMap<Level, Double>(Level.class) {
  //   {
      // put(Level.Ground, 0.0);
      // put(Level.Low, 1.0);
      // put(Level.Middle, 2.0);
      // put(Level.High, 3.0);
  //   }
  // };
  // Map<Level, Double> load2 = Cargo;

  // public double getDistance2(Map<Level, Double> load, Level place) { // this is just me messing around, it works but not
  //                                                                    // to be used.
  //   return (double) load.get(place);
  // }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}