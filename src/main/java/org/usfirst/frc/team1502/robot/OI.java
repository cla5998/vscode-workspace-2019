/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot;

import org.usfirst.frc.team1502.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	/**
	 * 
	 */

	public Joystick leftJoystick = new Joystick(RobotMap.LEFT_JOYSTICK);
	public Joystick rightJoystick = new Joystick(RobotMap.RIGHT_JOYSTICK);
	public XboxController manipJoystick = new XboxController(RobotMap.MANIP_JOYSTICK);

	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	/*X-Box Controller*/
	Button a = new JoystickButton(manipJoystick, 1); //Toggle Succ - OFF Default
	Button b = new JoystickButton(manipJoystick, 2); //Extend & Retract Hatch Release
	Button x = new JoystickButton(manipJoystick, 3); //Intake IN - Hold
	//not final. this is a click to check the lift application
	//Button y = new JoystickButton(manipJoystick, 4);
	Button rb = new JoystickButton(manipJoystick, 9); //Horizontal Slide OUT
	Button lb = new JoystickButton(manipJoystick, 10); //Horizontal Slide IN

	Button dpLeft = new JoystickButton(manipJoystick, 5);	//placeholder numbers. evan dont yell at me
	Button dpRight = new JoystickButton(manipJoystick, 6);
	Button dpDown = new JoystickButton(manipJoystick, 7);
	Button dpUp = new JoystickButton(manipJoystick, 8);

	Button back = new JoystickButton(manipJoystick, 7);

	/*Drive Joysticks*/
	Button rightDriveTrigger = new JoystickButton(rightJoystick, 1); //Climb UP
	Button leftDriveTrigger = new JoystickButton(leftJoystick, 1); //Climb DOWN
	
	public OI() {
		x.whileHeld(new IntakeCommands());
		b.whenPressed(new HatchReleaseCommands());
		a.toggleWhenPressed(new VacuumCommands());
		rb.whileHeld(new HorizontalSlideCommands(true));
		lb.whileHeld(new HorizontalSlideCommands(false));
		rightDriveTrigger.whileHeld(new PlatformLiftCommands(true));
		leftDriveTrigger.whileHeld(new PlatformLiftCommands(false));

		back.whenPressed(new LinearSlideCommands(1));
		dpLeft.whenPressed(new LinearSlideCommands("first"));
		dpRight.whenPressed(new LinearSlideCommands("low"));
		dpDown.whenPressed(new LinearSlideCommands("middle"));
		dpUp.whenPressed(new LinearSlideCommands("high"));
	}

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}