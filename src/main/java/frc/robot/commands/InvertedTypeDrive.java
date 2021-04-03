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


public class InvertedTypeDrive extends CommandBase {

	public InvertedTypeDrive() {
		
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
	} 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {

		//System.out.println(Constants.throttle * move_Speed.getAsDouble() + "and " + Constants.throttle *  rotate_Speed.getAsDouble());
		
		Constants.isTank = !Constants.isTank;	
		System.out.print("Drive System is now: ");
		if(Constants.isTank){
			System.out.println("Wendy Mode");
		}else{
			System.out.println("Arcade Mode");
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
