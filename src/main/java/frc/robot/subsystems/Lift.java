/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.Constants;


public class Lift extends SubsystemBase {
  
  public Spark liftMotor;
  public Spark winchMotor;
  public DigitalInput LiftLimitSwitch;
  public Encoder liftEncoder;

  public Lift() {
    //LiftLimitSwitch = new DigitalInput(Constants.LIFT_LIMIT_SWITCH);
    liftEncoder = new Encoder(Constants.LIFT_ENCODER_A,Constants.LIFT_ENCODER_B, true, Encoder.EncodingType.k4X);
    liftMotor = new Spark(Constants.LIFT_MOTOR);
    winchMotor = new Spark(Constants.WINCH_MOTOR);

    liftEncoder.reset();
    liftEncoder.setDistancePerPulse(Constants.kDistancePerPulseLIFT); 
  }

  public double getLiftRev(){
    System.out.println("Revolutions traveled" + this.liftEncoder.getDistance());
    return this.liftEncoder.getDistance();
  }

  @Override
  public void periodic() {

  }
}








