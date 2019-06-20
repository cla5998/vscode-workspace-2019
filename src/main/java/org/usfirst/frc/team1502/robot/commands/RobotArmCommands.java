package main.java.org.usfirst.frc.team1502.robot.commands;

import org.usfirst.frc.team1502.robot.Robot;

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
        int[] distMoved = {Robot.arm.expRate(Robot.m_oi.rightJoystick.getX()), Robot.arm.expRate(Robot.m_oi.rightJoystick.gety())};
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
