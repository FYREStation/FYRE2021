  /*----------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DriverStation;

public class DriveUAuto extends CommandBase {

	private Drivetrain drive_train;
    //private DriverStation driverStationObject;
	//driverStationObject = new DriverStation();
	private Timer myTimer = new Timer();

    public DriveUAuto(Drivetrain driveTrain) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		drive_train = driveTrain;
		
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
        /*
        while (driver_station.getMatchTime() > 120.0){
            drive_train.arcadeDrive(0.1, 0.0);
        }
		*/
        drive_train.resetLeftDriveEncoderCount();
        drive_train.resetRightDriveEncoderCount();
	} 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		
		
		while(Constants.rightWheelDistance < 42){
            drive_train.arcadeDrive(0.60, 0.0);
		}
		
		
		drive_train.resetLeftDriveEncoderCount();
		drive_train.resetRightDriveEncoderCount();
		/*
		while(Constants.rightWheelDistance < 1.28){
			drive_train.arcadeDrive(0.0, 0.75);
		}
		drive_train.resetLeftDriveEncoderCount();
		drive_train.resetRightDriveEncoderCount();
		
		
		while(Constants.rightWheelDistance < 1.28){
			drive_train.arcadeDrive(0.0, 0.75);
		}
		drive_train.resetLeftDriveEncoderCount();
		drive_train.resetRightDriveEncoderCount();
		*/

		/*
        if (Timer.getMatchTime() > 7){
		RobotContainer.intakeObject.BackSparkMaxIntake.set(1.0);
		RobotContainer.intakeObject.FrontSparkMaxIntake.set(-1.0);
		RobotContainer.shooterObject.shooterController.set(ControlMode.PercentOutput, 0.88);
		RobotContainer.shooterObject.kickController.set(ControlMode.PercentOutput,1.0);
		}else{
			RobotContainer.intakeObject.BackSparkMaxIntake.set(0.0);
		RobotContainer.intakeObject.FrontSparkMaxIntake.set(0.0);
		RobotContainer.shooterObject.shooterController.set(ControlMode.PercentOutput, 0.0);
		RobotContainer.shooterObject.kickController.set(ControlMode.PercentOutput, 0.0);
		}
		*/

		RobotContainer.intakeObject.BackSparkMaxIntake.set(1.0);
		RobotContainer.intakeObject.FrontSparkMaxIntake.set(-1.0);
		RobotContainer.shooterObject.shooterController.set(ControlMode.PercentOutput, 0.88);
		RobotContainer.shooterObject.kickController.set(ControlMode.PercentOutput,1.0);
		
		/*
		if(Timer.getMatchTime() < 7){
			drive_train.resetLeftDriveEncoderCount();
			drive_train.resetRightDriveEncoderCount();
			
			while(Constants.rightWheelDistance < 1.28){
				drive_train.arcadeDrive(0.0, 0.55);
			}
			drive_train.resetLeftDriveEncoderCount();
			drive_train.resetRightDriveEncoderCount();
			while(Constants.rightWheelDistance < 10){
				drive_train.arcadeDrive(0.60, 0.0);
			}

		}
		*/

		isFinished();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		RobotContainer.intakeObject.BackSparkMaxIntake.set(0.0);
		RobotContainer.intakeObject.FrontSparkMaxIntake.set(0.0);
		RobotContainer.shooterObject.shooterController.set(ControlMode.PercentOutput, 0.0);
		RobotContainer.shooterObject.kickController.set(ControlMode.PercentOutput, 0.0);
		drive_train.arcadeDrive(0.0, 0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
}
