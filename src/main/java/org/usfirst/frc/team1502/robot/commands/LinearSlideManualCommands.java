/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.commands;

import org.usfirst.frc.team1502.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LinearSlideManualCommands extends Command {
  public LinearSlideManualCommands() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.slide);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double manipRT = Robot.m_oi.manipJoystick.getTriggerAxis(Hand.kRight);
    double manipLT = Robot.m_oi.manipJoystick.getTriggerAxis(Hand.kLeft);
    SmartDashboard.putNumber("manipLT", manipLT);
    SmartDashboard.putNumber("manipRT", manipRT);
    if (manipRT > .1) {
			Robot.slide.moveBy(manipRT);
		} else if (manipLT > .1) {
			Robot.slide.moveBy(-manipLT);
		} else {
			Robot.slide.hold();
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
    Robot.slide.hold();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
