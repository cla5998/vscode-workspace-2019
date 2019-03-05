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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class LinearSlide extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public TalonSRX left; //Left Slide Motor
  public TalonSRX right; //Right Slide Motor
  public double startPos; //Encoder Start Position
  public boolean centered = false; //Is the slide at the target?
  public LoadType load = LoadType.Hatch; // Default linear slide position is hatch;
  public int holdThreshold = 18000;
  public int highHoldThreshold = 21000;

  public boolean switched = false;

  public static final double GROUND = 0; // Constants for getDistance. These are how far it moves
  public static final double HATCH_LOW = 6713;
  public static final double HATCH_MIDDLE = 15421;
  public static final double HATCH_HIGH = 23702;
  public static final double CARGO_LOW = 9951;
  public static final double CARGO_MIDDLE = 17498;
  public static final double CARGO_HIGH = 23426;

  public static enum Level {
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
    double target = startPos + input;
    if (left.getSelectedSensorPosition() < target - 30) {
      left.set(ControlMode.PercentOutput, -.45);
      right.set(ControlMode.PercentOutput, .45);
      centered = false;
    }
    else if (left.getSelectedSensorPosition() > target + 30) {
      left.set(ControlMode.PercentOutput, .1);
      right.set(ControlMode.PercentOutput, -.1);
      centered = false;
    } else {
      hold();
      centered = true;
    }
    SmartDashboard.putNumber("Enc value", left.getSelectedSensorPosition());
    System.out.println(startPos);
    System.out.println(target);
  }

  
  public void move() {
    double speed = Robot.m_oi.leftJoystick.getY();
    if (speed >= .08 || speed <= .08) {
      left.set(ControlMode.PercentOutput, speed);
      right.set(ControlMode.PercentOutput, -speed);
    } else {
      hold();
    }
    SmartDashboard.putNumber("Enc value", left.getSelectedSensorPosition());
    System.out.println(left.getSelectedSensorPosition());
  }

  public void hold() {
    if (left.getSelectedSensorPosition() < holdThreshold) {
      left.set(ControlMode.PercentOutput, -0.19);
      right.set(ControlMode.PercentOutput, 0.19);
    } else if (left.getSelectedSensorPosition() < highHoldThreshold) {
      left.set(ControlMode.PercentOutput, -0.28);
      right.set(ControlMode.PercentOutput, 0.28);
    } else {
      left.set(ControlMode.PercentOutput, -0.33);
      right.set(ControlMode.PercentOutput, 0.33);
    }
  }

  public double getDistance(Level level) {
    return getDistance(level, load);
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
      return 0.0;
    }
  }

  public void toggleChange() {
    // if (load == LoadType.Hatch) {
    //   load = LoadType.Cargo;
    //   System.out.println("Cargo");
    //   switched = true;
    // } else {
    //   load = LoadType.Hatch;
    //   System.out.println("Hatch");
    //   switched = true;
    // }
    load = load == LoadType.Hatch ? LoadType.Cargo : LoadType.Hatch;
    System.out.println(load);
    switched = true;
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