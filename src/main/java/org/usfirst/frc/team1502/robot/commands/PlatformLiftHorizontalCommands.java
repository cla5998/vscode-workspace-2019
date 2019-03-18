/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.commands;

import org.usfirst.frc.team1502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PlatformLiftHorizontalCommands extends Command {
  Direction dir; //True = Up; False = Down
  public static final double BACK_SPEED = 0.2, FORWARD_SPEED = -0.2;
  static final int EXECUTE_CALLS_PER_SECOND = 50;
  static final double SECONDS_TO_FULL_POWER = 2;
  public enum Direction {
    BACK, FORWARD
  };

  double targetSpeed = 0;

  double currentSpeed = 0;

  public PlatformLiftHorizontalCommands(Direction direction) {
    this.dir = direction;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires (Robot.lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (dir == Direction.BACK) {
      targetSpeed = BACK_SPEED;
    } else {
      targetSpeed = FORWARD_SPEED;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // double speedIncrement = 1 / SECONDS_TO_FULL_POWER / EXECUTE_CALLS_PER_SECOND;
    // if (targetSpeed < currentSpeed) {
    //   currentSpeed -= Math.min(speedIncrement, currentSpeed - targetSpeed);
    // }
    // if (targetSpeed > currentSpeed) {
    //   currentSpeed += Math.min(speedIncrement, targetSpeed - currentSpeed);
    // }
    Robot.lift.movePlatformLiftHorizontal(targetSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.lift.movePlatformLiftHorizontal(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
