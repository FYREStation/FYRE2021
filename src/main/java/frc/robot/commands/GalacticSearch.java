package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.SPI.Port;

public class GalacticSearch extends CommandBase {

    private Drivetrain drive_train;
    private DriverStation driver_station;
    
    private Gyro drive_gyro = new ADXRS450_Gyro(Port.kMXP);
    
    public GalacticSearch(Drivetrain driveTrain) {
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
        Shuffleboard.getTab("Gyro Is:").add((Sendable) drive_gyro);
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
