/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.usfirst.frc.team1502.robot.Robot;
import org.usfirst.frc.team1502.robot.subsystems.Led.Color;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class LinearSlide extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public TalonSRX left; // Left Slide Motor
  public TalonSRX right; // Right Slide Motor
  public double startPos; // Encoder Start Position when robot is enabled
  public boolean centered = false; // Is the slide at the target?
  public LoadType load = LoadType.Hatch; // Default linear slide position is hatch;
  public static final int LOW_HOLD_THRESHOLD = 18000;
  public static final int HIGH_HOLD_THRESHOLD = 21000;

  public boolean switched = false;

  public static final double GROUND = 0; // Constants for getDistance. These are how far it moves
  public static final double HATCH_LOW = 6713;
  public static final double HATCH_MIDDLE = 15421;
  public static final double HATCH_HIGH = 23702;
  public static final double CARGO_LOW = 10051;
  public static final double CARGO_MIDDLE = 18898;
  public static final double CARGO_HIGH = 27826;
  public static final double CARGO_SHIP_PORT = 15000;
  public static final double INTAKE_PUSH = 2440;

  public static enum Level {
    Ground, Low, Middle, High, Ship, IntakePush
  };

  public enum LoadType {
    Hatch, Cargo
  };

  public LinearSlide(TalonSRX left, TalonSRX right) {
    this.left = left;
    this.right = right;
  }

  /**
   * Moves the linear slide by {@link input}, a number between -1 and 1.
   * @param input A number between -1 and 1
   */
  public void moveBy(double input) {
    if (input > 0) { // up
      left.set(ControlMode.PercentOutput, -.50 * input);
      right.set(ControlMode.PercentOutput, .50 * input);
    } else { // down
      left.set(ControlMode.PercentOutput, -.15 * input);
      right.set(ControlMode.PercentOutput, .15 * input);
    }
    SmartDashboard.putNumber("Enc value", left.getSelectedSensorPosition());
  }

  public void moveTo(Level level) {
    moveTo(getDistance(level, load));
  }

  public void moveTo(double input) {
    moveTo(input, 0.5);
  }

  public void moveTo(double input, double upSpeed) {
    double target = startPos + input;
    SmartDashboard.putNumber("LinearSlide left sensor position", left.getSelectedSensorPosition());
    SmartDashboard.putNumber("LinearSlide target", target);
    if (left.getSelectedSensorPosition() < target - 40) { // Normal Movement
      left.set(ControlMode.PercentOutput, -upSpeed);
      right.set(ControlMode.PercentOutput, upSpeed);
      centered = false;
    } else if (left.getSelectedSensorPosition() > target + 40) {
      left.set(ControlMode.PercentOutput, .15);
      right.set(ControlMode.PercentOutput, -.15);
      centered = false;
    } else if (left.getSelectedSensorPosition() < target - 300) { // Deceleration Zone
      left.set(ControlMode.PercentOutput, -(upSpeed - .15));
      right.set(ControlMode.PercentOutput, (upSpeed - .15));
      centered = false;
    } else if (left.getSelectedSensorPosition() > target + 300) {
      left.set(ControlMode.PercentOutput, .05);
      right.set(ControlMode.PercentOutput, -.05);
      centered = false;
    } else {
      hold();
      centered = true;
    }
    SmartDashboard.putNumber("Enc value", left.getSelectedSensorPosition());
  }

  public void hold() {
    if (left.getSelectedSensorPosition() < LOW_HOLD_THRESHOLD) {
      left.set(ControlMode.PercentOutput, -0.1);
      right.set(ControlMode.PercentOutput, 0.1);
    } else if (left.getSelectedSensorPosition() < HIGH_HOLD_THRESHOLD) {
      left.set(ControlMode.PercentOutput, -0.21);
      right.set(ControlMode.PercentOutput, 0.21);
    } else {
      left.set(ControlMode.PercentOutput, -0.28);
      right.set(ControlMode.PercentOutput, 0.28);
    }
  }

  public double getDistance(Level level) {
    return getDistance(level, load);
  }

  public double getDistance(Level level, LoadType load) {
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
    case Ship:
      return CARGO_SHIP_PORT;
    case Ground:
      return GROUND;
    case IntakePush:
      return INTAKE_PUSH;
    default:
      return 0.0;
    }
  }

  public void toggleChange() {
    if (load == LoadType.Hatch) {
      load = LoadType.Cargo;
      System.out.println("Cargo");
      Robot.led.set(Color.Orange);
    } else {
      load = LoadType.Hatch;
      System.out.println("Hatch");
      Robot.led.set(Color.Blue);
    }
    switched = true;
    // load2 = load2 == Hatch ? Cargo : Hatch;
  } // if load type is hatch, then its cargo, else its changes to cargo

  // public static Map<Level, Double> Cargo = new EnumMap<Level,
  // Double>(Level.class) {
  // { // the "error" is saying that these arent set as a constant, if you wanted
  // to
  // // make a serial number, then if its changed so is the map value.
  // put(Level.Ground, 0.0); // goes without saying that you cant change these
  // numbers anywhere but in this
  // put(Level.Low, 1.0);
  // put(Level.Middle, 2.0);
  // put(Level.High, 3.0);
  // }
  // };
  // public static Map<Level, Double> Hatch = new EnumMap<Level,
  // Double>(Level.class) {
  // {
  // put(Level.Ground, 0.0);
  // put(Level.Low, 1.0);
  // put(Level.Middle, 2.0);
  // put(Level.High, 3.0);
  // }
  // };
  // Map<Level, Double> load2 = Cargo;

  // public double getDistance2(Map<Level, Double> load, Level place) { // this is
  // just me messing around, it works but not
  // // to be used.
  // return (double) load.get(place);
  // }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}