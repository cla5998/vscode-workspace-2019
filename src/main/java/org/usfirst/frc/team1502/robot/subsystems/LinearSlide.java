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

  public LinearSlide(TalonSRX left, TalonSRX right) {
    this.left = left;
    this.right = right;
  }

  public void move(double input) {
    Robot.enc.setDistancePerPulse(1);
    if (Robot.enc.getDistance() < input) {
      while (Robot.enc.getDistance() < input) {
        left.set(ControlMode.PercentOutput, 1);
        right.set(ControlMode.PercentOutput, 1);
      }
    }
    else if (Robot.enc.getDistance() > input) {
      while (Robot.enc.getDistance() > input) {
        left.set(ControlMode.PercentOutput, -1);
        right.set(ControlMode.PercentOutput, -1);
      }
    }
  }

  public void toggleChange() {
    toggled = !toggled;
  }
  public boolean getToggle() {
    return this.toggled;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}