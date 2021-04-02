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
	@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
	private final Drivetrain drive_train;
	//private final DriverStation driver_station;
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
        //driveStraightDistance(30,true);
		//driveSlalom();
		//driveBarrel();
		driveBounce();
	} 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
    		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		drive_train.tankDriving(0,0);
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
		double speed = 30; //Speed is inches/second
		double speedPWM = ((speed * 0.465) + 44.1)/100;

		if(speedPWM > 0.8){
			System.out.println("SPEED TOO HIGH!");
		}else if(speedPWM < 0.5){
			System.out.println("SPEED TOO LOW!");
		}else{
			if(isForward){
				destination =  currentPlace + distance;
				while(currentPlace < destination){
					System.out.println("Current Place is:" + currentPlace);
					currentPlace = drive_train.getRightDriveEncoderDistance();
					drive_train.tankDriving((speedPWM+0.03), speedPWM);
				}
			}else{
				destination = currentPlace - distance;
				while(currentPlace > destination){
					System.out.println("Current Place is:" + currentPlace);
					currentPlace = drive_train.getRightDriveEncoderDistance();
					drive_train.tankDriving(-(speedPWM+0.03), -speedPWM);
				
				}
			}
		}
	}
	
	public void driveStraightDistance(double distance, boolean isForward, double realSpeed){
		//drive_train.resetLeftDriveEncoderCount();
		double currentPlace = drive_train.getLeftDriveEncoderDistance();
        double destination;
		double speedPWM = ((realSpeed * 0.465) + 44.1)/100;
		
		/*if(speedPWM > 0.8){
			System.out.println("SPEED TOO HIGH!");
		}else if(speedPWM < 0.5){
			System.out.println("SPEED TOO LOW!");
		}else{*/
			if(isForward){
				destination = currentPlace + distance;
				drive_train.tankDriving((speedPWM + 0.02), speedPWM);

				while(currentPlace < destination){
					System.out.println("Current Place is:" + currentPlace);
					currentPlace = drive_train.getLeftDriveEncoderDistance();
				}

			}else{
				destination = currentPlace - distance;
				drive_train.tankDriving(-(speedPWM-0.03), -speedPWM);

				while(currentPlace > destination){
					System.out.println("Current Place is:" + currentPlace);
					currentPlace = drive_train.getLeftDriveEncoderDistance();
				}
			}
		//}
		drive_train.resetLeftDriveEncoderCount();
	}

	//This is the nonoverloaded version. Should not be used after testing day.
	public void driveArc(double radius, double degree, boolean isForward){
		double currentPlace = drive_train.getRightDriveEncoderDistance();
        double destination;
		double distanceRightArc;
		double distanceLeftArc;
		double distanceMiddleArc;
		double speed = 30;
		double time;
		double leftSpeedPWM;
		double rightSpeedPWM;
	

		//This is keeping track of the right wheel's distance traveled
		//This is checking if it forward and counterclockwise or backward and clockwise
		//(Inside or outside)
		if ((isForward && degree > 0) || !(isForward || degree > 0)){
			distanceRightArc = 2 * Constants.PI * (radius + 15.5) * Math.abs(degree) / 360;
			distanceMiddleArc = 2 * Constants.PI * radius * Math.abs(degree) / 360;
			distanceLeftArc = 2 * Constants.PI * (radius - 15.5) * Math.abs(degree) / 360;
		}else{
			distanceRightArc = 2 * Constants.PI * (radius -15.5) * Math.abs(degree) / 360;
			distanceMiddleArc = 2 * Constants.PI * radius * Math.abs(degree) / 360;
			distanceLeftArc = 2 * Constants.PI * (radius + 15.5) * Math.abs(degree) / 360;
		}
		
		time = distanceMiddleArc/speed;
		//Note to self and Ryan and others. This needs to be added.
		leftSpeedPWM = (((distanceLeftArc/time)* 0.465) + 44.1)/100; //realLeftSpeed input into PWM equation
		rightSpeedPWM = (((distanceRightArc/time)* 0.465) + 44.1)/100; //realRightSpeed input into PWM equation
		
		if(rightSpeedPWM > 0.8 || leftSpeedPWM > 0.8){
			System.out.println("SPEED TOO HIGH!");
		}else if(rightSpeedPWM < 0.5 || leftSpeedPWM < 0.5){
			System.out.println("SPEED TOO LOW!");
		}else{
			if (isForward){
				destination = currentPlace + distanceRightArc;
				while(currentPlace < destination){
					System.out.println("Current Place is:" + currentPlace);
					currentPlace = drive_train.getRightDriveEncoderDistance();
					drive_train.tankDriving((leftSpeedPWM+0.03), rightSpeedPWM);	
				}
			}else{
				destination = currentPlace - distanceRightArc;
				while(currentPlace > destination){
					System.out.println("Current Place is:" + currentPlace);
					currentPlace = drive_train.getRightDriveEncoderDistance();
					drive_train.tankDriving(-(leftSpeedPWM+0.03), -rightSpeedPWM);
				}	
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
		drive_train.resetLeftDriveEncoderCount();
		drive_train.resetRightDriveEncoderCount();
		double currentPlace;
		double initialAngle;
		double currentAngle; 
		double destination;
		double distanceRightArc;
		double distanceLeftArc;
		double distanceMiddleArc;
		//double averageRobotSpeed; 
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
			distanceLeftArc = 2 * Constants.PI * (radius - 15.5) * Math.abs(degree) / 360;
		}else{
			distanceRightArc = 2 * Constants.PI * (radius -15.5) * Math.abs(degree) / 360;
			distanceMiddleArc = 2 * Constants.PI * radius * Math.abs(degree) / 360;
			distanceLeftArc = 2 * Constants.PI * (radius + 15.5) * Math.abs(degree) / 360;
		}

		time = distanceMiddleArc/speed;
		realLeftSpeed = distanceLeftArc/time;
		realRightSpeed = distanceRightArc/time;
		leftSpeedPWM = ((realLeftSpeed* 0.465) + 44.1)/100; //realLeftSpeed input into PWM equation
		rightSpeedPWM = ((realRightSpeed* 0.465) + 44.1)/100; //realRightSpeed input into PWM equation

		/*if(rightSpeedPWM > 0.8 || leftSpeedPWM > 0.8){
			System.out.println("SPEED TOO HIGH!");
		}else if(rightSpeedPWM < 0.5 || leftSpeedPWM < 0.5){
			System.out.println("SPEED TOO LOW!");
		}else{*/

		/*
			if (isForward){
				//drive_train.resetLeftDriveEncoderCount();
				//drive_train.resetRightDriveEncoderCount();

				drive_train.tankDriving((leftSpeedPWM+0.03), rightSpeedPWM);
				if(distanceLeftArc > distanceRightArc){
					currentAngle = drive_train.getGyro();
					currentPlace = drive_train.getLeftDriveEncoderDistance();
					destination = currentPlace + distanceLeftArc;
					while(currentPlace < destination){
						System.out.println("Current Place is:" + currentPlace);		
						currentPlace = drive_train.getLeftDriveEncoderDistance();
					}
				}else{
					currentPlace = drive_train.getRightDriveEncoderDistance();
					destination = currentPlace + distanceRightArc;
					while(currentPlace < destination){
						System.out.println("Current Place is:" + currentPlace);
						currentPlace = drive_train.getRightDriveEncoderDistance();		
					}
				}
			}else{
				//drive_train.resetLeftDriveEncoderCount();
				//drive_train.resetRightDriveEncoderCount();
				
				drive_train.tankDriving(-(leftSpeedPWM-0.03), -rightSpeedPWM);
				if(distanceLeftArc > distanceRightArc){
					currentPlace = drive_train.getLeftDriveEncoderDistance();
					destination = currentPlace - distanceLeftArc;
					while(currentPlace > destination){
						System.out.println("Current Place is:" + currentPlace);		
						currentPlace = drive_train.getLeftDriveEncoderDistance();
					}
				}else{
					currentPlace = drive_train.getRightDriveEncoderDistance();
					destination = currentPlace - distanceRightArc;
					while(currentPlace > destination){
						System.out.println("Current Place is:" + currentPlace);
						currentPlace = drive_train.getRightDriveEncoderDistance();		
					}
				}
			}
		//}
		*/
		currentAngle = drive_train.getGyro();
		initialAngle = drive_train.getGyro();
		
		if(isForward){
			drive_train.tankDriving(leftSpeedPWM, rightSpeedPWM);
			if(degree > 0)
			{
				while(currentAngle < initialAngle + degree)
				{
					currentAngle = drive_train.getGyro();
				}
			}else{
				while(currentAngle > initialAngle + degree)
				{
					currentAngle = drive_train.getGyro();
				}
			}
		}else{
			drive_train.tankDriving(-leftSpeedPWM, -rightSpeedPWM);	
			if(degree > 0)
			{
				while(currentAngle < initialAngle + degree)
				{
					currentAngle = drive_train.getGyro();
				}
			}else{
				while(currentAngle > initialAngle + degree)
				{
					currentAngle = drive_train.getGyro();
				}
			}
		}

		drive_train.resetLeftDriveEncoderCount();
		drive_train.resetRightDriveEncoderCount();
	}

	public void driveHeading(double radius, double degree, boolean isForward, double realSpeed, double endDegree){
		drive_train.resetLeftDriveEncoderCount();
		drive_train.resetRightDriveEncoderCount();
		double currentPlace;
		double initialAngle;
		double currentAngle; 
		double destination;
		double distanceRightArc;
		double distanceLeftArc;
		double distanceMiddleArc;
		//double averageRobotSpeed; 
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
			distanceLeftArc = 2 * Constants.PI * (radius - 15.5) * Math.abs(degree) / 360;
		}else{
			distanceRightArc = 2 * Constants.PI * (radius -15.5) * Math.abs(degree) / 360;
			distanceMiddleArc = 2 * Constants.PI * radius * Math.abs(degree) / 360;
			distanceLeftArc = 2 * Constants.PI * (radius + 15.5) * Math.abs(degree) / 360;
		}

		time = distanceMiddleArc/speed;
		realLeftSpeed = distanceLeftArc/time;
		realRightSpeed = distanceRightArc/time;
		leftSpeedPWM = ((realLeftSpeed* 0.465) + 44.1)/100; //realLeftSpeed input into PWM equation
		rightSpeedPWM = ((realRightSpeed* 0.465) + 44.1)/100; //realRightSpeed input into PWM equation

		/*if(rightSpeedPWM > 0.8 || leftSpeedPWM > 0.8){
			System.out.println("SPEED TOO HIGH!");
		}else if(rightSpeedPWM < 0.5 || leftSpeedPWM < 0.5){
			System.out.println("SPEED TOO LOW!");
		}else{*/

		/*
			if (isForward){
				//drive_train.resetLeftDriveEncoderCount();
				//drive_train.resetRightDriveEncoderCount();

				drive_train.tankDriving((leftSpeedPWM+0.03), rightSpeedPWM);
				if(distanceLeftArc > distanceRightArc){
					currentAngle = drive_train.getGyro();
					currentPlace = drive_train.getLeftDriveEncoderDistance();
					destination = currentPlace + distanceLeftArc;
					while(currentPlace < destination){
						System.out.println("Current Place is:" + currentPlace);		
						currentPlace = drive_train.getLeftDriveEncoderDistance();
					}
				}else{
					currentPlace = drive_train.getRightDriveEncoderDistance();
					destination = currentPlace + distanceRightArc;
					while(currentPlace < destination){
						System.out.println("Current Place is:" + currentPlace);
						currentPlace = drive_train.getRightDriveEncoderDistance();		
					}
				}
			}else{
				//drive_train.resetLeftDriveEncoderCount();
				//drive_train.resetRightDriveEncoderCount();
				
				drive_train.tankDriving(-(leftSpeedPWM-0.03), -rightSpeedPWM);
				if(distanceLeftArc > distanceRightArc){
					currentPlace = drive_train.getLeftDriveEncoderDistance();
					destination = currentPlace - distanceLeftArc;
					while(currentPlace > destination){
						System.out.println("Current Place is:" + currentPlace);		
						currentPlace = drive_train.getLeftDriveEncoderDistance();
					}
				}else{
					currentPlace = drive_train.getRightDriveEncoderDistance();
					destination = currentPlace - distanceRightArc;
					while(currentPlace > destination){
						System.out.println("Current Place is:" + currentPlace);
						currentPlace = drive_train.getRightDriveEncoderDistance();		
					}
				}
			}
		//}
		*/
		currentAngle = drive_train.getGyro();
		initialAngle = drive_train.getGyro();
		
		if(isForward){
			drive_train.tankDriving(leftSpeedPWM, rightSpeedPWM);
			if(degree > 0)
			{
				while(currentAngle < endDegree)
				{
					currentAngle = drive_train.getGyro();
				}
			}else{
				while(currentAngle > endDegree)
				{
					currentAngle = drive_train.getGyro();
				}
			}
		}else{
			drive_train.tankDriving(-leftSpeedPWM, -rightSpeedPWM);	
			if(degree > 0)
			{
				while(currentAngle < endDegree)
				{
					currentAngle = drive_train.getGyro();
				}
			}else{
				while(currentAngle > endDegree)
				{
					currentAngle = drive_train.getGyro();
				}
			}
		}

		drive_train.resetLeftDriveEncoderCount();
		drive_train.resetRightDriveEncoderCount();
	}
	
	public void manualTesting(double speed){
		if(speed < 0.8){
			drive_train.tankDriving(speed + 0.03, speed);
		}else{
			System.out.println("SPEED IS TOO HIGH");
		}
	}

	//These are all the paths
	//We may need to fix Barrel
	public void driveBarrel(){
		this.driveStraightDistance(100, true, 40);
		this.driveHeading(25, -354.4413274, true, 50, -334.44);
		this.driveStraightDistance(100, true, 40);
		this.driveHeading(24, 309.4413274, true, 50, -60);
		this.driveStraightDistance(84.85, true, 40);
		this.driveHeading(19, 225, true, 50, 160);
		this.driveStraightDistance(270, true, 55);
		isFinished();
	}
	  
	public void driveSlalom(){
		
		this.driveStraightDistance(10, true, 45);
		//this.driveArc(30, 90, true, 28);
		//this.driveArc(30, -73, true, 28);
		this.driveHeading(30, 90, true, 42, 55);
		this.driveHeading(30, -90, true, 42, 12.5);

		this.driveStraightDistance(85, true, 35);
		//this.driveArc(30, -75, true, 28);
		//this.driveArc(30, 345, true, 28);
		//this.driveArc(30, -65, true, 28);
		this.driveHeading(30, -90, true, 42, -60);
		//this.driveStraightDistance(5, true, 42);
		this.driveHeading(25, 360, true, 50, 240);
		this.driveHeading(30, -90, true, 35, 180);
		
		this.driveStraightDistance(52.5, true, 35);
		//this.driveArc(30, -80, true, 28);
		//this.driveArc(30, 90, true, 28);
		this.driveHeading(35, -90, true, 35, 110);
		this.driveHeading(35, 90, true, 35, 180);
	
		isFinished();
		
	}
	
	  public void driveBounce(){
		this.driveStraightDistance(5, true, 40);
		this.driveHeading(30, 90, true, 40, 90);
		this.driveStraightDistance(2, true, 40);
		this.driveStraightDistance(1, false, 25);
		this.driveHeading(30, 26.57, false, 40, 108);
		this.driveStraightDistance(70, false, 40);
		this.driveHeading(20, 153.43, false, 55, 245);
		this.driveStraightDistance(90, false, 40);    
		this.driveStraightDistance(90, true, 40);
		this.driveHeading(30, 90, true, 40, 360);    
		//this.driveStraightDistance(5, true, 40);
		this.driveHeading(30, 90, true, 40, 440);
		this.driveStraightDistance(90, true, 40);
		this.driveStraightDistance(15, false, 40);
		this.driveHeading(25, 90, false, 40, 540);
		isFinished();
	}

	  public void driveOsillating(){
		this.driveArc(30, 90, true, 35.0);
		this.driveArc(30, -180, true, 35.0);
		this.driveArc(30, 180, true, 35.0);
		this.driveArc(30, -180, true, 35.0);
		this.driveArc(30, 180, true, 35.0);
	}

	public void pausing(double timeToWait){
		double startTime = System.currentTimeMillis();
		double finalTime = startTime + timeToWait;
		drive_train.tankDriving(0, 0);
		while(startTime < finalTime){
			startTime = System.currentTimeMillis();
		}
	}
}
