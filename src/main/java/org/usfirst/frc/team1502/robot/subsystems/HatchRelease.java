/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class HatchRelease extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Solenoid solenoid1 = null;
  Solenoid solenoid2 = null;
  Solenoid solenoid3 = null;

  public HatchRelease(Solenoid one, Solenoid two,  Solenoid three) {
    solenoid1 = one;
    solenoid2 = two;
    solenoid3 = three;
  }

  public void push() {
    solenoid1.set(true);
    solenoid2.set(true);
    solenoid3.set(true);
  }

  public void pull() {
    solenoid1.set(false);
    solenoid2.set(false);
    solenoid3.set(false);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());


  }
}
