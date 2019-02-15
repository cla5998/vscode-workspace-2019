/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.commands;

import org.usfirst.frc.team1502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class HorizontalSlideCommands extends Command {
  double speed = 0;
  public HorizontalSlideCommands(double speedInput) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.horizontalSlide);
    speed = speedInput;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.horizontalSlide.setSpeed(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() { // Limit switches output true by default and false when pressed in
    if (speed > 0 && Robot.horizontalLimitSwitchHigh.get()) return true;
    if (speed < 0 && Robot.horizontalLimitSwitchLow.get()) return true;
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
