package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.RobotContainer;

import frc.robot.subsystems.ShooterAndKick;


public class CircleKick extends CommandBase {
    public CircleKick(ShooterAndKick subsystem) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//requires(ControlPanel.theCommand);
	
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
    
    } 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
        double angle = 0;
        while(angle < 350 || angle > 360){
          RobotContainer.shooterObject.kickController.set(ControlMode.PercentOutput, 0.4);
          angle = (RobotContainer.shooterObject.kickEncoder.get()*360.0)%360;
		}
		RobotContainer.shooterObject.kickController.set(ControlMode.PercentOutput, 0.0);
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