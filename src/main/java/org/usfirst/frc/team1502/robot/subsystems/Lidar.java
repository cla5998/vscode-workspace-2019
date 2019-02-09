/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;



import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.usfirst.frc.team1502.robot.Robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Lidar extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Lidar(I2C.Port port, int deviceAddress) {
    sensor = new I2C(port, deviceAddress);
  }
I2C sensor;

  short cX = 0, cY = 0, cZ = 0;

  byte[] dataBuffer = new byte[6];

  public void init() {
   sensor.write(0x02, 0x00);
  } 

  public void readSensor() {
    sensor.read(0x03, 6, dataBuffer);
    ByteBuffer compBuffer = ByteBuffer.wrap(dataBuffer);
    compBuffer.order(ByteOrder.BIG_ENDIAN);
    cX = compBuffer.getShort();
    cY = compBuffer.getShort();
    cZ = compBuffer.getShort();	
        	
    System.out.println(cX);
    // SmartDashboard.putNumber("CompY", cY);
    // SmartDashboard.putNumber("CompZ", cZ);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
