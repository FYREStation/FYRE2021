/*----------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeUp extends CommandBase {

	private Intake m_Intake;
	public IntakeUp(Intake subsystem) {
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
        m_Intake.BackSparkMaxIntake.set(-1.0);
        m_Intake.FrontSparkMaxIntake.set(1.0);
    
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		m_Intake.BackSparkMaxIntake.set(0.0);
		m_Intake.FrontSparkMaxIntake.set(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
}
