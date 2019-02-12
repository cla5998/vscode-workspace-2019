/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.commands;

import org.usfirst.frc.team1502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HorizontalSlideCommands extends Command {
  
  boolean forward;
  double speed = .7;
  int i = 0;
  int tickRate = 0;
  public HorizontalSlideCommands(boolean forward) {
    this.forward = forward;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.horizontalSlide);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    tickRate++;
    SmartDashboard.putNumber("Tickrate", tickRate);
    SmartDashboard.putNumber("Speed", speed);
    if(i == 5 && speed > .4) {
       speed -= .1;
      i = 0;
    }
    else{
      i++;
    }
    if(forward == true) {
      Robot.horizontalSlide.setSpeed(speed);
    }
    else{
      Robot.horizontalSlide.setSpeed(-speed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.horizontalSlide.setSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
