/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class LinearSlide extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static int distance = 0;

  TalonSRX left;
  TalonSRX right;
  boolean toggled = true;

  public LinearSlide(TalonSRX left, TalonSRX right){
    this.left = left;
    this.right = right;
  }

  public void move(double input){
    if(distance < input){
      for(this.distance; this.distance < input; distance++) {

      }
    }
    else if(this.distance > input){
      for(this.distance; this.distance > input; distance--) {

      }
    }
    this.distance = input;
  }

  public void toggleChange(){
    toggled = !toggled;
  }
  public boolean getToggle(){
    return this.toggled;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
