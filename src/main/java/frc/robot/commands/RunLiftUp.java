/*----------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;

public class RunLiftUp extends CommandBase {
	//public boolean switchedHit = false;
	private Lift m_Lift;

	public RunLiftUp(Lift subsystem) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//requires(Robot.m_drivetrain);
		m_Lift = subsystem;	
	
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
	} 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		/*
		boolean stateSwitch = RobotContainer.liftObject.LiftLimitSwitch.get();
    	if (stateSwitch && !switchedHit){
        	m_Lift.liftMotor.set(0.50);
    	}else{
			switchedHit = true;
      		m_Lift.liftMotor.set(0.0);
		}
		*/
		m_Lift.liftMotor.set(1.0);
		System.out.println("LifEnc: "+ m_Lift.liftEncoder.get()); //how to fix this t get revolutiosn without dividng?
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		m_Lift.liftMotor.set(0.0);
	}
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
}
