/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot;

import org.usfirst.frc.team1502.robot.DPadButton.Direction;
import org.usfirst.frc.team1502.robot.commands.*;
import org.usfirst.frc.team1502.robot.subsystems.LinearSlide;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

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

	/* X-Box Controller */
	Button a = new JoystickButton(manipJoystick, 1); // Toggle Succ - OFF Default
	Button b = new JoystickButton(manipJoystick, 2); // Extend & Retract Hatch Release
	Button x = new JoystickButton(manipJoystick, 3); // Intake IN - Hold
	Button y = new JoystickButton(manipJoystick, 4); // Set linear slide to pushing intake height
	Button rb = new JoystickButton(manipJoystick, 5); // Horizontal Slide OUT
	Button lb = new JoystickButton(manipJoystick, 6); // Horizontal Slide IN
	Button leftManipTrigger = new Trigger(manipJoystick, Hand.kLeft, 0.1);
	Button rightManipTrigger = new Trigger(manipJoystick, Hand.kRight, 0.1);

	Button manipStickLeftClick = new JoystickButton(manipJoystick, 9);

	Button guidedDrivingButton = new JoystickButton(rightJoystick, 2);

	Button dpUp = new DPadButton(manipJoystick, Direction.Up);
	Button dpLeft = new DPadButton(manipJoystick, Direction.Left);
	Button dpDown = new DPadButton(manipJoystick, Direction.Down);
	Button dpRight = new DPadButton(manipJoystick, Direction.Right);
	Button back = new JoystickButton(manipJoystick, 7); // linear slide toggle switch.
	Button start = new JoystickButton(manipJoystick, 8);
	
	/* Drive Joysticks */
	Button rightDriveTrigger = new JoystickButton(rightJoystick, 1); // Climb UP
	Button leftDriveTrigger = new JoystickButton(leftJoystick, 1); // Climb DOWN
	Button rightHorizontalButton = new JoystickButton(rightJoystick, 3); // Climb FORWARD
	Button leftHorizontalButton = new JoystickButton(leftJoystick, 3); // Climb BACK
	Button compressorButton = new JoystickButton(rightJoystick, 7);

	public OI() {
		a.toggleWhenPressed(new VacuumCommands()); //Succ
		x.whileHeld(new IntakeCommands()); //Intake In
		b.whileHeld(new HatchReleaseCommands()); //Hatch Pistons

		//Horizontal Slide
		rb.whileHeld(new HorizontalSlideCommands(.6));
		lb.whileHeld(new HorizontalSlideCommands(-.6));

		//Climb Vertical
		leftDriveTrigger.whileHeld(new PlatformLiftVerticalCommands(PlatformLiftVerticalCommands.Direction.DOWN));
		rightDriveTrigger.whileHeld(new PlatformLiftVerticalCommands(PlatformLiftVerticalCommands.Direction.UP));
		
		//Climb Horizontal
		leftHorizontalButton.whileHeld(new PlatformLiftHorizontalCommands(PlatformLiftHorizontalCommands.Direction.BACK));
		rightHorizontalButton.whileHeld(new PlatformLiftHorizontalCommands(PlatformLiftHorizontalCommands.Direction.FORWARD));

		compressorButton.whileHeld(new CompressorCommands());

		//Linear Slide -- Automatic
		start.whenPressed(new LinearSlideLocationCommands(LinearSlide.Level.Ship)); //Cargo Ship Port
		back.whenPressed(new ToggleSlideCommands());
		dpDown.whenPressed(new LinearSlideLocationCommands(LinearSlide.Level.Low));
		dpLeft.whenPressed(new LinearSlideLocationCommands(LinearSlide.Level.Middle));
		dpUp.whenPressed(new LinearSlideLocationCommands(LinearSlide.Level.High));
		dpRight.whenPressed(new LinearSlideLocationCommands(LinearSlide.Level.Ground));
		y.whenPressed(new LinearSlideLocationCommands(LinearSlide.Level.IntakePush, 0.3));
		//Linear Slide -- Manual
		// manipStickLeftClick.whileHeld(new LinearSlideManualCommands());
		leftManipTrigger.whileHeld(new LinearSlideManualCommands());
		rightManipTrigger.whileHeld(new LinearSlideManualCommands());

		guidedDrivingButton.whileHeld(new GuidedDrivingCommands()); //Vision Tracking Button
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