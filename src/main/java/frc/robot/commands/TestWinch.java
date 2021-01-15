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

import frc.robot.subsystems.Lift;


public class TestWinch extends CommandBase {

    public TestWinch(){
        //Just added another constructor to overload the other so i can make 
        //the object without parameters

    }

	public TestWinch(Lift subsystem){
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
        if(RobotContainer.xboxController.getPOV(0) == 270){
            RobotContainer.liftObject.winchMotor.set(0.5);
        }else{
            RobotContainer.liftObject.winchMotor.set(0.0);
        }
        
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		RobotContainer.liftObject.winchMotor.set(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
}
