package main.java.org.usfirst.frc.team1502.robot.subsystems;

import org.usfirst.frc.team1502.robot.Robot;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1502.robot.InverseKinematics;

public class RoboticArm extends Subsystem {

    int armLength, forearmLength;
    InverseKinematics math;
    int[] currentPos, angle;
    final int TICKS_PER_REV = null;

    public RoboticArm(int armLength, int forearmLength) {
        this.armLength = armLength;
        this.forearmLength = forearmLength;
        math = new InverseKinematics (armLength, forearmLength);
    }

    public void bringTo (int[] distMoved) {
        currentPos[0] += distMoved[0];
        currentPos[1] += distMoved[1];
        angle = math.run(currentPos);
        int[] ticks = {angleToTick(angle[0]), angleToTick(angle[1])};
        moveToTick(ticks);
    }

    private int angleToTick (int angle) {
        return angle / 360 * TICKS_PER_REV % 360;
    }

    private void moveToTick (int[] ticks) {
        /*
        i dont know how to work encs so far, so ill do this later
        */
    }

    public int expRate (int x) {
        return Math.log(x * x + 1);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}