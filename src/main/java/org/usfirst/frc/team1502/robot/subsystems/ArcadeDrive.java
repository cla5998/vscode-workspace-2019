package org.usfirst.frc.team1502.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArcadeDrive extends Subsystem{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	TalonSRX leftFront;
	TalonSRX leftBack;
	TalonSRX rightFront;
	TalonSRX rightBack;
	
    public ArcadeDrive(TalonSRX leftFrontTalon, TalonSRX leftBackTalon, TalonSRX rightFrontTalon, TalonSRX rightBackTalon) {
		leftFront = leftFrontTalon;
		leftBack = leftBackTalon;
		rightFront = rightFrontTalon;
		rightBack = rightBackTalon;
	}
    
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	public void move(double moveSpeed, double rotateSpeed) {
        double leftPwr = moveSpeed + rotateSpeed;
        double rightPwr = moveSpeed - rotateSpeed;
        
        leftFront.set(ControlMode.PercentOutput, leftPwr);
        leftBack.set(ControlMode.PercentOutput, leftPwr);
        rightFront.set(ControlMode.PercentOutput, rightPwr);
        rightBack.set(ControlMode.PercentOutput, rightPwr);
	}
}

