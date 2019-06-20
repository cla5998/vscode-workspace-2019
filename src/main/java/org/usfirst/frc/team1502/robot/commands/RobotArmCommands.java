package org.usfirst.frc.team1502.robot.commands;

import org.usfirst.frc.team1502.robot.Robot;
import org.usfirst.frc.team1502.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class RobotArmCommands extends Command {

    public RobotArmCommands() {
        requires (Robot.arm);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        double[] distMoved = {
            Drivetrain.expRate(Robot.m_oi.rightJoystick.getX(), 2),
            Drivetrain.expRate(Robot.m_oi.rightJoystick.getY(), 2)
        };
        Robot.arm.bringTo(distMoved);
    }

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
        end();
    }
}
