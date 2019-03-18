package org.usfirst.frc.team1502.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1502.robot.Robot;

public class CompressorCommands extends Command {
    
    public CompressorCommands() {
        requires(Robot.compressor);
    }

    @Override
    public void initialize() {
        Robot.compressor.run();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
        Robot.compressor.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}