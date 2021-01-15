package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;


public class AnuCommand extends CommandBase {

	private Drivetrain drive_train;
	private DoubleSupplier move_Speed;
	private DoubleSupplier rotate_Speed;
	private double startTime;
    public AnuCommand(Drivetrain driveTrain, DoubleSupplier moveSpeed, DoubleSupplier rotateSpeed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//requires(ControlPanel.theCommand);
	
		drive_train = driveTrain;
		move_Speed = moveSpeed;
		rotate_Speed = rotateSpeed;
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
        return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		
	}
}