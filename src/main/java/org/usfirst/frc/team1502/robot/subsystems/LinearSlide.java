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

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * Add your docs here.
 */
public class LinearSlide extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonSRX left;
  TalonSRX right;
  boolean toggled = true;
  double distance;
  public LoadType load = LoadType.Hatch;

  public static final double HATCH_GROUND = 0;
  public static final double HATCH_LOW = 0;
  public static final double HATCH_MIDDLE = 0;
  public static final double HATCH_HIGH = 0;
  public static final double CARGO_GROUND = 0;
  public static final double CARGO_LOW = 0;
  public static final double CARGO_MIDDLE = 0;
  public static final double CARGO_HIGH = 0;

  public enum Level {
    Ground, Low, Middle, High;
  };

  public enum LoadType {
    Hatch, Cargo
  };


  public LinearSlide(TalonSRX left, TalonSRX right) {
    this.left = left;
    this.right = right;
  }

  public void move(double input) {
    Robot.enc.setDistancePerPulse(1);
    while (Robot.enc.getDistance() < input) {
      left.set(ControlMode.PercentOutput, 1);
      right.set(ControlMode.PercentOutput, -1);
    }
    while (Robot.enc.getDistance() > input) {
      left.set(ControlMode.PercentOutput, -1);
      right.set(ControlMode.PercentOutput, 1);
    }
  }

  // public void getDistance(String level) {
  //   String[] places = {"ground", "low", "middle", "high"}; //the levels
  //   for (int i = 0; i < places.length; i++) { // runs levels
  //     if (level.equals(places[i]) && toggled) {  //checks to see if levels is equal and tggle is on
  //       double[] distance = { 0, 1, 2, 3 }; // placeholder distances because we dont know how to do that
  //       move(distance[i]); // returns the distance if it runs
  //     } else if (level.equals(places[i]) && !toggled) { //checks to see if level is equal and toggle is off
  //       double[] distance = { 0, 1, 2, 3 }; 
  //       move(distance[i]);
  //     }
  //   }
  // }

  public double getDistance(Level level, LoadType load) {
    switch (level) {
      case Ground:
        if (load == LoadType.Hatch) return HATCH_GROUND;
        if (load == LoadType.Cargo) return CARGO_GROUND;
      case Low:
        if (load == LoadType.Hatch) return HATCH_LOW;
        if (load == LoadType.Cargo) return CARGO_LOW;
      case Middle:
        if (load == LoadType.Hatch) return HATCH_MIDDLE;
        if (load == LoadType.Cargo) return CARGO_MIDDLE;
      case High:
        if (load == LoadType.Hatch) return HATCH_HIGH;
        if (load == LoadType.Cargo) return CARGO_HIGH;
      default:
        return 0.0; // This won't happen
    }
  }

  // public void toggleChange() {
  //   toggled = !toggled;
  // }

  public void toggleChange() {
    load = load == LoadType.Hatch ? LoadType.Cargo : LoadType.Hatch;
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}