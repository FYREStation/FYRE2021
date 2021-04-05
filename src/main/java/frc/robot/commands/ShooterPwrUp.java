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

public class ShooterPwrUp extends CommandBase
{
    public ShooterPwrUp()
    {

    }

    //Called just before this Command runs the first time
    @Override
    public void initialize()
    {

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute()
    {
        if(Constants.shooterPower < 1.0)
        {
            Constants.shooterPower += 0.025;
        }
        System.out.println("Shooter Power is now: " + Constants.shooterPower);
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
	
	}
}
