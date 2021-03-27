/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import java.lang.Math;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

import edu.wpi.first.wpilibj.util.Color;


 //READ THIS COMMENT BEFORE YOU EDIT ANYTHING IN HERE!!!!
 //If you set two PWM or two DIO ports together then errors will
 //occur and it will not work out well
public final class Constants {
    public static final int DRIVER_CONTROLLER = 1;
    public static final int XBOX_CONTROLLER = 0;
    public static final int XBOX_LXAXIS = 0;
    public static final int XBOX_LYAXIS = 1;
    public static final int XBOX_RXAXIS = 4;
    public static final int XBOX_RYAXIS = 5;

    public static double throttle = 0.0;
    public static boolean invertedAxis = true;

    //Shouldn't there be two axes? One for X and the other for Y???
    public static final int DRIVER_CONTROLLER_MOVE_AXIS = 1;
    public static final int DRIVER_CONTROLLER_ROTATE_AXIS = 2;
    public static final int DRIVER_CONTROLLER_SLIDER = 3;


    //Camera
    //public static final int CAMERA_SERVO = 5; //DIO 5

    //Lift
    //public static final int LIFT_LIMIT_SWITCH = 8; //This is no longer being used. //Remember to fix this whole group DIO 0
    public static final int LIFT_ENCODER_A = 8;
    public static final int LIFT_ENCODER_B = 9;
    public static final int LIFT_MOTOR = 3;             //PWM 3
    public static final int WINCH_MOTOR = 2;            //PWM 2
    //public static final int LIFT_CAMERA = 5;            //This is a place holder USB ??

    //Drivetrain
    public static final double driveCircum =  6 * Math.PI;
        //Sparks
        public static final int DRIVETRAIN_LEFT_FRONT_SPARK = 4;          //PWM Port 0
        public static final int DRIVETRAIN_LEFT_BACK_SPARK = 4;           //PWM Port 0 (Reminder to fix this because it got changed)
        public static final int DRIVETRAIN_RIGHT_FRONT_SPARK = 1;         //PWM Port 1
        public static final int DRIVETRAIN_RIGHT_BACK_SPARK = 1;          //PWM Port 1
    
    //Encoders
    public static final int DRIVETRAIN_DRIVE_ENCODER_LEFT_A = 4;      //DIO Port 6
    public static final int DRIVETRAIN_DRIVE_ENCODER_LEFT_B = 5;      //DIO Port 7
    public static final int DRIVETRAIN_DRIVE_ENCODER_RIGHT_A = 6;     //DIO Port 8
    public static final int DRIVETRAIN_DRIVE_ENCODER_RIGHT_B = 7;     //DIO Port 9
  
    //Control Panel
    //public static final int CONTROL_PANEL_MOTOR_ENCODER_A = 3;           //DIO Port 3
    //public static final int CONTROL_PANEL_MOTOR_ENCODER_B = 4;           //DIO Port 4
    public static final int CONTROL_MOTOR = 2; //VEX SPX 2

    //Shooter
    public static final int SHOOTER_MOTORA = 0; //VEX SPX 0
    public static final int KICK_MOTORA = 1; //VEX SPX 1

    //Intake
    public static final int INTAKE_BACK_SPARK_Max = 4; //PWM 5
    public static final int INTAKE_FRONT_SPARK_Max = 3; //PWM 4
    public static final int INTAKE_ENCODER_BACK_A = 0; //DIO 0 CHANNEL A
    public static final int INTAKE_ENCODER_BACK_B = 1; //DIO 1 CHANNEL B
    public static final int INTAKE_ENCODER_FRONT_A = 2; //DIO 2 CHANNEL A
    public static final int INTAKE_ENCODER_FRONT_B = 3; //DIO 3 CHANNEL B



    //Circumference of Control Panel and wheel for Panel
    public static final double PI = Math.PI;
    public static final double circumPanel = 12 * Math.PI;
    public static final double circumWheel = 4 * Math.PI;
  
    public static final Color blue = new Color(0.143, 0.427, 0.429);  
    public static final Color green = new Color(0.197, 0.561, 0.240);
    public static final Color red = new Color(0.561, 0.232, 0.114);
    public static final Color yellow = new Color(0.361, 0.524, 0.113);

    //Encoder Pulse Distances
    public static final double kPulsesPerRevolution = 2048;     // for the AMU encoders we have?
        //LIFT
        public static final double kDistancePerRevolutionLIFT = 1.0;  // guestimate from your code
        public static final double kDistancePerPulseLIFT = kDistancePerRevolutionLIFT / kPulsesPerRevolution;
        //Drive
        public static final double kDistancePerRevolutionDRIVE = 1.0;  // guestimate from your code
        public static final double kDistancePerPulseDRIVE = kDistancePerRevolutionDRIVE / kPulsesPerRevolution;

        public static double leftWheelDistance = 0.0; //In Inches
        public static double rightWheelDistance = 0.0;
}
