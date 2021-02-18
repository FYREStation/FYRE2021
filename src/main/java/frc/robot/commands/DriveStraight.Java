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
        double currentPlace = drive_train.getRightDriveEncoderDistance();
        double destination;
		double speed = 0.6;
		
		if(isForward){
			destination =  currentPlace + distance;
			while(currentPlace < destination){
				currentPlace = drive_train.getRightDriveEncoderDistance();
				drive_train.tankDrive(speed, speed);
			}
		}else{
			destination = currentPlace - distance;
			while(currentPlace > destination){
				currentPlace = drive_train.getRightDriveEncoderDistance();
				drive_train.tankDrive(-speed, -speed);
			
			}
		}
	}
	
	public void driveStraightDistance(double distance, boolean isForward, double theSpeed){
        double currentPlace = drive_train.getRightDriveEncoderDistance();
        double destination = currentPlace + distance;
        //double speed = //input theSpeed into equation to get PWM duty cycle;
		double speed = theSpeed; //Comment this line when done testing. Input duty cycle on testing day.

		if(isForward){
			destination =  currentPlace + distance;
			while(currentPlace < destination){
				currentPlace = drive_train.getRightDriveEncoderDistance();
				drive_train.tankDrive(speed, speed);
			}
		}else{
			destination = currentPlace - distance;
			while(currentPlace > destination){
				currentPlace = drive_train.getRightDriveEncoderDistance();
				drive_train.tankDrive(-speed, -speed);
			
			}
		}
	}

	//This is the nonoverloaded version. Should not be used after testing day.
	public void driveArc(double radius, double degree, boolean isForward){
        double currentPlace = drive_train.getRightDriveEncoderDistance();
        double destination;
		double distanceRightArc;
		double distanceLeftArc;
		double distanceMiddleArc;
		double realLeftSpeed;
		double realRightSpeed = 0.4;
	

		//This is keeping track of the right wheel's distance traveled
		//This is checking if it forward and counterclockwise or backward and clockwise
		//(Inside or outside)
		if ((isForward && degree > 0) || !(isForward || degree > 0)){
			distanceRightArc = 2 * Constants.PI * (radius + 15.5) * Math.abs(degree) / 360;
			distanceMiddleArc = 2 * Constants.PI * radius * Math.abs(degree) / 360;
			distanceLeftArc = 2 * Constants.PI * (radius - 15.5) * Math.abs(degree) / 360;;
		}else{
			distanceRightArc = 2 * Constants.PI * (radius -15.5) * Math.abs(degree) / 360;
			distanceMiddleArc = 2 * Constants.PI * radius * Math.abs(degree) / 360;
			distanceLeftArc = 2 * Constants.PI * (radius + 15.5) * Math.abs(degree) / 360;
		}

		realLeftSpeed = realRightSpeed * (distanceLeftArc/distanceRightArc);

		if (isForward){
			destination = currentPlace + distanceRightArc;
			while(currentPlace < destination){
				currentPlace = drive_train.getRightDriveEncoderDistance();
				drive_train.tankDrive(realLeftSpeed, realRightSpeed);	
			}
		}else{
			destination = currentPlace - distanceRightArc;
			while(currentPlace > destination){
				drive_train.tankDrive(-realLeftSpeed, -realRightSpeed);
				
			}	
		}
	}


	/**
	 * @param radius the radius needs to be a double greater than zero
	 * @param degree this must be a double between -360.0 and 360.0 and must not be zero.
	 * @param isForward The direction of the movement is either forward or backwards based on
	 * if isForward is true or not.
	 * 
	 */

	public void driveArc(double radius, double degree, boolean isForward, double realSpeed){
        double currentPlace = drive_train.getRightDriveEncoderDistance();
        double destination;
		double distanceRightArc;
		double distanceLeftArc;
		double distanceMiddleArc;
		double averageRobotSpeed; 
		double realLeftSpeed;
		double realRightSpeed;
		double leftSpeedPWM;
		double rightSpeedPWM;
		double speed = realSpeed;
		double time;

		//This is keeping track of the right wheel's distance traveled
		//This is checking if it forward and counterclockwise or backward and clockwise
		//(Inside or outside)
		if ((isForward && degree > 0) || !(isForward || degree > 0)){
			distanceRightArc = 2 * Constants.PI * (radius + 15.5) * Math.abs(degree) / 360;
			distanceMiddleArc = 2 * Constants.PI * radius * Math.abs(degree) / 360;
			distanceLeftArc = 2 * Constants.PI * (radius - 15.5) * Math.abs(degree) / 360;;
		}else{
			distanceRightArc = 2 * Constants.PI * (radius -15.5) * Math.abs(degree) / 360;
			distanceMiddleArc = 2 * Constants.PI * radius * Math.abs(degree) / 360;
			distanceLeftArc = 2 * Constants.PI * (radius + 15.5) * Math.abs(degree) / 360;
		}

		time = distanceMiddleArc/speed;
		realLeftSpeed = distanceLeftArc/time;
		realRightSpeed = distanceRightArc/time;
		//Note to self and Ryan and others. This needs to be added.
		leftSpeedPWM = 0; //realLeftSpeed input into PWM equation
		rightSpeedPWM = 0; //realRightSpeed input into PWM equation

		if (isForward){
			destination = currentPlace + distanceRightArc;
			while(currentPlace < destination){
				currentPlace = drive_train.getRightDriveEncoderDistance();
				drive_train.tankDrive(leftSpeedPWM, rightSpeedPWM);	
			}
		}else{
			destination = currentPlace - distanceRightArc;
			while(currentPlace > destination){
				drive_train.tankDrive(-leftSpeedPWM, -rightSpeedPWM);
				
			}	
		}



	}
}
