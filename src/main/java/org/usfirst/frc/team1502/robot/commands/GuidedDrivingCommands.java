/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.commands;

import org.usfirst.frc.team1502.robot.Robot;

import java.util.ArrayList;
import java.util.Map;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1502.robot.GripPipeline;
import org.usfirst.frc.team1502.robot.PIDController;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

import org.usfirst.frc.team1502.robot.subsystems.Led.Color;
import org.usfirst.frc.team1502.robot.subsystems.LinearSlide.LoadType;
import org.usfirst.frc.team1502.robot.subsystems.Sonar.Boundaries;

public class GuidedDrivingCommands extends Command {

  Map<Boundaries, Double> place;
  PIDController distanceController;
  PIDController directionController;
  public static final int IMG_WIDTH = 160, IMG_HEIGHT = 120;
  UsbCamera camera;
  VisionThread visionThread;
  final Object imgLock = new Object(); // Synchronizes access to the data being simultaneously updated with each image
                                       // acquisition pass and the code that's processing the coordinates and steering
                                       // the robot.
  static final double IDLE_SPEED = 0.15;
  int targetCenterX, targetSize;
  boolean targetDetected = false, targetWasDetectedLastCycle = false;

  static final double TARGET_VALUE = 0.28; // measured in volts, represents ~1 foot away (sensor), robot will be
                                           // touching wall
  static final int STOPPING_TARGET_SIZE = 100;

  public GuidedDrivingCommands() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    requires(Robot.led);
    directionController = new PIDController(0.00479133, 1e-6, 0.5);
    camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
  }

  // Called just before this Command runs the first time
  @Override
  @SuppressWarnings("deprecation")
  protected void initialize() {
    camera.setExposureManual(20);
    visionThread = new VisionThread(camera, new GripPipeline(), (pipeline) -> {
      ArrayList<MatOfPoint> contours = pipeline.filterContoursOutput();
      targetWasDetectedLastCycle = targetDetected;
      SmartDashboard.putNumber("Contours detected", contours.size());
      if (contours.size() == 2) {
        targetDetected = true;
        Rect r1 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
        Rect r2 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));
        Rect leftTarget;
        Rect rightTarget;
        if (r1.x + (r1.width / 2) < r2.x + (r2.width / 2)) {
          leftTarget = r1;
          rightTarget = r2;
        } else {
          rightTarget = r1;
          leftTarget = r2;
        }
        synchronized (imgLock) {
          int leftTargetX = leftTarget.x + leftTarget.width / 2;
          int rightTargetX = rightTarget.x + rightTarget.width / 2;
          targetCenterX = (leftTargetX + rightTargetX) / 2;
          targetSize = rightTargetX - leftTargetX;
        }
      } else
        targetDetected = false;

      if (targetDetected && !targetWasDetectedLastCycle) {
        targetFound();
      }
      if (!targetDetected && targetWasDetectedLastCycle) {
        targetLost();
      }
    });
    visionThread.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if (Robot.sonar.isCloseToWall()) {
    // distanceController.input(Robot.sonar.readSensor() - TARGET_VALUE);
    // double motorSpeed = distanceController.getCorrection();
    // }
    //.putNumber("Sonar volts", Robot.sonar.readSensor());
    SmartDashboard.putBoolean("Target detected", targetDetected);
    SmartDashboard.putNumber("Target size", targetSize);
    /*double d = map(Robot.m_oi.rightJoystick.getThrottle(), 1, -1, 1e+1, 1e-1);
    SmartDashboard.putNumber("D", d);
    directionController.D = d;*/
    if (targetDetected && targetSize < STOPPING_TARGET_SIZE) {
      synchronized (imgLock) {
        double error = targetCenterX - (IMG_WIDTH / 2); // px
        SmartDashboard.putNumber("Error", error);
        directionController.input(error);
        double correction = directionController.getCorrection();
        SmartDashboard.putNumber("Correction", correction);
        Robot.drivetrain.arcadeDrive(IDLE_SPEED * (1 - Robot.m_oi.rightJoystick.getY()), correction);
      }
    } else targetLost();

    if (targetSize >= STOPPING_TARGET_SIZE) {
      Robot.led.set(Color.Green);
    } else {
      Robot.led.set(Color.Yellow);
    }
    // Robot.sonar.check(place);
    // double cm = Robot.sonar.readSensor(); // gets distance from object
    // while(cm < Robot.sonar.getBound(place, Boundaries.low)) { // gets what
    // distance and bound were looking for
    // SmartDashboard.putBoolean("close", true); //prints it out to smartDashboard
    // }
    // while(cm > Robot.sonar.getBound(place, Boundaries.high)) {
    // SmartDashboard.putBoolean("far", true);
    // }
  }

  double map(double x, double inMin, double inMax, double outMin, double outMax) {
    return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
  }

  void targetFound() {
    System.out.println("Target found");
  }

  void targetLost() {
    System.out.println("Target lost");
    directionController.reset();
    Robot.drivetrain.arcadeDrive(0, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  void commandFinished() {
    System.out.println("Guided driving commands finished");
    
    if (Robot.slide.load == LoadType.Hatch) {
			Robot.led.set(Color.Blue);
		} else {
			Robot.led.set(Color.Orange);
    }
    
    visionThread.interrupt();
    targetLost();
    camera.setExposureAuto();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    commandFinished();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    commandFinished();
  }
}
