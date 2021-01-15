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
import edu.wpi.first.wpilibj.util.Color;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class RotationControl extends CommandBase {

	public RotationControl() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//requires(Robot.m_drivetrain);
	
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
	} 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		int currentCount = 0;
		double revNeeded = 3*Constants.circumWheel/Constants.circumPanel;
		Color detectedColor = RobotContainer.controlPanelObject.GetSensorColor();

		/*
		panelEncoder.reset();
		while (currentCount < revNeeded) {
		Robot.ControlPanelObject.panelMotor.set(ControlMode.PercentOutput, 0.5);
		currentCount = panelEncoder.get();
		}
		*/
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		RobotContainer.controlPanelObject.panelMotor.set(ControlMode.PercentOutput, 0.0);
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
}
