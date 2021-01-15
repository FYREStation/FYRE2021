package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.subsystems.ShooterAndKick;

public class KickBackward extends CommandBase {

	private ShooterAndKick m_Kick;
    public KickBackward(ShooterAndKick system) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//requires(ControlPanel.theCommand);
		m_Kick = system;
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
    
    } 

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
        m_Kick.kickController.set(ControlMode.PercentOutput,-0.4);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
        return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		m_Kick.kickController.set(ControlMode.PercentOutput,0.0);
	}
}