package org.usfirst.frc.team1502.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Tank Drive 
 */
public class TankDrive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	TalonSRX leftFront = null;
	TalonSRX leftBack = null;
	TalonSRX rightFront = null;
	TalonSRX rightBack = null;
	
	public TankDrive(TalonSRX leftFrontTalon, TalonSRX leftBackTalon, TalonSRX rightFrontTalon, TalonSRX rightBackTalon) {
		leftFront = leftFrontTalon;
		leftBack = leftBackTalon;
		rightFront = rightFrontTalon;
		rightBack = rightBackTalon;
	}
	
	public void move(double leftSpeed, double rightSpeed) {
		double leftPwr = leftSpeed;
		double rightPwr = rightSpeed;
		
		leftFront.set(ControlMode.PercentOutput, leftPwr);
		leftBack.set(ControlMode.PercentOutput, leftPwr);
		rightFront.set(ControlMode.PercentOutput, rightPwr);
		rightBack.set(ControlMode.PercentOutput, rightPwr);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

