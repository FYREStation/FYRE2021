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
import frc.robot.subsystems.ControlPanel;
import com.ctre.phoenix.motorcontrol.ControlMode;


public class PositionControl extends CommandBase {

	String colorLookingFor;
	ControlPanel colorDetection;
	public PositionControl(ControlPanel subsystem) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//requires(Robot.m_drivetrain);
		colorDetection = subsystem;
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {

		colorLookingFor = "Red";
		//colorLookingFor = DriverStation.getInstance().getGameSpecificMessage()
		
	} 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() { 
		
		System.out.println(colorDetection.printColor());
		
		/*
		while (!colorLookingFor.equals(colorDetection.printColor())){
			RobotContainer.controlPanelObject.panelMotor.set(ControlMode.PercentOutput,0.35);
			//StopLoop(); - if boolean should be false
		}
		*/
		
  	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		RobotContainer.controlPanelObject.panelMotor.set(ControlMode.PercentOutput, 0.0);
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
}
