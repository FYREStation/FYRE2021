/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ShooterAndKick extends SubsystemBase {

  public VictorSPX shooterController;
  public VictorSPX kickController;
  public Encoder kickEncoder;
  public double angle;
  //Spark shooterController;
  //Spark kickController;

  public ShooterAndKick() {
    // SO this is commented out because I dont know where it goes 
    // This needs to be in constants kickEncoder = new Encoder();
    shooterController = new VictorSPX(Constants.SHOOTER_MOTORA);
    kickController = new VictorSPX(Constants.KICK_MOTORA);

  }
 
  public void smartBoardTest(){

  }

  @Override
  public void periodic() {
    //smartBoardTest();

  }
}