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
import frc.robot.Constants;

public class DriveStraight extends CommandBase {

	private Drivetrain drive_train;
	private DriverStation driver_station;
    /*
    private PIDController autoPID;
	private double P = 0.1;
	private double I = 0;
	private double D = 0;
    */
    
    public DriveStraight(Drivetrain driveTrain) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		drive_train = driveTrain;
		
		addRequirements(drive_train);
		
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
        

		
	} 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
    		
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

    public void driveStraightDistance(double distance, boolean isForward){
        double currentPlace = Constants.rightWheelDistance;
        double destination = currentPlace + distance;
        double speed = 0.6;

        while(Constants.rightWheelDistance < destination){
            if (isForward){
                drive_train.tankDrive(speed, speed);
            }else{
                drive_train.tankDrive(-speed, -speed);
            }
            
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param radius the radius needs to be a double greater than zero
	 * @param degree this must be a double between -360.0 and 360.0 and must not be zero.
	 * @param isForward The direction of the movement is either forward or backwards based on
	 * if isForward is true or not.
	 * 
	 */

    public void driveArc(double radius, double degree, boolean isForward){
        double currentPlace = Constants.rightWheelDistance;
        double destination;
		double distanceCircum;
		
		//This is keeping track of the right wheel's distance traveled
		if ((isForward && degree > 0) || !(isForward || degree > 0)){
        	distanceCircum = 2 * Constants.PI * (radius + 15.5) * degree / 360; 
		}else{
			distanceCircum = 2 * Constants.PI * (radius -15.5) * degree / 360;
		}

		destination = currentPlace + distanceCircum;

        while(Constants.rightWheelDistance < destination){
            if (isForward){
                drive_train.tankDrive(0.60, 0.60);
            }else{
                drive_train.tankDrive(-0.60, 0-.60);
            }
            
		}
    }


}
