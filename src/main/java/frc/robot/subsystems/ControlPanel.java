/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.I2C.Port;

public class ControlPanel extends SubsystemBase{

  
  public VictorSPX panelMotor;
  //public Encoder panelEncoder;
  public ColorSensorV3 colorSensor;

  public ControlPanel() {
    I2C.Port i2cPort = I2C.Port.kOnboard;
    //panelEncoder = new Encoder(Constants.CONTROL_PANEL_MOTOR_ENCODER_A, Constants.CONTROL_PANEL_MOTOR_ENCODER_B);
    panelMotor = new VictorSPX(Constants.CONTROL_MOTOR);
    colorSensor = new ColorSensorV3(i2cPort);

  }
  
  public Color GetSensorColor(){
    /*
    int detectedRed = colorSensor.getRed();
    int detectedGreen = colorSensor.getGreen();
    int detectedBlue = colorSensor.getBlue();
    */

    Color detectedColor = colorSensor.getColor();
    return detectedColor;
    
  }

  public ColorMatchResult GetMatchedColor(Color colorToMatch){
    final ColorMatch colorMatch = new ColorMatch();
    colorMatch.addColorMatch(Constants.blue);
    colorMatch.addColorMatch(Constants.green);
    colorMatch.addColorMatch(Constants.red);
    colorMatch.addColorMatch(Constants.yellow);

    //colorMatch.setConfidenceThreshold(0.95);
    final ColorMatchResult thisColor = colorMatch.matchClosestColor(colorToMatch);

    if (thisColor.confidence >= 0.925){
      return thisColor;
    }else{
      final ColorMatchResult badColor = new ColorMatchResult(Color.kBlack, 0.0);
      return badColor;
    }
  }

  public String printColor(){
    Color printedColor = GetMatchedColor(GetSensorColor()).color;
      if(printedColor.equals(Constants.blue)){
        return "Blue";
      }else if (printedColor.equals(Constants.green)){
        return "Green";
      }else if (printedColor.equals(Constants.red)){
        return "Red";
      }else if (printedColor.equals(Constants.yellow)){
        return "Yellow";
      }else{
        return "VERY UNSURE";
      }
  }

  @Override
  public void periodic() {
  
  }

}
