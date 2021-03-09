/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * Add your docs here.
 */
public class Drivetrain extends SubsystemBase {
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    /*
    private final Spark LeftFrontSpark = new Spark(Constants.DRIVETRAIN_LEFT_FRONT_SPARK);
    private final Spark LeftBackSpark = new Spark(Constants.DRIVETRAIN_LEFT_BACK_SPARK);
    private final Spark RightFrontSpark = new Spark(Constants.DRIVETRAIN_RIGHT_FRONT_SPARK);
    private final Spark RightBackSpark = new Spark(Constants.DRIVETRAIN_RIGHT_BACK_SPARK);
    */

    private final Spark LeftSparksAll = new Spark(Constants.DRIVETRAIN_LEFT_FRONT_SPARK);
    private final Spark RightSparksAll = new Spark(Constants.DRIVETRAIN_RIGHT_FRONT_SPARK);

    private final SpeedControllerGroup leftMotors = new SpeedControllerGroup(LeftSparksAll); //Removed Back Spark
    private final SpeedControllerGroup rightMotors = new SpeedControllerGroup(RightSparksAll); //Removed Back Spark

	//private final SpeedControllerGroup leftMotors = new SpeedControllerGroup(LeftFrontSpark); //Removed Back Spark
   //private final SpeedControllerGroup rightMotors = new SpeedControllerGroup(RightFrontSpark); //Removed Back Spark

    private final DifferentialDrive differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
    
    // Encoders
    private final Encoder LeftDriveEncoder = new Encoder(Constants.DRIVETRAIN_DRIVE_ENCODER_LEFT_A, Constants.DRIVETRAIN_DRIVE_ENCODER_LEFT_B,true,Encoder.EncodingType.k4X);
    private final Encoder RightDriveEncoder = new Encoder(Constants.DRIVETRAIN_DRIVE_ENCODER_RIGHT_A, Constants.DRIVETRAIN_DRIVE_ENCODER_RIGHT_B,true,Encoder.EncodingType.k4X);
    
	public Drivetrain() {
        //LeftSparksAll.setSafetyEnabled(false);
        //RightSparksAll.setSafetyEnabled(false);
        //differentialDrive.setSafetyEnabled(false);
        LeftSparksAll.setExpiration(99999);
        RightSparksAll.setExpiration(99999);
        LeftSparksAll.setSafetyEnabled(false);
        RightSparksAll.setSafetyEnabled(false);
        differentialDrive.setExpiration(99999);
        LeftDriveEncoder.reset();
        RightDriveEncoder.reset();
        LeftDriveEncoder.setDistancePerPulse(Constants.driveCircum/(2048.0));
        LeftDriveEncoder.setReverseDirection(false);
        RightDriveEncoder.setDistancePerPulse(Constants.driveCircum/(2048.0));
    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        
        differentialDrive.setDeadband(0.10); //This apparently creates a deadzone for us
       
        Constants.leftWheelDistance = (this.LeftDriveEncoder.getDistance());
        Constants.rightWheelDistance = (this.RightDriveEncoder.getDistance());
        System.out.println("DisInInches Left:" + this.LeftDriveEncoder.getDistance());
        System.out.println("DisInInches Right:" + this.RightDriveEncoder.getDistance());
        //System.out.println("LEFT DRIVE:" + Constants.leftWheelDistance); 
        //System.out.println("RIGHT DRIVE:" + Constants.rightWheelDistance); 
        
        differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
        
        //System.out.println("Left"+LeftDriveEncoder.get());
        //System.out.println("Right"+RightDriveEncoder.get());
        //System.out.println("LEFT DRIVE: " + (getLeftDriveEncoderCount()));
        //System.out.println("RIGHT DRIVE: " + (getRightDriveEncoderCount()));
    }

    public void tankDriving(double moveSpeedLeft, double moveSpeedRight) {
        differentialDrive.tankDrive(moveSpeedLeft, moveSpeedRight);
    }

    public double getLeftDriveEncoderCount() {
        return this.LeftDriveEncoder.get();
    }

    public double getRightDriveEncoderCount() {
        return this.RightDriveEncoder.get();
    }

    public void resetLeftDriveEncoderCount() {
        LeftDriveEncoder.reset();
    }

    public void resetRightDriveEncoderCount() {
        RightDriveEncoder.reset();
    }

    public double getLeftDriveEncoderDistance() {
		return LeftDriveEncoder.getDistance();
	}

    public double getRightDriveEncoderDistance() {
        return RightDriveEncoder.getDistance();    
    }

    public void smartBoardTest(){
        SmartDashboard.putNumber("LeftEncoder: ", getLeftDriveEncoderDistance());
        SmartDashboard.putNumber("RightEncoder: ", getRightDriveEncoderDistance());
        SmartDashboard.updateValues();
    }

    @Override
    public void periodic() {
        smartBoardTest();
    }
}