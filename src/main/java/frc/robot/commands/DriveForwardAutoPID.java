  /*----------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.controller.PIDController;

public class DriveForwardAutoPID extends CommandBase {

	private Drivetrain drive_train;
	private DriverStation driver_station;
	private PIDController autoPID;
	private double P = 0.1;
	private double I = 0;
	private double D = 0;

	public DriveForwardAutoPID(Drivetrain driveTrain) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		drive_train = driveTrain;
		
		addRequirements(drive_train);
		autoPID = new PIDController(P, I, D);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
        /*
        while (driver_station.getMatchTime() > 120.0){
            drive_train.arcadeDrive(0.1, 0.0);
        }
		*/
		
		

		
	} 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		autoPID.setSetpoint(10);
		drive_train.arcadeDrive(autoPID.calculate(RobotContainer.m_drivetrain.getLeftDriveEncoderCount()), 
		0);

		/*
		if (autoPID.atSetpoint()){
			isFinished();
		}
		*/
		
		if (Math.abs(autoPID.getPositionError()) < 0.03){
				isFinished();
		}
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
}
