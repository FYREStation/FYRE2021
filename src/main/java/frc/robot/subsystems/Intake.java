/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Encoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Intake extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  public CANSparkMax FrontSparkMaxIntake;
  public CANSparkMax BackSparkMaxIntake; 

  //public Encoder FrontIntakeEncoder;
 // public Encoder BackIntakeEncoder;

  public Intake() {
    
    BackSparkMaxIntake = new CANSparkMax(Constants.INTAKE_BACK_SPARK_Max, CANSparkMaxLowLevel.MotorType.kBrushed);
    FrontSparkMaxIntake = new CANSparkMax(Constants.INTAKE_FRONT_SPARK_Max,CANSparkMaxLowLevel.MotorType.kBrushed);  
    BackSparkMaxIntake.setCANTimeout(999999);
    FrontSparkMaxIntake.setCANTimeout(999999);

   // Encoder FrontIntakeEncoder = new Encoder(Constants.INTAKE_ENCODER_FRONT_A, Constants.INTAKE_ENCODER_FRONT_B);
   // Encoder BackIntakeEncoder = new Encoder(Constants.INTAKE_ENCODER_BACK_A, Constants.INTAKE_ENCODER_BACK_B);

  }

  public void runIntakeDown(){
    BackSparkMaxIntake.set(-1.0);
    FrontSparkMaxIntake.set(1.0);
  }

  public void runIntakeUp(){
    BackSparkMaxIntake.set(1.0);
    FrontSparkMaxIntake.set(-1.0);
  }

  public void stopIntake(){
    BackSparkMaxIntake.set(0.0);
    FrontSparkMaxIntake.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}