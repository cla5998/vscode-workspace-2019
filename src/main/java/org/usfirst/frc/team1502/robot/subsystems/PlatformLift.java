/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team1502.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class PlatformLift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Spark vertical;
  Spark horizontal;
  DigitalInput limitSwitchHigh;
  DigitalInput limitSwitchLow;

  public PlatformLift(Spark vertical, Spark horizontal, int LIMIT_HIGH, int LIMIT_LOW) {
    limitSwitchHigh = new DigitalInput(LIMIT_HIGH);
    limitSwitchLow = new DigitalInput(LIMIT_LOW);
    this.vertical = vertical;
    this.horizontal = horizontal;
  }

  public boolean check() {
    return limitSwitchHigh.get();
  }

  public void movePlatformLiftVerticle(double speed) {
    if (check()) setVerticalSpeed(speed);
    else setVerticalSpeed(0);
  }

  public void movePlatformLiftHorizontal(double speed) {
    if (check()) setHorizontalSpeed(speed);
    else setHorizontalSpeed(0);
  }

  public void setVerticalSpeed(double speed) {
    vertical.set(speed);
  }

  public void setHorizontalSpeed(double speed) {
    horizontal.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
