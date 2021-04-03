/*----------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;


public class DriveArcade extends CommandBase {

	private Drivetrain drive_train;
	private DoubleSupplier move_Speed;
	private DoubleSupplier rotate_Speed;
	private DoubleSupplier XJoyStick;
	private DoubleSupplier YJoyStick;

	public DriveArcade(Drivetrain driveTrain, DoubleSupplier theY, DoubleSupplier theX) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		drive_train = driveTrain;
		XJoyStick = theX;
		YJoyStick = theY;
		addRequirements(drive_train);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
	} 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {

		//System.out.println(Constants.throttle * move_Speed.getAsDouble() + "and " + Constants.throttle *  rotate_Speed.getAsDouble());
		double yValue = YJoyStick.getAsDouble();
		double xValue = XJoyStick.getAsDouble();
		if(yValue < 0.2 && yValue > -0.2){
			yValue = 0.0;
		} 
		if(xValue < 0.2 && xValue > -0.2){
			xValue = 0.0;
		} 
		

		double leftPower = yValue - xValue;
		double rightPower = yValue + xValue;
		leftPower = -leftPower;
		rightPower = -rightPower;
		drive_train.tankDriving(Constants.throttle * leftPower, Constants.throttle * rightPower);
		//drive_train.arcadeDrive(Constants.throttle * move_Speed.getAsDouble(), Constants.throttle * YJoyStick.getAsDouble());
		
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
