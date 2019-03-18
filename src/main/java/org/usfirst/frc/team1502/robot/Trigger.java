/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;

public class Trigger extends Button {
    XboxController joystick;
    double threshold;
    Hand hand;
    public Trigger(XboxController joystick, Hand hand, double threshold) {
        this.joystick = joystick;
        this.hand = hand;
        this.threshold = threshold;
    }
    
    @Override
    public boolean get() {
        return joystick.getTriggerAxis(hand) > threshold;
    }
}
