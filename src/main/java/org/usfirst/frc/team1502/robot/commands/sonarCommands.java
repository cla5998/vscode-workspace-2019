/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.commands;

import org.usfirst.frc.team1502.robot.Robot;
import org.usfirst.frc.team1502.robot.subsystems.Sonar;
import org.usfirst.frc.team1502.robot.subsystems.Sonar.Distance;
import org.usfirst.frc.team1502.robot.subsystems.Sonar.Type;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class sonarCommands extends Command {

  Type place;

  public sonarCommands(Type place) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.place = place;

    requires(Robot.sonar);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double cm = Robot.sonar.readSensor(); // gets distance from object
    while(cm < Robot.sonar.getBoundary(place, Distance.low)) { // gets what distance and bound were looking for
      SmartDashboard.putBoolean("close", true); //prints it out to smartDashboard
    }
    while(cm > Robot.sonar.getBoundary(place, Distance.high)) {
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
