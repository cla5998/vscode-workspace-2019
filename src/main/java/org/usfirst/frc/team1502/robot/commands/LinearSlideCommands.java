/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.commands;

import org.usfirst.frc.team1502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LinearSlideCommands extends Command {

  String level;
  boolean toggle = true;
  int set = 0;
  public LinearSlideCommands(String place) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.level = place;
    requires(Robot.slide);
  }

  public LinearSlideCommands(int set){
    this.set = set;
  }
  
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(set == 0){
      String [] places = {"ground", "low", "middle", "high"};
      for (int i = 0; i < places.length; i++) {
        if (this.level.equals(places[i]) && toggle == true) {
          double [] distance = {0, 1, 2, 3}; //placeholder distances because we dont know how to do that
          Robot.slide.move(distance[i]);
        }
        else if (this.level.equals(places[i]) && toggle == false) {
          double [] distance = {0, 1, 2, 3};
          Robot.slide.move(distance[i]);
        }
      }
    }
    else if(set == 1){
      toggle = !toggle;
      set = 0;
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
