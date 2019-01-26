/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	
	//Talons
	public static final int DRIVETRAIN_LEFT_FRONT_TALON = 1;
	public static final int DRIVETRAIN_LEFT_BACK_TALON = 3;
	public static final int DRIVETRAIN_RIGHT_FRONT_TALON = 2;
	public static final int DRIVETRAIN_RIGHT_BACK_TALON = 4;

	public static final int VACUUM_TALON = 5;
	
	public static final int PLATFORM_TALON_LEFT = 6;
	public static final int PLATFORM_TALON_RIGHT = 7;
	
	public static final int LINEAR_SLIDE_TALON_LEFT = 8;
	public static final int LINEAR_SLIDE_TALON_RIGHT = 9;

	//Joysticks
	public static final int LEFT_JOYSTICK = 0;
	public static final int RIGHT_JOYSTICK = 1;
	public static final int MANIP_JOYSTICK = 2;
	
	//Solenoids
	public static final Solenoid SOLENOID_1 = new Solenoid(11, 1);
	public static final Solenoid SOLENOID_2 = new Solenoid(11, 2);
	public static final Solenoid SOLENOID_3 = new Solenoid(11, 3);

	// public static final int ARM_SOLENOID_OPEN = 0;
	// public static final int ARM_SOLENOID_CLOSE = 1;

	//PWM
	public static final Spark INTAKE_SPARK = new Spark(0);
	public static final Spark RACK_SPARK = new Spark(1);

	public static final Spark VACUUM_SPARK1 = new Spark(4);
	public static final Spark VACUUM_SPARK2 = new Spark(5);

	//this isnt real im just being stupid
	public static final AnalogInput SONAR_SPARK = new AnalogInput(3);

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
