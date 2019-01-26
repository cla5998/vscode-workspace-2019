/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.commands;

import org.usfirst.frc.team1502.robot.Robot;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SonarCommands extends Command {
  double smallBoundary;
  double largeBoundary;
  public SonarCommands(double boundS, double boundL) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.smallBoundary = boundS;
    this.largeBoundary = boundL;

    requires(Robot.sonar);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double cm = Robot.sonar.readSensor();
    while(cm < smallBoundary) {
      SmartDashboard.putBoolean("close", true);
    }
    while(cm > largeBoundary) {
      SmartDashboard.putBoolean("far", true);
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
