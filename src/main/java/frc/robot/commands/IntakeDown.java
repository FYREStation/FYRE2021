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

import frc.robot.subsystems.Intake;

public class IntakeDown extends CommandBase {

	private Intake m_Intake;
	public IntakeDown(Intake subsystem) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//requires(Robot.m_drivetrain);
		m_Intake = subsystem;
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
	} 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
        m_Intake.BackSparkMaxIntake.set(1.0);
        m_Intake.FrontSparkMaxIntake.set(-1.0);
    //Errors were here. I just commented this out for testing. (Tyler) 
	
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		RobotContainer.intakeObject.BackSparkMaxIntake.set(0.0);
		RobotContainer.intakeObject.FrontSparkMaxIntake.set(0.0);
	}
}
