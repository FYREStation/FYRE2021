/*----------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

public class GetGameColor extends CommandBase {

    String colorWanted;
    public GetGameColor(ControlPanel subsystem) {
		// Use requires() here to declare subsystem dependencies
		
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
        colorWanted = " ";
        
	} 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
        System.out.println(DriverStation.getInstance().getGameSpecificMessage());
            
        if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) != ' '){
                colorWanted = DriverStation.getInstance().getGameSpecificMessage();
        }
          
            
        System.out.println("colorWanted is: " + colorWanted);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
}
