package org.usfirst.frc.team1502.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public class Rumbler {
	XboxController joystick;

	public Rumbler(XboxController joystick) {
		this.joystick = joystick;
	}
	
	public void setLeft(double power) {
		joystick.setRumble(RumbleType.kLeftRumble, power);
	}
	
	public void setRight(double power) {
		joystick.setRumble(RumbleType.kRightRumble, power);
	}
	
	public void setBoth(double power) {
		setLeft(power);
		setRight(power);
	}
	
	public Timer rumbleLeftFor(double power, double millis) {
		setLeft(power);
		return new Timer(() -> setLeft(0), (long) millis, Timer.TimerType.kTimeout);
	}
	
	public Timer rumbleRightFor(double power, double millis) {
		setRight(power);
		return new Timer(() -> setRight(0), (long) millis, Timer.TimerType.kTimeout);
	}
	
	public Timer rumbleBothFor(double power, double millis) {
		setBoth(power);
		return new Timer(() -> setBoth(0), (long) millis, Timer.TimerType.kTimeout);
	}
}