/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Add your docs here.
 */
public class DPadButton extends Button {
    XboxController joystick;
    Direction direction;
    public DPadButton(XboxController joystick, Direction direction) {
        this.joystick = joystick;
        this.direction = direction;
    }
    
    @Override
    public boolean get() {
        int degree = joystick.getPOV(0);
        
        return degree == direction.degree;
    }
    
    public enum Direction {
        Up (0),
        Right (90),
        Down (180),
        Left (270);
        
        int degree;
        Direction(int degree) {
            this.degree = degree;
        }
    }
}
