/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.commands;

import org.usfirst.frc.team1502.robot.Robot;
import org.usfirst.frc.team1502.robot.subsystems.LinearSlide;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LinearSlideCommands extends Command {

  // String level;
  LinearSlide.Level level;
  //LinearSlide.Level level
  public LinearSlideCommands() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //this.level = level;
    requires(Robot.slide);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Robot.slide.move(Robot.slide.getDistance(level, Robot.slide.load));
    Robot.slide.move();
    SmartDashboard.putNumber("Enc value", Robot.enc.get());
    //Robot.slide.move(Robot.slide.getDistance2(level, Robot.slide.load));
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